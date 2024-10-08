package com.kotlin.board.controller

import com.kotlin.board.controller.dto.PostCreateRequest
import com.kotlin.board.controller.dto.PostDetailResponse
import com.kotlin.board.controller.dto.PostSearchRequest
import com.kotlin.board.controller.dto.PostSummaryResponse
import com.kotlin.board.controller.dto.PostUpdateRequest
import com.kotlin.board.controller.dto.toDto
import com.kotlin.board.controller.dto.toResponse
import com.kotlin.board.service.PostService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class PostController(
    private val postService: PostService,
) {
    @PostMapping("/posts")
    fun createPost(
        @RequestBody postCreateRequest: PostCreateRequest,
    ): Long = postService.createPost(postCreateRequest.toDto())

    @PutMapping("/posts/{id}")
    fun updatePost(
        @PathVariable id: Long,
        @RequestBody updateRequest: PostUpdateRequest,
    ): Long = postService.updatePost(id, updateRequest.toDto())

    @DeleteMapping("/posts/{id}")
    fun deletePost(
        @PathVariable id: Long,
        @RequestParam createdBy: String,
    ): Long = postService.deletePost(id, createdBy)

    @GetMapping("/posts/{id}")
    fun getPost(
        @PathVariable id: Long,
    ): PostDetailResponse = postService.getPost(id).toResponse()

    @GetMapping("/posts")
    fun getPosts(
        pageable: Pageable,
        postSearchRequest: PostSearchRequest,
    ): Page<PostSummaryResponse> = postService.findPageBy(pageable, postSearchRequest.toDto()).toResponse()
}
