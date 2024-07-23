package com.kotlin.board

import com.kotlin.board.domain.Post
import com.kotlin.board.exception.PostNotDeletableException
import com.kotlin.board.exception.PostNotFoundException
import com.kotlin.board.exception.PostNotUpdatableException
import com.kotlin.board.repository.PostRepository
import com.kotlin.board.service.PostService
import com.kotlin.board.service.dto.PostCreateRequestDto
import com.kotlin.board.service.dto.PostUpdateRequestDto
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.comparables.shouldBeGreaterThan
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.repository.findByIdOrNull

@SpringBootTest
class PostServiceTest(
    private val postService: PostService,
    private val postRepository: PostRepository,
) : BehaviorSpec({
        given("게시글 생성시") {
            When("게시글 인풋이 정상적으로 들어오면") {
                val postId =
                    postService.createPost(
                        PostCreateRequestDto(
                            title = "title",
                            content = "content",
                            createdBy = "mocha",
                        ),
                    )
                then("게시글이 정상적으로 생성됨을 확인한다") {
                    postId shouldBeGreaterThan 0L
                    val post = postRepository.findByIdOrNull(postId)
                    post shouldNotBe null
                    post?.title shouldBe "title"
                    post?.content shouldBe "content"
                    post?.createdBy shouldBe "mocha"
                }
            }
        }
        given("게시글 수정시") {
            val saved = postRepository.save(Post(title = "title", content = "content", createdBy = "mocha"))
            When("정상 수정시") {
                val updatedId =
                    postService.updatePost(
                        saved.id,
                        PostUpdateRequestDto(
                            title = "update title",
                            content = "update content",
                            updatedBy = "mocha",
                        ),
                    )
                then("게시글이 정상적으로 수정됨을 확인한다") {
                    saved.id shouldBe updatedId
                    val updated = postRepository.findByIdOrNull(updatedId)
                    updated shouldNotBe saved
                    updated?.title shouldBe "update title"
                    updated?.content shouldBe "update content"
                    updated?.updatedBy shouldBe "mocha"
                }
            }
            When("게시글이 없을 때") {
                then("게시글을 찾을 수 없다는 예외가 발생한다.") {
                    shouldThrow<PostNotFoundException> {
                        postService.updatePost(
                            9999L,
                            PostUpdateRequestDto(
                                title = "update title",
                                content = "update content",
                                updatedBy = "mocha",
                            ),
                        )
                    }
                }
            }
            When("작성자가 동일하지 않으면") {
                then("수정할 수 없는 게시물 입니다 예외가 발생한다.") {
                    shouldThrow<PostNotUpdatableException> {
                        postService.updatePost(
                            1L,
                            PostUpdateRequestDto(
                                title = "update title",
                                content = "update content",
                                updatedBy = "update mocha",
                            ),
                        )
                    }
                }
            }
        }
        given("게시글 삭제시") {
            val saved = postRepository.save(Post(title = "title", content = "content", createdBy = "mocha"))
            When("정상 삭제시") {
                val postId = postService.deletePost(saved.id, "mocha")
                then("게시글이 정상적으로 삭제됨을 확인한다") {
                    postId shouldBe saved.id
                    postRepository.findByIdOrNull(postId) shouldBe null
                }
            }
            val saved2 = postRepository.save(Post(title = "title", content = "content", createdBy = "mocha"))
            When("작성자가 동일하지 않으면 ") {
                then("삭제할 수 없는 게시물입니다 예외가 발생한다") {
                    shouldThrow<PostNotDeletableException> { postService.deletePost(saved2.id, "mocha2") }
                }
            }
        }
    })
