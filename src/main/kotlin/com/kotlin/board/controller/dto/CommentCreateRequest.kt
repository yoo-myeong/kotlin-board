package com.kotlin.board.controller.dto

import com.kotlin.board.service.dto.CommentCreateRequestDto

data class CommentCreateRequest(
    val content: String,
    val createdBy: String,
)

fun CommentCreateRequest.toDto() =
    CommentCreateRequestDto(
        createdBy = createdBy,
        content = content,
    )
