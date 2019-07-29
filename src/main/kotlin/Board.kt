package main.kotlin

class Board(private val size: Int, deltaX: Int, deltaY: Int) {
    private val displacements: List<Position> = listOf(
        Position(deltaX, deltaY),
        Position(deltaX, -deltaY),
        Position(-deltaX, deltaY),
        Position(-deltaX, -deltaY),
        Position(deltaY, deltaX),
        Position(deltaY, -deltaX),
        Position(-deltaY, deltaX),
        Position(-deltaY, -deltaX)
    )

    fun isGoal(position: Position): Boolean {
        return position == Position(x = size - 1, y = size - 1)
    }

    fun getSuccessors(position: Position): List<Position> {
        return displacements
            .map { position.addDelta(it) }
            .filter { it.isValid(size) }
    }
}