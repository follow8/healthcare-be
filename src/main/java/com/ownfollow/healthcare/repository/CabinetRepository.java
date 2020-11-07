package com.ownfollow.healthcare.repository;

import com.ownfollow.healthcare.entity.Cabinet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CabinetRepository extends JpaRepository<Cabinet, Long> {

}
