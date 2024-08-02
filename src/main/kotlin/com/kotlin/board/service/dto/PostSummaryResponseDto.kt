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
    val likeCount: Long = 0,
)

fun Page<Post>.toSummaryResponseDto(countLike: (Long) -> Long) =
    PageImpl(
        content.map { it.toSummaryResponseDto(countLike) },
        pageable,
        totalElements,
    )

fun Post.toSummaryResponseDto(countLike: (Long) -> Long) =
    PostSummaryResponseDto(
        id = this.id,
        title = this.title,
        createdBy = this.createdBy,
        createdAt = this.createdAt.toString(),
        firstTag = tags.firstOrNull()?.name,
        likeCount = countLike(id),
    )
