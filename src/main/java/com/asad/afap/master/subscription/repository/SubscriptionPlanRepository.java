package com.asad.afap.master.subscription.repository;


import com.asad.afap.master.subscription.entity.SubscriptionPlans;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SubscriptionPlanRepository extends JpaRepository<SubscriptionPlans, UUID> {
         boolean existsByPlanName(String planName);

}
