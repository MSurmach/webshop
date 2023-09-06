package com.intexsoft.webshop.feedbackservice.repository;

import com.intexsoft.webshop.feedbackservice.model.Feedback;
import com.intexsoft.webshop.feedbackservice.model.enums.SubjectType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
    @Query(value = "WITH RECURSIVE feedback_tree (feedback_id, subject_id, header, score, content, created_at, subject_type, parent_feedback_id, author_id) " +
            "AS (SELECT feedback_id, subject_id, header, score, content, created_at, subject_type, parent_feedback_id, author_id " +
            "FROM feedback " +
            "WHERE subject_id = :subjectId " +
            "AND subject_type = :#{#subjectType.name()} " +
            "UNION " +
            "SELECT f2.feedback_id, f2.subject_id, f2.header, f2.score, f2.content, f2.created_at, f2.subject_type, f2.parent_feedback_id, f2.author_id " +
            "FROM feedback f2 " +
            "INNER JOIN feedback_tree " +
            "ON feedback_tree.feedback_id = f2.parent_feedback_id) " +
            "SELECT feedback_id, subject_id, header, score, content, created_at, subject_type, parent_feedback_id, author_id " +
            "FROM feedback_tree WHERE parent_feedback_id IS NULL ",
            nativeQuery = true)
    List<Feedback> findAllBySubjectIdAndSubjectTypeAndParentFeedbackIsNull(@Param("subjectId") Long subjectId,
                                                                           @Param("subjectType") SubjectType subjectType);
}