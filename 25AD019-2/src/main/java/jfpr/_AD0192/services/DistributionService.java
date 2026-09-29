package jfpr._AD0192.services;

import jfpr._AD0192.models.Distribution;
import jfpr._AD0192.repositories.DistributionRepository;
import jfpr._AD0192.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DistributionService {

    private final DistributionRepository distributionRepository;

    public DistributionService(DistributionRepository distributionRepository) {
        this.distributionRepository = distributionRepository;
    }

    // CREATE
    public Distribution createDistribution(Distribution distribution) {
        return distributionRepository.save(distribution);
    }

    // READ
    public List<Distribution> getAllDistributions() {
        return distributionRepository.findAll();
    }

    // UPDATE
    public Distribution updateDistribution(
            Long id,
            Distribution newDistribution) {

        Distribution existingDistribution =
                distributionRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Distribution not found with id: " + id
                                )
                        );

        existingDistribution.setDonationId(
                newDistribution.getDonationId()
        );

        existingDistribution.setRecipientId(
                newDistribution.getRecipientId()
        );

        existingDistribution.setQuantityReceived(
                newDistribution.getQuantityReceived()
        );

        existingDistribution.setDistributionDate(
                newDistribution.getDistributionDate()
        );

        return distributionRepository.save(
                existingDistribution
        );
    }
}
