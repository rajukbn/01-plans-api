package com.raju.service;

import com.raju.entity.Plan;

import java.util.List;
import java.util.Map;

public interface PlanService {
    public Map<Integer, String> getCategories();

    public boolean upsertPlan(Plan plan);//for save and update plan

    public List<Plan> getAllPlans();

    public Plan getPlanById(Integer id);

    public boolean deletePlanById(Integer id);

    public boolean planStatusChange(Integer id, String status);
}
