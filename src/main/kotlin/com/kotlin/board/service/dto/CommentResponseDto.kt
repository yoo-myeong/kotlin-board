package com.kotlin.board.service.dto

import com.kotlin.board.domain.Comment

data class CommentResponseDto(
    val id: Long,
    val content: String,
    val createdAt: String,
    val createdBy: String,
)

fun Comment.toResponseDto() =
    CommentResponseDto(
        id = id,
        content = content,
        createdBy = createdBy,
        createdAt = createdAt.toString(),
    )
