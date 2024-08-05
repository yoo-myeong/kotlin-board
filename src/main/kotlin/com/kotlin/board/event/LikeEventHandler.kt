package com.kotlin.board.event

import com.kotlin.board.domain.Like
import com.kotlin.board.event.dto.LikeEvent
import com.kotlin.board.exception.PostNotFoundException
import com.kotlin.board.repository.LikeRepository
import com.kotlin.board.repository.PostRepository
import com.kotlin.board.util.RedisUtil
import org.springframework.data.repository.findByIdOrNull
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Service
import org.springframework.transaction.event.TransactionalEventListener

@Service
class LikeEventHandler(
    private val postRepository: PostRepository,
    private val likeRepository: LikeRepository,
    private val redisUtil: RedisUtil,
) {
    @Async
    @TransactionalEventListener(LikeEvent::class)
    fun handleLikeEvent(event: LikeEvent) {
        val post = postRepository.findByIdOrNull(event.postId) ?: throw PostNotFoundException()
        redisUtil.increment(redisUtil.getLikeCountKey(event.postId))
        likeRepository.save(Like(post, event.createdBy)).id
    }
}
