package com.intexsoft.webshop.feedbackservice.controller;

import com.intexsoft.webshop.feedbackservice.dto.FeedbackCreateDto;
import com.intexsoft.webshop.feedbackservice.dto.FeedbackDto;
import com.intexsoft.webshop.feedbackservice.dto.FindAllFeedbacksBySubjectDto;
import com.intexsoft.webshop.feedbackservice.service.FeedbackService;
import com.intexsoft.webshop.feedbackservice.util.JsonUtils;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/feedback")
@RequiredArgsConstructor
@Slf4j
@Validated
public class FeedbackController {
    private final FeedbackService feedbackService;

    @PostMapping
    public ResponseEntity<FeedbackDto> createFeedback(@RequestBody @Valid
                                                      FeedbackCreateDto feedbackCreateDto) {
        log.info("IN: request to create a new feedback received. Request body = {}",
                JsonUtils.getAsString(feedbackCreateDto));
        FeedbackDto createdFeedbackDto = feedbackService.saveFeedback(feedbackCreateDto);
        log.info("OUT: new feedback created successfully. Response body = {}", JsonUtils.getAsString(createdFeedbackDto));
        return ResponseEntity.ok(createdFeedbackDto);
    }

    @GetMapping("/{feedbackId}")
    public ResponseEntity<FeedbackDto> findFeedbackById(@PathVariable @Positive Long feedbackId) {
        log.info("IN: request to find a feedback by id = {} received", feedbackId);
        FeedbackDto feedbackDto = feedbackService.findFeedbackById(feedbackId);
        log.info("OUT: the feedback with id = {} found successfully. Response body = {}",
                feedbackId, JsonUtils.getAsString(feedbackDto));
        return ResponseEntity.ok(feedbackDto);
    }

    @PostMapping("/search")
    public ResponseEntity<List<FeedbackDto>> findAllFeedbacksBySubjectId(@RequestBody @Valid
                                                                         FindAllFeedbacksBySubjectDto findAllFeedbacksBySubjectDto) {
        log.info("IN: request to find all feedbacks for subject with id = {}, and subject type = {} received",
                findAllFeedbacksBySubjectDto.getSubjectId(), findAllFeedbacksBySubjectDto.getSubjectType());
        List<FeedbackDto> feedbackDtos = feedbackService.findAllFeedbacksBySubjectId(findAllFeedbacksBySubjectDto);
        log.info("OUT: response will return {} feedbacks for subject with id = {}",
                feedbackDtos.size(), findAllFeedbacksBySubjectDto.getSubjectId());
        return ResponseEntity.ok(feedbackDtos);
    }
}
