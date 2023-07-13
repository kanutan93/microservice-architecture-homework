package ru.msa.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.msa.homework.model.dao.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
}
