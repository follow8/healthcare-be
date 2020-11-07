package com.ownfollow.healthcare.repository;

import com.ownfollow.healthcare.entity.Diagnosis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiagnosisRepository extends JpaRepository<Diagnosis, Long> {

}
