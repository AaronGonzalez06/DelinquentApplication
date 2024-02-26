package com.aga.DelinquentApplication.repository;

import com.aga.DelinquentApplication.models.Delinquent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DelinquentRepository extends JpaRepository<Delinquent, Integer> {
}
