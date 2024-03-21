package omok.model

import lib.ark.ArkFourFourRule
import lib.ark.ArkOverLineRule
import lib.ark.ArkThreeThreeRule
import omok.mapper.toArkOmokBoard
import omok.mapper.toArkOmokPoint
import omok.model.search.AscendingDfs
import omok.model.search.DescendingDfs
import omok.model.search.HorizontalDfs
import omok.model.search.VerticalDfs

class Board(
    stones: List<Stone> = emptyList(),
) {
    val status: Array<Array<Color?>> = Array(ARRAY_SIZE) { Array(ARRAY_SIZE) { null } }

    var stones: List<Stone> = stones.toList()
        private set

    fun place(position: Position): GameResult? {
        require(position !in stones.map { it.position }) {
            EXCEPTION_DUPLICATED_POSITION
        }
        val stonesCount = stones.size
        val row = ARRAY_SIZE - position.row.value
        val col = position.col.value
        when (isEven(stonesCount)) {
            true -> {
                addStone(row, position.col.title, Color.BLACK, position)
                if (calculateSearchResult(row, col, Color.BLACK)) {
                    return GameResult.WINNER_BLACK
                }
            }

            false -> {
                addStone(row, position.col.title, Color.WHITE, position)
                if (calculateSearchResult(row, col, Color.WHITE)) {
                    return GameResult.WINNER_WHITE
                }
            }
        }
        if (stones.size >= BOARD_SIZE * BOARD_SIZE) return GameResult.DRAW
        return null
    }

    private fun addStone(
        row: Int,
        col: Char,
        color: Color,
        position: Position,
    ) {
        if (color == Color.BLACK) {
            val arkBoard = status.toArkOmokBoard()
            val arkPoint = Position.of(row, col).toArkOmokPoint()
            val isNotFourFour = ArkFourFourRule.validate(arkBoard, arkPoint).not()
            val isNotThreeThree = ArkThreeThreeRule.validate(arkBoard, arkPoint).not()
            val isNotJangMok = ArkOverLineRule.validate(arkBoard, arkPoint).not()
            val isPlacementAvailable = isNotFourFour && isNotThreeThree && isNotJangMok
            if (isPlacementAvailable) {
                status[row][Column.valueOf(col)?.value ?: return] = color
                stones = stones.plus(Stone.Black(Position.of(position.row.value, position.col.title)))
            } else {
                throw IllegalArgumentException(EXCEPTION_FORBIDDEN_PLACEMENT)
            }
        } else {
            status[row][Column.valueOf(col)?.value ?: return] = color
            stones = stones.plus(Stone.White(Position.of(position.row.value, position.col.title)))
        }
    }

    private fun calculateSearchResult(
        row: Int,
        col: Int,
        color: Color,
    ): Boolean {
        val verticalCount = VerticalDfs(status).apply { search(color, row, col) }.count
        val horizontalCount = HorizontalDfs(status).apply { search(color, row, col) }.count
        val ascendingCount = AscendingDfs(status).apply { search(color, row, col) }.count
        val descendingCount = DescendingDfs(status).apply { search(color, row, col) }.count
        return listOf(verticalCount, horizontalCount, ascendingCount, descendingCount).any { it >= 5 }
    }

    private fun isEven(num: Int): Boolean {
        return num % ODD_EVEN_INDICATOR == 0
    }

    companion object {
        private const val EXCEPTION_DUPLICATED_POSITION = "중복된 곳에 착수할 수 없습니다."
        private const val EXCEPTION_FORBIDDEN_PLACEMENT = "금수인 위치입니다."
        private const val ODD_EVEN_INDICATOR = 2
        private const val ARRAY_SIZE = 16
        private const val BOARD_SIZE = ARRAY_SIZE - 1
    }
}
