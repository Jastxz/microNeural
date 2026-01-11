package es.jastxz.micro_neural.repo;

import es.jastxz.micro_neural.model.UserRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRequestRepo extends JpaRepository<UserRequest, String> {
}
