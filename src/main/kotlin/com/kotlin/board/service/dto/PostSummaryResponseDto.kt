package com.kotlin.board.service.dto

import com.kotlin.board.domain.Post
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl

data class PostSummaryResponseDto(
    val id: Long,
    val title: String,
    val createdBy: String,
    val createdAt: String,
    val firstTag: String? = null,
)

fun Page<Post>.toSummaryResponseDto() =
    PageImpl(
        content.map { it.toSummaryResponseDto() },
        pageable,
        totalElements,
    )

fun Post.toSummaryResponseDto() =
    PostSummaryResponseDto(
        id = this.id,
        title = this.title,
        createdBy = this.createdBy,
        createdAt = this.createdAt.toString(),
        firstTag = tags.firstOrNull()?.name,
    )
