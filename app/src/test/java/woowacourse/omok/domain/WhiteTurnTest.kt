package woowacourse.omok.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class WhiteTurnTest {
    @Test
    fun `백돌이 착수를 진행하면 다음 순서는 흑돌이 된다`() {
        val actual = Turn.WhiteTurn.rotate()
        assertThat(actual).isInstanceOf(Turn.BlackTurn::class.java)
    }
}
