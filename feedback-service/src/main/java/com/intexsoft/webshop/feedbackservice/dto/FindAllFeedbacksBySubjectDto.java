package com.intexsoft.webshop.feedbackservice.dto;

import com.intexsoft.webshop.feedbackservice.model.enums.SubjectType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FindAllFeedbacksBySubjectDto {
    @Positive
    @NotNull
    Long subjectId;
    @NotNull
    SubjectType subjectType;
}