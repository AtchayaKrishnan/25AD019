package jfpr._AD0192.controllers;

import jfpr._AD0192.models.Distribution;
import jfpr._AD0192.services.DistributionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/distributions")
@CrossOrigin
public class DistributionController {

    private final DistributionService distributionService;

    public DistributionController(DistributionService distributionService) {
        this.distributionService = distributionService;
    }

    @PostMapping
    public Distribution createDistribution(
            @RequestBody Distribution distribution) {

        return distributionService.createDistribution(distribution);
    }

    @GetMapping
    public List<Distribution> getAllDistributions() {
        return distributionService.getAllDistributions();
    }
}