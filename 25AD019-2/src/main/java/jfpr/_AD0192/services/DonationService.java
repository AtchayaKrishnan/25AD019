package jfpr._AD0192.services;

import jfpr._AD0192.models.Donation;
import jfpr._AD0192.repositories.DonationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DonationService {

    private final DonationRepository donationRepository;

    public DonationService(DonationRepository donationRepository) {
        this.donationRepository = donationRepository;
    }

    public List<Donation> getAllDonations() {
        return donationRepository.findAll();
    }

    public Donation getDonationById(Long id) {
        return donationRepository.findById(id).orElse(null);
    }

    public Donation createDonation(Donation donation) {
        return donationRepository.save(donation);
    }

    public List<Donation> getDonationsByDrive(Long driveId) {
        return donationRepository.findByDriveId(driveId);
    }

    public void deleteDonation(Long id) {
        donationRepository.deleteById(id);
    }
}