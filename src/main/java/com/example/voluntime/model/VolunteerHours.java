package com.example.voluntime.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "volunteer_hours")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VolunteerHours {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @NotNull(message = "User is required")
    private User user;

    @ManyToOne
    @JoinColumn(name = "event_id", nullable = false)
    @NotNull(message = "Event is required")
    private Event event;

    @Column(nullable = false)
    @Min(value = 1, message = "At least 1 hour must be recorded")
    @Max(value = 24, message = "Cannot log more than 24 hours per day")
    private int hoursWorked;

    @Column(nullable = false)
    @PastOrPresent(message = "Work date cannot be in the future")
    private LocalDate workDate;
}
