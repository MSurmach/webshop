package com.intexsoft.webshop.feedbackservice.service;

import com.intexsoft.webshop.feedbackservice.dto.FeedbackCreateDto;
import com.intexsoft.webshop.feedbackservice.dto.FeedbackDto;
import com.intexsoft.webshop.feedbackservice.dto.FindAllFeedbacksBySubjectDto;

import java.util.List;

public interface FeedbackService {
    FeedbackDto saveFeedback(FeedbackCreateDto feedbackCreateDto);

    FeedbackDto findFeedbackById(Long feedbackId);

    List<FeedbackDto> findAllFeedbacksBySubjectId(FindAllFeedbacksBySubjectDto findAllFeedbacksBySubjectDto);
}
