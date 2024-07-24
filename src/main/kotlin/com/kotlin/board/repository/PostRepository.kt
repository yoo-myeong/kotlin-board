package com.kotlin.board.repository

import com.kotlin.board.domain.Post
import com.kotlin.board.domain.QPost.post
import com.kotlin.board.service.dto.PostSearchRequestDto
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport

interface PostRepository :
    JpaRepository<Post, Long>,
    CustomPostRepository

interface CustomPostRepository {
    fun findPageBy(
        pagerRequest: Pageable,
        postSearchRequestDto: PostSearchRequestDto,
    ): Page<Post>
}

class CustomPostRepositoryImpl :
    QuerydslRepositorySupport(Post::class.java),
    CustomPostRepository {
    override fun findPageBy(
        pagerRequest: Pageable,
        postSearchRequestDto: PostSearchRequestDto,
    ): Page<Post> {
        val result =
            from(post)
                .where(
                    postSearchRequestDto.title?.let { post.title.contains(it) },
                    postSearchRequestDto.createdBy?.let { post.createdBy.contains(it) },
                ).orderBy(post.createdAt.desc())
                .offset(pagerRequest.offset)
                .limit(pagerRequest.pageSize.toLong())
                .fetchResults()

        return PageImpl(result.results, pagerRequest, result.total)
    }
}
