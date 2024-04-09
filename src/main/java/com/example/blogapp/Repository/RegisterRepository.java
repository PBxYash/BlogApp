package com.example.blogapp.Repository;

import com.example.blogapp.Entity.Register;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RegisterRepository extends JpaRepository<Register,Long> {
    Optional<Register> findOneByEmailAndPassword(String email, String password);
    Register findByEmail(String email);
}
