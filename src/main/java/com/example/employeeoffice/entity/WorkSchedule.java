package com.example.employeeoffice.entity;

import com.example.employeeoffice.entity.enums.WorkScheduleName;
import com.example.employeeoffice.generator.UuidTimeSequenceGenerator;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalTime;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
/**
 * Entity class for Work Schedule.
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "work_schedules")
@Schema(description = "Details about work schedules")
public class WorkSchedule {

    /**
     * Unique identifier for the work schedule.
     */
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID",
            type = UuidTimeSequenceGenerator.class)
    @Column(name = "sched_id")
    @Schema(description = "Unique identifier of the work schedule")
    private UUID schedId;

    /**
     * Name of the work schedule.
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "sched_name")
    @Schema(description = "Name of the work schedule")
    private WorkScheduleName schedName;

    /**
     * Indicates if the day is off.
     */
    @Column(name = "is_day_off")
    @Schema(description = "Indicates if the day is off")
    private boolean isDayOff;

    /**
     * Start time of the work schedule.
     */
    @Column(name = "start_time")
    @Schema(description = "Start time of the work schedule")
    private LocalTime startTime;

    /**
     * End time of the work schedule.
     */
    @Column(name = "end_time")
    @Schema(description = "End time of the work schedule")
    private LocalTime endTime;

    /**
     * Employees associated with the work schedule.
     * One work schedule can be associated with multiple employees.
     */
    @OneToMany(mappedBy = "workSchedule", orphanRemoval = true, fetch = FetchType.LAZY)
    @Schema(description = "Employees associated with the work schedule")
    private Set<Employee> employees;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof WorkSchedule)) return false;
        WorkSchedule that = (WorkSchedule) o;
        return schedId.equals(that.schedId) && schedName == that.schedName;
    }

    @Override
    public int hashCode() {
        return Objects.hash(schedId, schedName);
    }

    @Override
    public String toString() {
        return "WorkSchedule{" +
                "schedId=" + schedId +
                ", schedName=" + schedName +
                ", isDayOff=" + isDayOff +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", employees=" + employees +
                '}';
    }
}
