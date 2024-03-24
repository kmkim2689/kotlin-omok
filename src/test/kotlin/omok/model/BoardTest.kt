package omok.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class BoardTest {
    @Test
    fun `이미 돌이 있는 자리에 착수를 진행하면, 예외를 발생시킨다`() {
        // given
        val stones =
            listOf(
                Stone.Black(Position(HorizontalCoordinate.ONE, VerticalCoordinate.A)),
                Stone.White(Position(HorizontalCoordinate.TEN, VerticalCoordinate.C)),
            )
        val board = Board(stones)
        assertThrows<IllegalArgumentException> {
            board.place(Position(HorizontalCoordinate.ONE, VerticalCoordinate.A))
        }
    }

    @Test
    fun `플레이어가 번갈아가며 착수하게 한다`() {
        // given
        val board = Board()
        // when
        board.place(Position(HorizontalCoordinate.TEN, VerticalCoordinate.B))
        board.place(Position(HorizontalCoordinate.ONE, VerticalCoordinate.A))
        // then
        assertThat(board.stones.last().color).isEqualTo(Color.WHITE)
    }
}
