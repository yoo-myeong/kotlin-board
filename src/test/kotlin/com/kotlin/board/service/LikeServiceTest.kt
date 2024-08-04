package com.kotlin.board.service

import com.kotlin.board.domain.Post
import com.kotlin.board.exception.PostNotFoundException
import com.kotlin.board.repository.LikeRepository
import com.kotlin.board.repository.PostRepository
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.extensions.testcontainers.perSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.repository.findByIdOrNull
import org.testcontainers.containers.GenericContainer

@SpringBootTest
class LikeServiceTest(
    private val likeService: LikeService,
    private val likeRepository: LikeRepository,
    private val postRepository: PostRepository,
) : BehaviorSpec({
        val redisContainer = GenericContainer<Nothing>("redis:5.6")
        beforeSpec {
            redisContainer.portBindings.add("16379:6379")
            redisContainer.start()
            listener(redisContainer.perSpec())
        }
        afterSpec {
            redisContainer.stop()
        }
        given("종아요 생성시") {
            postRepository.save(Post(createdBy = "mocha", title = "title", content = "content"))
            When("인풋이 정상적으로 들어오면") {
                likeService.createLike(1L, "mocha")
                then("좋아요가 정상적으로 생성됨을 확인한다.") {
                    val like = likeRepository.findByIdOrNull(1L)
                    like shouldNotBe null
                    like?.createdBy shouldBe "mocha"
                }
            }
            When("게시글이 존재하지 않으면") {
                then("존재하지 않는 게시글 예외가 발생한다.") {
                    shouldThrow<PostNotFoundException> { likeService.createLike(9999L, "mocha") }
                }
            }
        }
    })
