package woowacourse.omok.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BlackTurnTest {
    @Test
    fun `흑돌이 착수를 진행하면 다음 순서는 백돌이 된다`() {
        val actual = Turn.BlackTurn.rotate()
        assertThat(actual).isInstanceOf(Turn.WhiteTurn::class.java)
    }
}

sealed class Turn() {
    abstract fun rotate(): Turn

    object BlackTurn : Turn() {
        override fun rotate(): Turn {
            return WhiteTurn
        }
    }

    object WhiteTurn : Turn() {
        override fun rotate(): Turn {
            return BlackTurn
        }
    }
}

/**
 * Board
 * * 책임 : 착수 정보 보유하면서 Rule에 승패 결정 요청
 * Stone
 * Position
 * Turn
 * * 책임 : 착수 진행, 교대
 * Rule
 * * 책임 : 승/패 결정, 착수 가능 여부
 */
