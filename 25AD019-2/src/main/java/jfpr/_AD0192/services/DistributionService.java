package jfpr._AD0192.services;

import jfpr._AD0192.models.Distribution;
import jfpr._AD0192.repositories.DistributionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DistributionService {

    private final DistributionRepository distributionRepository;

    public DistributionService(DistributionRepository distributionRepository) {
        this.distributionRepository = distributionRepository;
    }

    public Distribution createDistribution(Distribution distribution) {
        return distributionRepository.save(distribution);
    }

    public List<Distribution> getAllDistributions() {
        return distributionRepository.findAll();
    }
}
