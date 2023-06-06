package ru.msa.homework3.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.msa.homework3.model.dao.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
}
