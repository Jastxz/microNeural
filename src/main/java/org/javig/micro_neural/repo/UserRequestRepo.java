package org.javig.micro_neural.repo;

import org.javig.micro_neural.model.UserRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRequestRepo extends JpaRepository<UserRequest, String> {
}
