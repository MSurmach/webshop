package com.intexsoft.webshop.feedbackservice.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.intexsoft.webshop.feedbackservice.model.enums.SubjectType;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.hibernate.validator.constraints.Length;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonIgnoreProperties(ignoreUnknown = true)
public class FeedbackCreateDto {
    @Positive
    @NotNull
    Long subjectId;
    @Nullable
    String header;
    @Nullable
    Short score;
    @NotEmpty
    @Length(max = 2500)
    String content;
    @NotNull
    SubjectType subjectType;
    @Nullable
    @Positive
    Long parentFeedbackId;
    @NotNull
    @Positive
    Long authorId;
}