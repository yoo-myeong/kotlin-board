package com.kotlin.board.service

import com.kotlin.board.exception.PostNotDeletableException
import com.kotlin.board.exception.PostNotFoundException
import com.kotlin.board.repository.PostRepository
import com.kotlin.board.service.dto.PostCreateRequestDto
import com.kotlin.board.service.dto.PostUpdateRequestDto
import com.kotlin.board.service.dto.toEntity
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class PostService(
    private val postRepository: PostRepository,
) {
    @Transactional
    fun createPost(requestDto: PostCreateRequestDto): Long = postRepository.save(requestDto.toEntity()).id

    @Transactional
    fun updatePost(
        id: Long,
        requestDto: PostUpdateRequestDto,
    ): Long {
        val post = postRepository.findByIdOrNull(id) ?: throw PostNotFoundException()
        post.update(requestDto)
        return id
    }

    @Transactional
    fun deletePost(
        id: Long,
        deletedBy: String,
    ): Long {
        val post = postRepository.findByIdOrNull(id) ?: throw PostNotFoundException()
        if (post.createdBy != deletedBy) throw PostNotDeletableException()
        postRepository.delete(post)

        return id
    }
}
