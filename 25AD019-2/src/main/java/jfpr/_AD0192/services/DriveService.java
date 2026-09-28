package jfpr._AD0192.services;

import jfpr._AD0192.models.Drive;
import jfpr._AD0192.repositories.DriveRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DriveService {

    private final DriveRepository driveRepository;

    public DriveService(DriveRepository driveRepository) {
        this.driveRepository = driveRepository;
    }

    public List<Drive> getAllDrives() {
        return driveRepository.findAll();
    }

    public Drive getDriveById(Long id) {
        return driveRepository.findById(id).orElse(null);
    }

    public Drive createDrive(Drive drive) {
        return driveRepository.save(drive);
    }

    public Drive updateDrive(Long id, Drive drive) {
        Drive existing = driveRepository.findById(id).orElse(null);

        if (existing == null) {
            return null;
        }

        existing.setDriveName(drive.getDriveName());
        existing.setDescription(drive.getDescription());
        existing.setStartDate(drive.getStartDate());
        existing.setEndDate(drive.getEndDate());

        return driveRepository.save(existing);
    }

    public void deleteDrive(Long id) {
        driveRepository.deleteById(id);
    }
}