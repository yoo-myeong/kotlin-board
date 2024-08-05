package com.kotlin.board.service

import com.kotlin.board.event.dto.LikeEvent
import com.kotlin.board.repository.LikeRepository
import com.kotlin.board.repository.PostRepository
import com.kotlin.board.util.RedisUtil
import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class LikeService(
    private val likeRepository: LikeRepository,
    private val postRepository: PostRepository,
    private val redisUtil: RedisUtil,
    private val applicationEventPublisher: ApplicationEventPublisher,
) {
    @Transactional
    fun createLike(
        postId: Long,
        createdBy: String,
    ) {
        applicationEventPublisher.publishEvent(LikeEvent(postId, createdBy))
    }

    fun countLike(postId: Long): Long {
        redisUtil.getCount(redisUtil.getLikeCountKey(postId))?.let { return it }

        with(likeRepository.countByPostId(postId)) {
            redisUtil.setData(redisUtil.getLikeCountKey(postId), this)
            return this
        }
    }
}
