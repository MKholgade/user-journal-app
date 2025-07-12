
package com.example.controller;

import com.example.model.JournalEntity;
import com.example.service.JournalService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/journals")
public class JournalController {

    private final JournalService journalService;

    public JournalController(JournalService journalService) {
        this.journalService = journalService;
    }

    @GetMapping
    public ResponseEntity<List<JournalEntity>> getAllJournals() {
        return ResponseEntity.ok(journalService.getAllJournals());
    }

    @GetMapping("/{id}")
    public ResponseEntity<JournalEntity> getJournalById(@PathVariable Long id) {
        JournalEntity journal = journalService.getJournalById(id);
        return journal != null ? ResponseEntity.ok(journal) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<JournalEntity> createJournal(@RequestBody JournalEntity journal) {
        return ResponseEntity.ok(journalService.createJournal(journal));
    }

    @PutMapping("/{id}")
    public ResponseEntity<JournalEntity> updateJournal(@PathVariable Long id, @RequestBody JournalEntity journal) {
        JournalEntity updated = journalService.updateJournal(id, journal);
        return updated != null ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteJournal(@PathVariable Long id) {
        return journalService.deleteJournal(id) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
