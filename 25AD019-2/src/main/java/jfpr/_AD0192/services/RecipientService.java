package jfpr._AD0192.services;

import jfpr._AD0192.models.Recipient;
import jfpr._AD0192.repositories.RecipientRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecipientService {

    private final RecipientRepository recipientRepository;

    public RecipientService(RecipientRepository recipientRepository) {
        this.recipientRepository = recipientRepository;
    }

    public List<Recipient> getAllRecipients() {
        return recipientRepository.findAll();
    }

    public Recipient getRecipientById(Long id) {
        return recipientRepository.findById(id).orElse(null);
    }

    public Recipient createRecipient(Recipient recipient) {
        return recipientRepository.save(recipient);
    }

    public void deleteRecipient(Long id) {
        recipientRepository.deleteById(id);
    }
}