package jfpr._AD0192.controllers;

import jfpr._AD0192.models.Recipient;
import jfpr._AD0192.services.RecipientService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recipients")
@CrossOrigin
public class RecipientController {

    private final RecipientService recipientService;

    public RecipientController(RecipientService recipientService) {
        this.recipientService = recipientService;
    }

    @GetMapping
    public List<Recipient> getAllRecipients() {
        return recipientService.getAllRecipients();
    }

    @GetMapping("/{id}")
    public Recipient getRecipient(@PathVariable Long id) {
        return recipientService.getRecipientById(id);
    }

    @PostMapping
    public Recipient createRecipient(@RequestBody Recipient recipient) {
        return recipientService.createRecipient(recipient);
    }

    @DeleteMapping("/{id}")
    public void deleteRecipient(@PathVariable Long id) {
        recipientService.deleteRecipient(id);
    }
}
