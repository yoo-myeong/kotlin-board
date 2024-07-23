package com.kotlin.board.repository

import com.kotlin.board.domain.Post
import org.springframework.data.jpa.repository.JpaRepository

interface PostRepository : JpaRepository<Post, Long>
