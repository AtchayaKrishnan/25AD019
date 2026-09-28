package jfpr._AD0192.controllers;

import jfpr._AD0192.models.Donation;
import jfpr._AD0192.services.DonationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/donations")
@CrossOrigin
public class DonationController {

    private final DonationService donationService;

    public DonationController(DonationService donationService) {
        this.donationService = donationService;
    }

    @GetMapping
    public List<Donation> getAllDonations() {
        return donationService.getAllDonations();
    }

    @GetMapping("/{id}")
    public Donation getDonation(@PathVariable Long id) {
        return donationService.getDonationById(id);
    }

    @GetMapping("/drive/{driveId}")
    public List<Donation> getByDrive(@PathVariable Long driveId) {
        return donationService.getDonationsByDrive(driveId);
    }

    @PostMapping
    public Donation createDonation(@RequestBody Donation donation) {
        return donationService.createDonation(donation);
    }

    @DeleteMapping("/{id}")
    public void deleteDonation(@PathVariable Long id) {
        donationService.deleteDonation(id);
    }
}
