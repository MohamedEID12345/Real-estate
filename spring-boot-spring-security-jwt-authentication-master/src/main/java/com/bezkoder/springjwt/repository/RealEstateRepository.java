package com.bezkoder.springjwt.repository;

import com.bezkoder.springjwt.models.RealEstate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RealEstateRepository extends JpaRepository<RealEstate,Long> {
    /*List<RealEstate> findByPublished(boolean published);*/
    List<RealEstate> findByNameContaining(String name);
}
