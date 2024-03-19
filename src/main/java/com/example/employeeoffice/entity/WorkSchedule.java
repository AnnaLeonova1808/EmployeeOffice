package com.example.employeeoffice.entity;

import com.example.employeeoffice.entity.enums.WorkScheduleName;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;
import java.util.UUID;
@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "workschedule")

public class WorkSchedule {
    @Id
    @GeneratedValue(generator = "UUID")
    @Column(name = "schedule_id", columnDefinition = "BINARY(16)")
    private UUID schedId;

//    @OneToOne(mappedBy = "employee")
//    private WorkSchedule workSchedule; один график работы может быть связан с несколькими сотрудниками. И добавить в класс Employee
    @Enumerated(EnumType.STRING)
    @Column(name = "schedule_name")
    private WorkScheduleName schedName;
    @Column(name = "is_day_off")
    private boolean isDayOff;
    @Column(name = "start_time")
    private LocalTime startTime;
    @Column(name = "end_time")
    private LocalTime endTime;
    @Column(name = "holidays_id", columnDefinition = "BINARY(16)")
    private UUID holidayId;
    @Column(name = "holiday_date")
    private LocalDate holidayDate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof WorkSchedule)) return false;
        WorkSchedule that = (WorkSchedule) o;
        return Objects.equals(schedId, that.schedId) && schedName == that.schedName;
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
                ", holidayId=" + holidayId +
                ", holidayDate=" + holidayDate +
                '}';
    }
}
