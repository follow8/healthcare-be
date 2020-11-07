package com.ownfollow.healthcare.repository;

import com.ownfollow.healthcare.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Account, Long> {
  Optional<Account> findByUsername(@Param("username") String username);
  Optional<Account> findByEmail(String email);
}
