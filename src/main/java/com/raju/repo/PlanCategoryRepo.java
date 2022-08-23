package com.raju.repo;

import com.raju.entity.PlanCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlanCategoryRepo extends JpaRepository<PlanCategory, Integer> {
}
