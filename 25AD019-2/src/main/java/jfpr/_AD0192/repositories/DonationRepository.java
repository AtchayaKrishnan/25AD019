package jfpr._AD0192.repositories;

import jfpr._AD0192.models.Donation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DonationRepository extends JpaRepository<Donation, Long> {

    List<Donation> findByDriveId(Long driveId);
}
