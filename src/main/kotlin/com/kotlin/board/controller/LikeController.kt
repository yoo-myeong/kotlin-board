package com.kotlin.board.controller

import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class LikeController {
    @PostMapping("/posts/{postId}/likes")
    fun createLike(
        @PathVariable("postId") postId: String,
        @RequestParam createdBy: String,
    ): Long = 1L
}
