
package com.example.service;

import com.example.model.JournalEntity;
import com.example.repository.JournalRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JournalService {

    private final JournalRepository journalRepository;

    public JournalService(JournalRepository journalRepository) {
        this.journalRepository = journalRepository;
    }

    public List<JournalEntity> getAllJournals() {
        return journalRepository.findAll();
    }

    public JournalEntity getJournalById(Long id) {
        return journalRepository.findById(id).orElse(null);
    }

    public JournalEntity createJournal(JournalEntity journal) {
        return journalRepository.save(journal);
    }

    public JournalEntity updateJournal(Long id, JournalEntity journal) {
        if (!journalRepository.existsById(id)) return null;
        journal.setId(id);
        return journalRepository.save(journal);
    }

    public boolean deleteJournal(Long id) {
        if (!journalRepository.existsById(id)) return false;
        journalRepository.deleteById(id);
        return true;
    }
}
