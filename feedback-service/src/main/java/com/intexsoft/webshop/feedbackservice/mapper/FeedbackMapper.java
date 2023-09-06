package com.intexsoft.webshop.feedbackservice.mapper;

import com.intexsoft.webshop.feedbackservice.dto.ChildFeedbackDto;
import com.intexsoft.webshop.feedbackservice.dto.FeedbackCreateDto;
import com.intexsoft.webshop.feedbackservice.dto.FeedbackDto;
import com.intexsoft.webshop.feedbackservice.model.Feedback;
import com.intexsoft.webshop.feedbackservice.model.UsersReplica;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        uses = {UserReplicaMapper.class})
public interface FeedbackMapper {
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "feedbackId", ignore = true)
    @Mapping(target = "subjectId", source = "feedbackCreateDto.subjectId")
    @Mapping(target = "header", source = "feedbackCreateDto.header")
    @Mapping(target = "score", source = "feedbackCreateDto.score")
    @Mapping(target = "subjectType", source = "feedbackCreateDto.subjectType")
    @Mapping(target = "parentFeedback", ignore = true)
    @Mapping(target = "childFeedbacks", ignore = true)
    @Mapping(target = "author", source = "author")
    Feedback toFeedback(FeedbackCreateDto feedbackCreateDto, UsersReplica author);

    @Mapping(target = "feedbackId", source = "feedbackId")
    @Mapping(target = "subjectId", source = "subjectId")
    @Mapping(target = "header", source = "header")
    @Mapping(target = "score", source = "score")
    @Mapping(target = "content", source = "content")
    @Mapping(target = "createdAt", source = "createdAt")
    @Mapping(target = "subjectType", source = "subjectType")
    @Mapping(target = "parentFeedbackId", source = "feedback.parentFeedback.feedbackId")
    @Mapping(target = "childFeedbackDtos", source = "childFeedbacks")
    @Mapping(target = "usersReplicaDto", source = "author")
    FeedbackDto toFeedbackDto(Feedback feedback);

    @Mapping(target = "feedbackId", source = "feedbackId")
    @Mapping(target = "content", source = "content")
    @Mapping(target = "createdAt", source = "createdAt")
    @Mapping(target = "parentFeedbackId", source = "feedback.parentFeedback.feedbackId")
    @Mapping(target = "childFeedbackDtos", source = "childFeedbacks")
    @Mapping(target = "usersReplicaDto", source = "author")
    ChildFeedbackDto toChildFeedbackDto(Feedback feedback);

    List<FeedbackDto> toFeedbackDtos(List<Feedback> allFeedbacks);
}