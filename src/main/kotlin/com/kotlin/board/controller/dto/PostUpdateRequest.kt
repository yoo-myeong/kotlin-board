package com.kotlin.board.controller.dto

import com.kotlin.board.service.dto.PostUpdateRequestDto

data class PostUpdateRequest(
    val title: String,
    val content: String,
    val updatedBy: String,
    val tags: List<String> = emptyList(),
)

fun PostUpdateRequest.toDto() =
    PostUpdateRequestDto(
        title = this.title,
        content = this.content,
        updatedBy = this.updatedBy,
        tags = tags,
    )
