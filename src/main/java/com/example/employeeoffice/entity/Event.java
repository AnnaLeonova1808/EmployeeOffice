package com.example.employeeoffice.entity;

import com.example.employeeoffice.entity.enums.EventType;
import com.example.employeeoffice.generator.UuidTimeSequenceGenerator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "events")
public class Event {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID",
            type = UuidTimeSequenceGenerator.class)
    @Column(name = "ev_id")
    private UUID evId;

    @Enumerated(EnumType.STRING)
    @Column(name = "ev_type")
    private EventType evType;

    @Column(name = "start_date_time")
    private LocalDateTime startDateTime;

    @Column(name = "location")
    private String location;

    @Column(name = "description")
    private String description;

    @JsonIgnore
    @ManyToMany(mappedBy = "events", fetch = FetchType.LAZY)
    private Set<Employee> employees;// (9)  Одно событие (Events) может быть связано с несколькими сотрудниками (Employee)

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
