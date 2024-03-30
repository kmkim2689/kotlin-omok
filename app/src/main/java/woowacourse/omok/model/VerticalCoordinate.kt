package woowacourse.omok.model

enum class VerticalCoordinate(val index: Int) {
    A(1),
    B(2),
    C(3),
    D(4),
    E(5),
    F(6),
    G(7),
    H(8),
    I(9),
    J(10),
    K(11),
    L(12),
    M(13),
    N(14),
    O(15),
    ;

    companion object {
        fun valueOf(verticalCoordinate: Int): VerticalCoordinate {
            return entries.find {
                it.index == verticalCoordinate
            } ?: throw IllegalArgumentException()
        }
    }
}