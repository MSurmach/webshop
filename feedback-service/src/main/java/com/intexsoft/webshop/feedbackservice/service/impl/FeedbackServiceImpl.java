package com.intexsoft.webshop.feedbackservice.service.impl;

import com.intexsoft.webshop.feedbackservice.dto.FeedbackCreateDto;
import com.intexsoft.webshop.feedbackservice.dto.FeedbackDto;
import com.intexsoft.webshop.feedbackservice.dto.FindAllFeedbacksBySubjectDto;
import com.intexsoft.webshop.feedbackservice.exception.notfound.FeedbackNotFoundException;
import com.intexsoft.webshop.feedbackservice.exception.notfound.UserReplicaNotFoundException;
import com.intexsoft.webshop.feedbackservice.mapper.FeedbackMapper;
import com.intexsoft.webshop.feedbackservice.model.Feedback;
import com.intexsoft.webshop.feedbackservice.model.UsersReplica;
import com.intexsoft.webshop.feedbackservice.model.enums.SubjectType;
import com.intexsoft.webshop.feedbackservice.repository.FeedbackRepository;
import com.intexsoft.webshop.feedbackservice.repository.UsersReplicaRepository;
import com.intexsoft.webshop.feedbackservice.service.FeedbackService;
import com.intexsoft.webshop.feedbackservice.util.JsonUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class FeedbackServiceImpl implements FeedbackService {
    private final FeedbackRepository feedbackRepository;
    private final FeedbackMapper feedbackMapper;
    private final UsersReplicaRepository usersReplicaRepository;

    @Override
    @Transactional
    public FeedbackDto saveFeedback(FeedbackCreateDto feedbackCreateDto) {
        log.info("IN: trying to save a new feedback = {}", JsonUtils.getAsString(feedbackCreateDto));
        Long authorId = feedbackCreateDto.getAuthorId();
        UsersReplica author = usersReplicaRepository.findById(authorId)
                .orElseThrow(() -> new UserReplicaNotFoundException(authorId));
        Long parentFeedbackId = feedbackCreateDto.getParentFeedbackId();
        Feedback newFeedback = feedbackMapper.toFeedback(feedbackCreateDto, author);
        if (!Objects.isNull(parentFeedbackId)) {
            Feedback foundParentFeedback = feedbackRepository.findById(parentFeedbackId)
                    .orElseThrow(() -> new FeedbackNotFoundException(parentFeedbackId));
            newFeedback.addParentFeedback(foundParentFeedback);
        }
        Feedback savedFeedback = feedbackRepository.save(newFeedback);
        log.info("OUT: the feedback saved successfully. The saved feedback = {}",
                JsonUtils.getAsString(savedFeedback));
        return feedbackMapper.toFeedbackDto(savedFeedback);
    }

    @Override
    public FeedbackDto findFeedbackById(Long feedbackId) {
        log.info("IN: trying to find a feedback by id = {}", feedbackId);
        Feedback foundFeedback = feedbackRepository.findById(feedbackId)
                .orElseThrow(() -> new FeedbackNotFoundException(feedbackId));
        log.info("OUT: the feedback with id = {} found successfully. Found feedback details = {}",
                feedbackId, JsonUtils.getAsString(foundFeedback));
        return feedbackMapper.toFeedbackDto(foundFeedback);
    }

    @Override
    public List<FeedbackDto> findAllFeedbacksBySubjectId(FindAllFeedbacksBySubjectDto findAllFeedbacksBySubjectDto) {
        Long subjectId = findAllFeedbacksBySubjectDto.getSubjectId();
        SubjectType subjectType = findAllFeedbacksBySubjectDto.getSubjectType();
        log.info("IN: trying to find all feedbacks for subject with id = {} and type = {}", subjectId, subjectType);
        List<Feedback> allFeedbacks = feedbackRepository.findAllBySubjectIdAndSubjectTypeAndParentFeedbackIsNull(subjectId, subjectType);
        log.info("OUT: {} feedbacks found", allFeedbacks.size());
        return feedbackMapper.toFeedbackDtos(allFeedbacks);
    }
}