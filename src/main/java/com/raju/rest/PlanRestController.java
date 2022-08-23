package com.raju.rest;

import com.raju.entity.Plan;
import com.raju.service.PlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class PlanRestController {

    @Autowired
    private PlanService planService;

    @GetMapping("/categories")
    public ResponseEntity<Map<Integer, String>> planCategories() {
        Map<Integer, String> categories = planService.getCategories();

        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    @PostMapping("/plan")
    public ResponseEntity<String> savePlan(@RequestBody Plan plan) {
        String msg = "";

        boolean isSaved = planService.upsertPlan(plan);

        if (isSaved) {
            msg = "plan is saved";
        } else {
            msg = "plan is not saved";
        }

        return new ResponseEntity<>(msg, HttpStatus.CREATED);
    }

    @GetMapping("/plans")
    public ResponseEntity<List<Plan>> getAllPlans() {
        List<Plan> allPlans = planService.getAllPlans();

        return new ResponseEntity<>(allPlans, HttpStatus.OK);
    }

    @GetMapping("/plan/{planId}")
    public ResponseEntity<Plan> editPlan(@PathVariable Integer planId) {
        Plan plan = planService.getPlanById(planId);

        return new ResponseEntity<>(plan, HttpStatus.OK);
    }

    @PutMapping("/plan")
    public ResponseEntity<String> updatePlan(@RequestBody Plan plan) {
        boolean isUpdated = planService.upsertPlan(plan);

        String msg = "";

        if (isUpdated) {
            msg = "plan is updated";
        } else {
            msg = "plan is not updated";
        }

        return new ResponseEntity<>(msg, HttpStatus.OK);
    }

    @DeleteMapping("/plan/{planId}")
    public ResponseEntity<String> deletePlan(@PathVariable Integer planId) {
        boolean isDeleted = planService.deletePlanById(planId);

        String msg = "";

        if (isDeleted) {
            msg = "plan is deleted";
        } else {
            msg = "plan is not deleted";
        }

        return new ResponseEntity<>(msg, HttpStatus.OK);
    }

    @PutMapping("/status-changed/{planId}/{status}")
    public ResponseEntity<String> statusChange(@PathVariable Integer planId, @PathVariable String status) {
        boolean isStatusChanged = planService.planStatusChange(planId, status);

        String msg = "";

        if (isStatusChanged) {
            msg = "plan status is changed!";
        } else {
            msg = "plan status is not changed!";
        }

        return new ResponseEntity<>(msg, HttpStatus.OK);
    }
}
