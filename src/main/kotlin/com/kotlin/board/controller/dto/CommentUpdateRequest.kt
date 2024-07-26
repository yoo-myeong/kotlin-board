package com.kotlin.board.controller.dto

import com.kotlin.board.service.dto.CommentUpdateRequestDto

data class CommentUpdateRequest(
    val content: String,
    val updatedBy: String,
)

fun CommentUpdateRequest.toDto() =
    CommentUpdateRequestDto(
        content = content,
        updatedBy = updatedBy,
    )
