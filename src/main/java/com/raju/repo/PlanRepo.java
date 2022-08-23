package com.raju.repo;

import com.raju.entity.Plan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlanRepo extends JpaRepository<Plan, Integer> {
}
