package com.kotlin.board.service

import com.kotlin.board.exception.CommentNotDeletableException
import com.kotlin.board.exception.CommentNotFoundException
import com.kotlin.board.exception.PostNotFoundException
import com.kotlin.board.repository.CommentRepository
import com.kotlin.board.repository.PostRepository
import com.kotlin.board.service.dto.CommentCreateRequestDto
import com.kotlin.board.service.dto.CommentUpdateRequestDto
import com.kotlin.board.service.dto.toEntity
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class CommentService(
    private val commentRepository: CommentRepository,
    private val postRepository: PostRepository,
) {
    @Transactional
    fun createdComment(
        postId: Long,
        createRequestDto: CommentCreateRequestDto,
    ): Long {
        val post = postRepository.findByIdOrNull(postId) ?: throw PostNotFoundException()
        return commentRepository.save(createRequestDto.toEntity(post)).id
    }

    @Transactional
    fun updateComment(
        id: Long,
        updatedRequestDto: CommentUpdateRequestDto,
    ): Long {
        val comment = commentRepository.findByIdOrNull(id) ?: throw CommentNotFoundException()
        comment.update(updatedRequestDto)

        return comment.id
    }

    @Transactional
    fun deleteComment(
        id: Long,
        deletedBy: String,
    ): Long {
        val comment = commentRepository.findByIdOrNull(id) ?: throw CommentNotFoundException()
        if (comment.createdBy != deletedBy) {
            throw CommentNotDeletableException()
        }
        commentRepository.delete(comment)

        return id
    }
}
