package omok.model.fixture

import omok.model.Color
import omok.model.Color.BLACK
import omok.model.Color.WHITE

fun createPlayingBoard() =
    arrayOf(
        arrayOf<Color?>(null, null, null, null, null, null, null, null, null, null, null, null, null, null, null),
        arrayOf<Color?>(null, null, null, null, null, null, null, null, null, null, null, null, null, null, null),
        arrayOf<Color?>(null, null, null, null, null, null, null, null, null, null, null, null, null, null, null),
        arrayOf<Color?>(null, WHITE, WHITE, null, null, null, null, null, null, null, null, null, null, null, null),
        arrayOf<Color?>(null, null, WHITE, WHITE, null, null, null, null, null, null, null, null, null, null, null),
        arrayOf<Color?>(null, WHITE, null, WHITE, null, null, null, null, null, null, null, null, null, null, null),
        arrayOf<Color?>(null, null, null, null, null, WHITE, null, null, null, null, null, null, null, null, null),
        arrayOf<Color?>(null, null, null, null, null, null, null, null, null, null, null, null, null, null, null),
        arrayOf<Color?>(null, null, null, null, null, null, null, null, null, null, null, null, null, null, null),
        arrayOf<Color?>(null, null, null, null, null, null, null, null, null, null, null, null, null, null, null),
        arrayOf<Color?>(null, null, null, BLACK, null, BLACK, null, null, null, null, null, null, null, null, null),
        arrayOf<Color?>(null, null, null, BLACK, BLACK, null, null, null, null, null, null, null, null, null, null),
        arrayOf<Color?>(null, null, null, BLACK, BLACK, BLACK, BLACK, null, null, null, null, null, null, null, null),
        arrayOf<Color?>(null, null, null, BLACK, null, null, null, null, null, null, null, null, null, null, null),
        arrayOf<Color?>(null, null, null, BLACK, null, null, null, null, null, null, null, null, null, null, null),
    )