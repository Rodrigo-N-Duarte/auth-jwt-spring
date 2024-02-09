package com.example.zart.repository;

import com.example.zart.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query(value = "SELECT * FROM tb_user u WHERE u.email = :email", nativeQuery = true)
    User findOneByEmail(@Param("email") String email);
}
