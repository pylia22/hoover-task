package com.andersen.hoover.oleg.repository;

import com.andersen.hoover.oleg.entity.HooverTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HooverTaskRepository extends JpaRepository<HooverTask, Long> {
}
