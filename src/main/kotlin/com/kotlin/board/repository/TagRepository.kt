package com.kotlin.board.repository

import com.kotlin.board.domain.Tag
import org.springframework.data.jpa.repository.JpaRepository

interface TagRepository : JpaRepository<Tag, Long> {
    fun findByPostId(postId: Long): List<Tag>
}
