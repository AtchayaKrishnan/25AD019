package jfpr._AD0192.controllers;

import jfpr._AD0192.models.Drive;
import jfpr._AD0192.services.DriveService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/drives")
@CrossOrigin
public class DriveController {

    private final DriveService driveService;

    public DriveController(DriveService driveService) {
        this.driveService = driveService;
    }

    @GetMapping
    public List<Drive> getAllDrives() {
        return driveService.getAllDrives();
    }

    @GetMapping("/{id}")
    public Drive getDrive(@PathVariable Long id) {
        return driveService.getDriveById(id);
    }

    @PostMapping
    public Drive createDrive(@RequestBody Drive drive) {
        return driveService.createDrive(drive);
    }

    @PutMapping("/{id}")
    public Drive updateDrive(@PathVariable Long id,
                             @RequestBody Drive drive) {
        return driveService.updateDrive(id, drive);
    }

    @DeleteMapping("/{id}")
    public void deleteDrive(@PathVariable Long id) {
        driveService.deleteDrive(id);
    }
}
