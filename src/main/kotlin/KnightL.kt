package main.kotlin

import java.util.*
import kotlin.system.measureTimeMillis

fun bfs(board: Board, startPosition: Position): Node? {
    val startNode = Node(position = startPosition)

    val bfsQueue: Queue<Node> = LinkedList()
    val visited: MutableSet<Node> = mutableSetOf()

    bfsQueue.add(startNode)
    visited.add(startNode)

    while (!bfsQueue.isEmpty()) {
        val currentNode = bfsQueue.remove()
        val currentNodePosition = currentNode.position

        if (board.isGoal(currentNodePosition)) {
            return currentNode
        }
        val nonVisitedSuccessors = board.getSuccessors(currentNodePosition)
            .filter {
                !visited.any { visitedNode -> it == visitedNode.position }
            }.filter {
                !visited.any { visitedNode -> it.transpose() == visitedNode.position }
            }

        nonVisitedSuccessors.forEach {
            val successorNode = Node(position = it, parent = currentNode)
            visited.add(successorNode)
            bfsQueue.add(successorNode)
        }
    }
    return null
}

fun countNumberOfSteps(finishNode: Node?): Int {
    if (finishNode == null)
        return -1

    var count = 0
    var currentNode: Node? = finishNode
    while (currentNode?.parent != null) {
        count++
        println(currentNode.position)
        currentNode = currentNode.parent
    }
    return count
}

fun main() {
    val board = Board(10, 11, 1)
    val startPosition = Position(0, 0)
    println(measureTimeMillis {
        val finalNode = bfs(board, startPosition)
        println(countNumberOfSteps(finalNode))
    } / 1000f)
}