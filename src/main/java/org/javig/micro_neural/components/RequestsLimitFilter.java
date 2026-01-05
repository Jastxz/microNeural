package org.javig.micro_neural.components;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.javig.micro_neural.model.GlobalRequest;
import org.javig.micro_neural.model.UserRequest;
import org.javig.micro_neural.repo.GlobalRequestRepo;
import org.javig.micro_neural.repo.UserRequestRepo;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class RequestsLimitFilter implements Filter {

    private static final int MAX_REQUESTS_GLOBAL = 100;
    private static final int MAX_REQUESTS_USER = 30;

    private static final long TIME_WINDOW_MILLIS = 10_000; // 10 segundos
    private static final long GLOBAL_ID = 1L;

    private final GlobalRequestRepo globalRequestRepo;
    private final UserRequestRepo userRequestRepo;

    public RequestsLimitFilter(GlobalRequestRepo globalRequestRepo, UserRequestRepo userRequestRepo) {
        this.globalRequestRepo = globalRequestRepo;
        this.userRequestRepo = userRequestRepo;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String endpoint = httpRequest.getRequestURI();

        String clientId = request.getRemoteAddr();
        long currentTime = System.currentTimeMillis();

        GlobalRequest globalLog = globalRequestRepo.findById(GLOBAL_ID)
                .orElse(createNewGlobalRequest());

        UserRequest userLog = userRequestRepo.findById(clientId)
                .orElse(createNewUserRequest(clientId));

        if (isTimeWindowExpired(globalLog.getLastRequestTimestamp(), currentTime)) {
            globalLog.setRequestCount(0);
            globalLog.setLastRequestTimestamp(currentTime);
        }

        if (isTimeWindowExpired(userLog.getLastRequestTimestamp(), currentTime)) {
            userLog.setRequestCount(0);
            userLog.setLastRequestTimestamp(currentTime);
        }

        globalLog.setRequestCount(globalLog.getRequestCount() + 1);
        userLog.setRequestCount(userLog.getRequestCount() + 1);

        globalLog.setLastRequestTimestamp(currentTime);
        userLog.setLastRequestTimestamp(currentTime);

        if (globalLog.getRequestCount() > MAX_REQUESTS_GLOBAL) {
            sendRateLimitResponse(response, "Global rate limit exceeded for " + endpoint);
            return;
        }

        if (userLog.getRequestCount() > MAX_REQUESTS_USER) {
            sendRateLimitResponse(response, "User rate limit exceeded for " + endpoint);
            return;
        }

        globalRequestRepo.save(globalLog);
        userRequestRepo.save(userLog);

        chain.doFilter(request, response);
    }

    private boolean isTimeWindowExpired(long lastRequestTime, long currentTime) {
        return lastRequestTime == 0 || (currentTime - lastRequestTime) > TIME_WINDOW_MILLIS;
    }

    private GlobalRequest createNewGlobalRequest() {
        GlobalRequest global = new GlobalRequest();
        global.setId(GLOBAL_ID);
        global.setRequestCount(0);
        global.setLastRequestTimestamp(0);
        return global;
    }

    private UserRequest createNewUserRequest(String clientId) {
        UserRequest user = new UserRequest();
        user.setClientId(clientId);
        user.setRequestCount(0);
        user.setLastRequestTimestamp(0);
        return user;
    }

    private void sendRateLimitResponse(ServletResponse response, String message) throws IOException {
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        httpResponse.setStatus(429);
        httpResponse.setContentType("application/json");
        httpResponse.getWriter().write("{\"error\": \"" + message + "\", \"code\": 429}");
    }
}