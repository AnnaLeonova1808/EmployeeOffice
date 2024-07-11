package com.example.employeeoffice.repository;

import com.example.employeeoffice.entity.Event;
import com.example.employeeoffice.entity.enums.EventType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;
@Repository
public interface EventRepository extends JpaRepository<Event, UUID> {


}
