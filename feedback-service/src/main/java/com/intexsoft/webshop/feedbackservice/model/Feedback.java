package com.intexsoft.webshop.feedbackservice.model;

import com.intexsoft.webshop.feedbackservice.model.enums.SubjectType;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.proxy.HibernateProxy;

import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "feedback", schema = "public")
@Getter
@Setter
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
public class Feedback {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "feedback_id", nullable = false)
    Long feedbackId;
    @Column(name = "subject_id", nullable = false)
    Long subjectId;
    @Column(name = "header")
    String header;
    @Column(name = "score")
    Short score;
    @Column(name = "content", nullable = false, length = 2500)
    String content;
    @CreationTimestamp
    @Column(name = "created_at", nullable = false)
    LocalDateTime createdAt;
    @Column(name = "subject_type", nullable = false)
    @Enumerated(EnumType.STRING)
    SubjectType subjectType;
    @ManyToOne(
            fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE}
    )
    @JoinColumn(name = "parent_feedback_id",
            foreignKey = @ForeignKey(name = "fk_child_to_parent_feedback"))
    @ToString.Exclude
    Feedback parentFeedback;
    @OneToMany(
            mappedBy = "parentFeedback",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @ToString.Exclude
    Set<Feedback> childFeedbacks = new LinkedHashSet<>();
    @ManyToOne
    @JoinColumn(name = "author_id",
            foreignKey = @ForeignKey(name = "fk_feedback_user"))
    UsersReplica author;

    public void addParentFeedback(Feedback parentFeedback){
        this.parentFeedback = parentFeedback;
        parentFeedback.getChildFeedbacks().add(this);
    }

    @Override
    public final boolean equals(Object object) {
        if (this == object) return true;
        if (object == null) return false;
        Class<?> oEffectiveClass = object instanceof HibernateProxy ? ((HibernateProxy) object).getHibernateLazyInitializer().getPersistentClass() : object.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        Feedback feedback = (Feedback) object;
        return getFeedbackId() != null && Objects.equals(getFeedbackId(), feedback.getFeedbackId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}