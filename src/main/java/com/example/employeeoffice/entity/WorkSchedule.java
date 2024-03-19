package com.example.employeeoffice.entity;

import com.example.employeeoffice.entity.enums.WorkScheduleName;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "work_schedules")

public class WorkSchedule {
    @Id
    @GeneratedValue(generator = "UUID")
    @Column(name = "schedule_id")
    private UUID scheduleId;

    @Enumerated(EnumType.STRING)
    @Column(name = "schedule_name")
    private WorkScheduleName scheduleName;

    @Column(name = "is_day_off")
    private boolean isDayOff;

    @Column(name = "start_time")
    private LocalTime startTime;

    @Column(name = "end_time")
    private LocalTime endTime;

    @Column(name = "holidays_id")
    private UUID holidayId;

    @Column(name = "holiday_date")
    private LocalDate holidayDate;

    @OneToMany(mappedBy = "work_schedule")
    private Set<Employee> employees; // (6) Связь с сотрудниками: один график работы может быть связан с несколькими сотрудниками.

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof WorkSchedule)) return false;
        WorkSchedule that = (WorkSchedule) o;
        return scheduleId.equals(that.scheduleId) && scheduleName == that.scheduleName;
    }

    @Override
    public int hashCode() {
        return Objects.hash(scheduleId, scheduleName);
    }

    @Override
    public String toString() {
        return "WorkSchedule{" +
                "scheduleId=" + scheduleId +
                ", scheduleName=" + scheduleName +
                ", isDayOff=" + isDayOff +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", holidayId=" + holidayId +
                ", holidayDate=" + holidayDate +
                ", employees=" + employees +
                '}';
    }
}
