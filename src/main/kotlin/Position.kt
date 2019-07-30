package main.kotlin

data class Position(val x: Int, val y: Int) {
    fun isValid(maxSize: Int): Boolean = x >= 0 && y >= 0 && x < maxSize && y < maxSize

    fun addDelta(positionDelta: Position): Position = copy(x = x + positionDelta.x, y = y + positionDelta.y)

    fun transpose(): Position = copy(x = y, y = x)
}