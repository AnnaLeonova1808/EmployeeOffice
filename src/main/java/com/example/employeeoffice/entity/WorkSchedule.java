package com.example.employeeoffice.entity;

import com.example.employeeoffice.entity.enums.WorkScheduleName;
import com.example.employeeoffice.generator.UuidTimeSequenceGenerator;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

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
    @GenericGenerator(name = "UUID",
            type = UuidTimeSequenceGenerator.class)
    @Column(name = "sched_id")
    private UUID schedId;

    @Enumerated(EnumType.STRING)
    @Column(name = "sched_name")
    private WorkScheduleName schedName;

    @Column(name = "is_day_off")
    private boolean isDayOff;

    @Column(name = "start_time")
    private LocalTime startTime;

    @Column(name = "end_time")
    private LocalTime endTime;

    @OneToMany(mappedBy = "workSchedule", fetch = FetchType.LAZY)
    private Set<Employee> employees; // (6) Связь с сотрудниками: один график работы может быть связан с несколькими сотрудниками.

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
