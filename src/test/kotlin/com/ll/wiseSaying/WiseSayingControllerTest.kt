package com.ll.wiseSaying

import com.ll.global.BeanConfig.wiseSayingRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class WiseSayingControllerTest {
    @BeforeEach
    fun setUp() {
        wiseSayingRepository.clear()
    }

    @Test
    fun 명언_생성() {
        val result = TestRunner.run(
            """
            등록
            현재를 사랑하라.
            작자미상
        """
        )

        assertThat(result).contains("명언 : ")
        assertThat(result).contains("작가 : ")
        assertThat(result).contains("1번 명언이 등록되었습니다.")
    }

    @Test
    fun 명언_목록() {
        val result = TestRunner.run(
            """
            등록
            현재를 사랑하라.
            작자미상
            등록
            과거에 집착하지 마라.
            작자미상
            목록
        """
        )

        assertThat(result).contains("1 / 작자미상 / 현재를 사랑하라.")
        assertThat(result).contains("2 / 작자미상 / 과거에 집착하지 마라.")
    }

    @Test
    fun 명언_삭제() {
        val result = TestRunner.run(
            """
            등록
            현재를 사랑하라.
            작자미상
            등록
            과거에 집착하지 마라.
            작자미상
            삭제?id=1
            목록
        """
        )

        assertThat(result).contains("1번 명언이 삭제되었습니다.")
        assertThat(result).doesNotContain("1 / 작자미상 / 현재를 사랑하라.")
        assertThat(result).contains("2 / 작자미상 / 과거에 집착하지 마라.")
    }

    @Test
    fun 명언_수정() {
        val result = TestRunner.run(
            """
            등록
            과거에 집착하지 마라.
            작자미상
            수정?id=1
            현재와 자신을 사랑하라.
            작자미상
            목록
        """
        )

        assertThat(result).doesNotContain("1 / 작자미상 / 과거에 집착하지 마라.")
        assertThat(result).contains("1 / 작자미상 / 현재와 자신을 사랑하라.")
    }

    @Test
    fun 빌드() {
        val result = TestRunner.run(
            """
            등록
            현재를 사랑하라.
            작자미상
            등록
            과거에 집착하지 마라.
            작자미상
            빌드
        """
        )

        assertThat(result)
            .contains("data.json 파일의 내용이 갱신되었습니다.");
    }
}