package com.kotlin.board.domain

import com.kotlin.board.exception.CommentNotUpdatableException
import com.kotlin.board.service.dto.CommentUpdateRequestDto
import jakarta.persistence.ConstraintMode
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.ForeignKey
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne

@Entity
class Comment(
    content: String,
    post: Post,
    createdBy: String,
) : BaseEntity(createdBy = createdBy) {
    fun update(updateRequestDto: CommentUpdateRequestDto) {
        if (updateRequestDto.updatedBy != this.createdBy) {
            throw CommentNotUpdatableException()
        }
        this.content = updateRequestDto.content
        super.updatedBy(updateRequestDto.updatedBy)
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0

    var content: String = content
        protected set

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(foreignKey = ForeignKey(ConstraintMode.CONSTRAINT))
    var post: Post = post
        protected set
}
