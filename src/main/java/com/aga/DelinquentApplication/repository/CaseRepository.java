package com.aga.DelinquentApplication.repository;

import com.aga.DelinquentApplication.models.Case;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;

public interface CaseRepository extends JpaRepository<Case, Integer> {
    @Modifying
    @Query("UPDATE Case c SET c.caseStatus = 'Closed', c.endDate = :date WHERE c.caseId = :id")
    void updateStateById(@Param("id") Integer id,@Param("date") Date date);
}
