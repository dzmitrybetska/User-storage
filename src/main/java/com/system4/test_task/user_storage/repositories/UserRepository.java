package com.system4.test_task.user_storage.repositories;

import com.system4.test_task.user_storage.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
