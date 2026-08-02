package com.asad.afap.master.seeders;

import com.asad.afap.master.subscription.entity.SubscriptionPlans;
import com.asad.afap.master.subscription.repository.SubscriptionPlanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.Instant;

@Component
@RequiredArgsConstructor
public class SubscriptionPlanSeeder implements CommandLineRunner {

private final SubscriptionPlanRepository subscriptionPlanRepository;

@Override
public void run(String... args) {

    seedPlan(
            "Free",
            "Ideal for evaluation and small projects.",
            BigDecimal.ZERO,
            5,
            2,
            1,
            1000
    );

    seedPlan(
            "Starter",
            "Suitable for startups and small businesses.",
            BigDecimal.valueOf(999),
            25,
            10,
            5,
            50000
    );

    seedPlan(
            "Professional",
            "Designed for growing businesses with higher usage.",
            BigDecimal.valueOf(4999),
            100,
            50,
            20,
            500000
    );

    seedPlan(
            "Enterprise",
            "Custom enterprise plan with unlimited access.",
            BigDecimal.valueOf(99999),
            null,
            null,
            null,
            null
    );

    System.out.println("Subscription plans seeded successfully.");
}

private void seedPlan(
        String planName,
        String description,
        BigDecimal price,
        Integer maxUsers,
        Integer maxProjects,
        Integer maxApiKeys,
        Integer monthlyApiLimit
) {

    if (subscriptionPlanRepository.existsByPlanName(planName)) {
        return;
    }

    SubscriptionPlans plan = new SubscriptionPlans();

    plan.setPlanName(planName);
    plan.setDescription(description);
    plan.setPrice(price);
    plan.setCreatedAt(Instant.now());
    plan.setUpdatedAt(Instant.now());
    plan.setMaxUsers(maxUsers);
    plan.setMaxProjects(maxProjects);
    plan.setMaxApiKeys(maxApiKeys);
    plan.setMonthlyApiLimit(monthlyApiLimit);

    subscriptionPlanRepository.save(plan);
}
}