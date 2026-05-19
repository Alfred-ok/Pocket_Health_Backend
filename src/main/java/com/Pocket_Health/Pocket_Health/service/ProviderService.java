package com.Pocket_Health.Pocket_Health.service;

import com.Pocket_Health.Pocket_Health.entity.Provider;
import com.Pocket_Health.Pocket_Health.entity.User;
import com.Pocket_Health.Pocket_Health.repository.ProviderRepository;
import com.Pocket_Health.Pocket_Health.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProviderService {

    private final ProviderRepository providerRepository;
    private final UserRepository userRepository;

    public Provider create(Map<String, Object> body) {
        UUID userId = UUID.fromString((String) body.get("userId"));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Provider provider = Provider.builder()
                .user(user)
                .providerName((String) body.get("providerName"))
                .category((String) body.get("category"))
                .specialty((String) body.get("specialty"))
                .location((String) body.get("location"))
                .region((String) body.get("region"))
                .isVerified(body.get("isVerified") != null && (Boolean) body.get("isVerified"))
                .isAvailable(body.get("isAvailable") == null || (Boolean) body.get("isAvailable"))
                .rates(body.get("rates") != null ? new BigDecimal(body.get("rates").toString()) : null)
                .build();
        return providerRepository.save(provider);
    }

    public List<Provider> getAll() { return providerRepository.findAll(); }

    public List<Provider> getAvailable() { return providerRepository.findByIsAvailableTrue(); }

    public List<Provider> getByRegion(String region) { return providerRepository.findByRegion(region); }

    public Provider getById(UUID id) {
        return providerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Provider not found"));
    }

    public Provider update(UUID id, Map<String, Object> body) {
        Provider provider = getById(id);
        if (body.containsKey("providerName")) provider.setProviderName((String) body.get("providerName"));
        if (body.containsKey("specialty"))    provider.setSpecialty((String) body.get("specialty"));
        if (body.containsKey("location"))     provider.setLocation((String) body.get("location"));
        if (body.containsKey("isAvailable"))  provider.setIsAvailable((Boolean) body.get("isAvailable"));
        if (body.containsKey("isVerified"))   provider.setIsVerified((Boolean) body.get("isVerified"));
        if (body.containsKey("rates"))        provider.setRates(new BigDecimal(body.get("rates").toString()));
        return providerRepository.save(provider);
    }
}
