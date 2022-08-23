package com.raju.service;

import com.raju.entity.Plan;
import com.raju.entity.PlanCategory;
import com.raju.repo.PlanCategoryRepo;
import com.raju.repo.PlanRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class PlanServiceImpl implements PlanService{

    @Autowired
    private PlanRepo planRepo;

    @Autowired
    private PlanCategoryRepo planCategoryRepo;

    @Override
    public Map<Integer, String> getCategories() {
        List<PlanCategory> categories = planCategoryRepo.findAll();
        Map<Integer, String> categoriesMap = new HashMap<>();
        categories.forEach(planCategory -> {
            categoriesMap.put(planCategory.getCategoryId(), planCategory.getCategoryName());
        });
        return categoriesMap;
    }

    @Override
    public boolean upsertPlan(Plan plan) {
        Plan saved = planRepo.save(plan);
        return saved.getPlanId() != null;
    }

    @Override
    public List<Plan> getAllPlans() {
        return planRepo.findAll();
    }

    @Override
    public Plan getPlanById(Integer id) {
        Optional<Plan> findById = planRepo.findById(id);
        if (findById.isPresent()) {
            return findById.get();
        }
        return null;
    }

    @Override
    public boolean deletePlanById(Integer id) {
        boolean status = false;
        try {
            planRepo.deleteById(id);
            status = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }

    @Override
    public boolean planStatusChange(Integer id, String status) {
        Optional<Plan> findById = planRepo.findById(id);
        if (findById.isPresent()) {
            Plan plan = findById.get();
            plan.setActiveSw(status);
            planRepo.save(plan);
            return true;
        }
        return false;
    }
}
