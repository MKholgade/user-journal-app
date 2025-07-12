
package com.example.repository;

import com.example.model.JournalEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JournalRepository extends JpaRepository<JournalEntity, Long> {
}
