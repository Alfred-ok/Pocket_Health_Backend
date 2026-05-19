package com.Pocket_Health.Pocket_Health.repository;

import com.Pocket_Health.Pocket_Health.entity.Provider;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProviderRepository extends JpaRepository<Provider, UUID> {
    List<Provider> findByRegion(String region);
    List<Provider> findByIsAvailableTrue();
    List<Provider> findByCategoryAndRegion(String category, String region);
    Optional<Provider> findByUser_UserId(UUID userId);
}
