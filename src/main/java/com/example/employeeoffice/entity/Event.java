package com.example.employeeoffice.entity;

import com.example.employeeoffice.entity.enums.EventType;
import com.example.employeeoffice.generator.UuidTimeSequenceGenerator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

/**
 * Represents an event in the employee office system.
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "events")
@Schema(description = "Represents an event in the employee office system.")
public class Event {

    /**
     * Unique identifier of the event.
     */
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID",
            type = UuidTimeSequenceGenerator.class)
    @Column(name = "ev_id")
    @Schema(description = "Unique identifier of the event")
    private UUID evId;

    /**
     * Start date and time of the event.
     */
    @NotNull(message = "Event type cannot be null")
    @Enumerated(EnumType.STRING)
    @Column(name = "ev_type")
    @Schema(description = "Start date and time of the event")
    private EventType evType;

    /**
     * Location of the event.
     */
    @Column(name = "start_date_time")
    @Schema(description = "Location of the event")
    private LocalDateTime startDateTime;

    /**
     * Location of the event.
     */
    @NotNull(message = "Location cannot be null")
    @Column(name = "location")
    @Schema(description = "Location of the event")
    private String location;

    /**
     * Description of the event.
     */
    @Column(name = "description")
    @Schema(description = "Description of the event")
    private String description;

    /**
     * Employees associated with the event.
     */
    @JsonIgnore
    @ManyToMany(mappedBy = "events", fetch = FetchType.LAZY)
    @Schema(description = "Employees associated with the event")
    private Set<Employee> employees;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Event)) return false;
        Event events = (Event) o;
        return Objects.equals(evId, events.evId) && evType == events.evType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(evId, evType);
    }

    @Override
    public String toString() {
        return "Event{" +
                "evId=" + evId +
                ", evType=" + evType +
                ", startDateTime=" + startDateTime +
                ", location='" + location + '\'' +
                ", description='" + description + '\'' +
                ", employees=" + employees +
                '}';
    }
}
