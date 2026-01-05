package org.javig.micro_neural.repo;

import org.javig.micro_neural.model.GlobalRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GlobalRequestRepo extends JpaRepository<GlobalRequest, Long> {
}
