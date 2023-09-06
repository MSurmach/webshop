package com.intexsoft.webshop.feedbackservice.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.intexsoft.webshop.feedbackservice.model.enums.SubjectType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class FeedbackDto {
    Long feedbackId;
    Long subjectId;
    String header;
    Short score;
    String content;
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    LocalDateTime createdAt;
    @Enumerated(EnumType.STRING)
    SubjectType subjectType;
    Long parentFeedbackId;
    @JsonProperty("childFeedbacks")
    Set<ChildFeedbackDto> childFeedbackDtos;
    @JsonProperty("author")
    UsersReplicaDto usersReplicaDto;
}