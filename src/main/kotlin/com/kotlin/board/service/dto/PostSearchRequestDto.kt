package com.kotlin.board.service.dto

data class PostSearchRequestDto(
    val title: String? = null,
    val createdBy: String? = null,
)
