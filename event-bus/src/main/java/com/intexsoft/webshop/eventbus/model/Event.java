package com.intexsoft.webshop.eventbus.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Entity
@Table(name = "event")
@Getter
@Setter
@ToString
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@AllArgsConstructor
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "event_id", nullable = false)
    Long eventId;
    @Column(name = "created_at", nullable = false)
    LocalDateTime createdAt;
    @Column(name = "json_content", nullable = false)
    String jsonContent;
    @Column(name = "type", nullable = false)
    String type;
}
