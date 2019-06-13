package com.whiterabbits.bpevolution.dao;

import com.whiterabbits.bpevolution.entities.BusinessProfile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BusinessProfileRepository extends JpaRepository<BusinessProfile, Long> {

    @Query("Select b from BusinessProfile b where b.code like :x")
    public Page<BusinessProfile> findByCode(@Param("x") String code, Pageable page);


    @Query("Select distinct b from BusinessProfile b" +
            " join b.applicationList app" +
            " join app.businessProfile bp where bp.id=b.id")
    public Page<BusinessProfile> findAllProfilesWithApp(Pageable page);
}
