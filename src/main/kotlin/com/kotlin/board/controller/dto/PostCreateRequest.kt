package com.kotlin.board.controller.dto

import com.kotlin.board.service.dto.PostCreateRequestDto

data class PostCreateRequest(
    val title: String,
    val content: String,
    val createdBy: String,
    val tags: List<String> = emptyList(),
)

fun PostCreateRequest.toDto() =
    PostCreateRequestDto(
        title = this.title,
        content = this.content,
        createdBy = this.createdBy,
    )
