package com.abcriativa.mediator.repository;

import com.abcriativa.mediator.domain.Lead;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LeadRepository extends JpaRepository<Lead, String> {

    List<Lead> findByEmail(String email);
}
