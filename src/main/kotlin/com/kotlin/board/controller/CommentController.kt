package com.kotlin.board.controller

import com.kotlin.board.controller.dto.CommentCreateRequest
import com.kotlin.board.controller.dto.CommentUpdateRequest
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class CommentController {
    @PostMapping("posts/{postId}/comments")
    fun createComment(
        @PathVariable("postId") postId: Long,
        @RequestBody commentCreatedRequest: CommentCreateRequest,
    ): Long {
        println(commentCreatedRequest.content)
        println(commentCreatedRequest.createdBy)
        return 1L
    }

    @PutMapping("comments/{commentId}")
    fun updateComment(
        @PathVariable("commentId") commentId: Long,
        @RequestBody commentUpdateRequest: CommentUpdateRequest,
    ): Long {
        println(commentUpdateRequest.content)
        println(commentUpdateRequest.updatedBy)
        return 1L
    }

    @DeleteMapping("comments/{commentId}")
    fun deleteComment(
        @PathVariable("commentId") commentId: Long,
        @RequestParam("deletedBy") deletedBy: String,
    ): Long {
        println(commentId)
        return commentId
    }
}
