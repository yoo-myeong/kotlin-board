package com.kotlin.board.repository

import com.kotlin.board.domain.Like
import org.springframework.data.jpa.repository.JpaRepository

interface LikeRepository : JpaRepository<Like, Long> {
    fun countByPostId(postId: Long): Long
}
