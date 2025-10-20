package com.youssef.conferenceservice.repositories;

import com.youssef.conferenceservice.entities.Conference;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConferenceRepo extends JpaRepository<Conference, Long> {
}
