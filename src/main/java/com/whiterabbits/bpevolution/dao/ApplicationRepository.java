package com.whiterabbits.bpevolution.dao;

import com.whiterabbits.bpevolution.entities.Application;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ApplicationRepository extends JpaRepository<Application, Long> {

    @Query("select a from Application a where a.code like :x")
    public Page<Application> findByCode(@Param("x") String code, Pageable page);
}
