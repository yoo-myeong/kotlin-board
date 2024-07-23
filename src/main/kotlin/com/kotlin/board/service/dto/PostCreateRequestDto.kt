package com.kotlin.board.service.dto

import com.kotlin.board.domain.Post

data class PostCreateRequestDto(
    val title: String,
    val content: String,
    val createdBy: String,
)

fun PostCreateRequestDto.toEntity() =
    Post(
        title = title,
        content = content,
        createdBy = createdBy,
    )
