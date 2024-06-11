package woowacourse.omok.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BoardTest {
    @Test
    fun `새로운 돌을 착수할 수 있다`() {
        val board = Board()
        val stone = Stone(Position(0, 0), Color.BLACK)
        val actual = board.place(stone)
        assertThat(actual.stones).isEqualTo(setOf(stone))
    }
}

data class Stone(
    val position: Position,
    val color: Color,
) : Comparable<Position> {
    override fun compareTo(other: Position): Int {
        return if (position == other) 0 else -1
    }
}

enum class Color {
    BLACK,
    WHITE,
}

data class Position(
    val row: Int,
    val column: Int,
)

data class Board(
    val stones: Set<Stone> = emptySet(),
) {
    fun place(stone: Stone): Board {
        return Board(stones + stone)
    }
}
