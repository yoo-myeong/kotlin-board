package com.kotlin.board.controller

import com.kotlin.board.controller.dto.CommentCreateRequest
import com.kotlin.board.controller.dto.CommentUpdateRequest
import com.kotlin.board.controller.dto.toDto
import com.kotlin.board.service.CommentService
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class CommentController(
    val commentService: CommentService,
) {
    @PostMapping("posts/{postId}/comments")
    fun createComment(
        @PathVariable("postId") postId: Long,
        @RequestBody commentCreatedRequest: CommentCreateRequest,
    ): Long = commentService.createdComment(postId, commentCreatedRequest.toDto())

    @PutMapping("comments/{commentId}")
    fun updateComment(
        @PathVariable("commentId") commentId: Long,
        @RequestBody commentUpdateRequest: CommentUpdateRequest,
    ): Long = commentService.updateComment(commentId, commentUpdateRequest.toDto())

    @DeleteMapping("comments/{commentId}")
    fun deleteComment(
        @PathVariable("commentId") commentId: Long,
        @RequestParam("deletedBy") deletedBy: String,
    ): Long = commentService.deleteComment(commentId, deletedBy)
}
