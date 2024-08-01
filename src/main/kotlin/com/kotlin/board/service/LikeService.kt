package com.kotlin.board.service

import com.kotlin.board.domain.Like
import com.kotlin.board.exception.PostNotFoundException
import com.kotlin.board.repository.LikeRepository
import com.kotlin.board.repository.PostRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class LikeService(
    private val likeRepository: LikeRepository,
    private val postRepository: PostRepository,
) {
    fun createLike(
        postId: Long,
        createdBy: String,
    ): Long {
        val post = postRepository.findByIdOrNull(postId) ?: throw PostNotFoundException()
        return likeRepository.save(Like(post, createdBy)).id
    }
}
