package com.kotlin.board.controller.dto

import com.kotlin.board.service.dto.CommentResponseDto

data class CommentResponse(
    val id: Long,
    val content: String,
    val createdAt: String,
    val createdBy: String,
)

fun CommentResponseDto.toResponse() = CommentResponse(id, content, createdAt, createdBy)
