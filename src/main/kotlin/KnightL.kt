package main.kotlin

import java.util.*
import kotlin.system.measureTimeMillis

fun bfs(board: Board, startPosition: Position): Int {
    val startNode = Node(position = startPosition)

    val bfsQueue: Queue<Node> = LinkedList()
    val visited: MutableSet<Node> = mutableSetOf()

    bfsQueue.add(startNode)
    visited.add(startNode)

    while (!bfsQueue.isEmpty()) {
        val currentNode = bfsQueue.remove()
        val currentNodePosition = currentNode.position
        val currentNodeRank = currentNode.rank

        if (board.isGoal(currentNodePosition)) {
            return currentNodeRank
        }
        val nonVisitedSuccessors = board.getSuccessors(currentNodePosition)
            .filter {
                !visited.any { visitedNode -> it == visitedNode.position }
            }.filter {
                !visited.any { visitedNode -> it.transpose() == visitedNode.position }
            }

        nonVisitedSuccessors.forEach {
            val successorNode = Node(position = it, rank = currentNodeRank + 1)
            visited.add(successorNode)
            bfsQueue.add(successorNode)
        }
    }
    return -1
}

fun main() {
    val board = Board(10, 1, 1)
    val startPosition = Position(0, 0)
    println(measureTimeMillis {
        println(bfs(board, startPosition))
    } / 1000f)
}