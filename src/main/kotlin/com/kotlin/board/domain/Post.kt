package com.kotlin.board.domain

import com.kotlin.board.exception.PostNotUpdatableException
import com.kotlin.board.service.dto.PostUpdateRequestDto
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
class Post(
    createdBy: String,
    title: String,
    content: String,
) : BaseEntity(createdBy) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0

    var title: String = title
        protected set
    var content: String = content
        protected set

    fun update(dto: PostUpdateRequestDto) {
        if (dto.updatedBy != this.createdBy) {
            throw PostNotUpdatableException()
        }
        this.title = dto.title
        this.content = dto.content
        super.updatedBy(dto.updatedBy)
    }
}
