package com.kotlin.board.service.dto

import com.kotlin.board.domain.Comment
import com.kotlin.board.domain.Post

data class CommentCreateRequestDto(
    val content: String,
    val createdBy: String,
)

fun CommentCreateRequestDto.toEntity(post: Post) =
    Comment(
        content = content,
        createBy = createdBy,
        post = post,
    )
