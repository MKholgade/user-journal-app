package com.example.service;

import com.example.model.JournalEntity;
import com.example.repository.JournalRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class JournalServiceTest {

    private JournalRepository journalRepository;
    private JournalService journalService;

    @BeforeEach
    void setUp() {
        journalRepository = mock(JournalRepository.class);
        journalService = new JournalService(journalRepository);
    }

    @Test
    void testGetAllJournals() {
        List<JournalEntity> mockList = List.of(
            new JournalEntity(1L, "Title1", "Desc1"),
            new JournalEntity(2L, "Title2", "Desc2")
        );

        when(journalRepository.findAll()).thenReturn(mockList);

        List<JournalEntity> result = journalService.getAllJournals();

        assertEquals(2, result.size());
        verify(journalRepository, times(1)).findAll();
    }

    @Test
    void testGetJournalById_Found() {
        JournalEntity journal = new JournalEntity(1L, "Title", "Desc");

        when(journalRepository.findById(1L)).thenReturn(Optional.of(journal));

        JournalEntity result = journalService.getJournalById(1L);

        assertNotNull(result);
        assertEquals("Title", result.getTitle());
    }

    @Test
    void testGetJournalById_NotFound() {
        when(journalRepository.findById(99L)).thenReturn(Optional.empty());

        JournalEntity result = journalService.getJournalById(99L);

        assertNull(result);
    }

    @Test
    void testCreateJournal() {
        JournalEntity journal = new JournalEntity(null, "New Title", "New Desc");
        JournalEntity savedJournal = new JournalEntity(1L, "New Title", "New Desc");

        when(journalRepository.save(journal)).thenReturn(savedJournal);

        JournalEntity result = journalService.createJournal(journal);

        assertEquals(1L, result.getId());
        assertEquals("New Title", result.getTitle());
    }

    @Test
    void testUpdateJournal_Found() {
        JournalEntity updated = new JournalEntity(1L, "Updated", "Updated Desc");

        when(journalRepository.existsById(1L)).thenReturn(true);
        when(journalRepository.save(updated)).thenReturn(updated);

        JournalEntity result = journalService.updateJournal(1L, updated);

        assertNotNull(result);
        assertEquals("Updated", result.getTitle());
    }

    @Test
    void testUpdateJournal_NotFound() {
        JournalEntity journal = new JournalEntity(2L, "Any", "Any");

        when(journalRepository.existsById(2L)).thenReturn(false);

        JournalEntity result = journalService.updateJournal(2L, journal);

        assertNull(result);
    }

    @Test
    void testDeleteJournal_Found() {
        when(journalRepository.existsById(1L)).thenReturn(true);
        doNothing().when(journalRepository).deleteById(1L);

        boolean result = journalService.deleteJournal(1L);

        assertTrue(result);
    }

    @Test
    void testDeleteJournal_NotFound() {
        when(journalRepository.existsById(2L)).thenReturn(false);

        boolean result = journalService.deleteJournal(2L);

        assertFalse(result);
    }
}
