package com.kotlin.board.controller.dto

import com.kotlin.board.service.dto.PostDetailResponseDto

data class PostDetailResponse(
    val id: Long,
    val title: String,
    val content: String,
    val createdBy: String,
    val createdAt: String,
    val comments: List<CommentResponse> = emptyList(),
    val tags: List<String> = emptyList(),
    val likeCount: Long = 0,
)

fun PostDetailResponseDto.toResponse() =
    PostDetailResponse(
        id = this.id,
        title = this.title,
        content = this.content,
        createdBy = this.createdBy,
        createdAt = this.createdAt,
        comments = comments.map { it.toResponse() },
        tags = tags,
        likeCount = likeCount,
    )
