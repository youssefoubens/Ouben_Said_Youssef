package com.youssef.keynoteservice.repositories;

import com.youssef.keynoteservice.entities.Keynote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KeynoteRepo extends JpaRepository<Keynote, Long> {
}
