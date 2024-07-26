package com.kotlin.board.service.dto

import com.kotlin.board.domain.Post

data class PostDetailResponseDto(
    val id: Long,
    val title: String,
    val content: String,
    val createdAt: String,
    val createdBy: String,
    val comments: List<CommentResponseDto>,
)

fun Post.toDetailResponseDto() =
    PostDetailResponseDto(
        id = this.id,
        title = this.title,
        content = this.content,
        createdAt = this.createdAt.toString(),
        createdBy = this.createdBy,
        comments = comments.map { it.toResponseDto() },
    )
