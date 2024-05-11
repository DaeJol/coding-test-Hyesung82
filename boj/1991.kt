import java.io.BufferedReader
import java.io.InputStreamReader

/**
 * 트리 순회
 * https://www.acmicpc.net/problem/1991
 */
fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val n = br.readLine().toInt()
    val nodes = Array(n) { Node('A' + it) }

    for (i in 0 until n) {
        val input = br.readLine()
        val curr = input[0]
        val left = input[2]
        val right = input[4]
        val node = nodes[curr - 'A']

        if (left != '.') {
            node.left = nodes[left - 'A'].apply {
                parent = node
            }
        }

        if (right != '.') {
            node.right = nodes[right - 'A'].apply {
                parent = node
            }
        }
    }

    br.close()

    val root = nodes[0]

    println(traversePreorder(root))
    println(traverseInorder(root))
    println(traversePostorder(root))
}

/**
 * 전위 순회
 */
private fun traversePreorder(
    root: Node
): String {
    val result = StringBuilder()
    val left = root.left
    val right = root.right

    result.append(root.name)

    if (left != null) {
        result.append(traversePreorder(left))
    }

    if (right != null) {
        result.append(traversePreorder(right))
    }

    return result.toString()
}

/**
 * 중위 순회
 */
private fun traverseInorder(
    root: Node
): String {
    val result = StringBuilder()
    val left = root.left
    val right = root.right

    if (left != null) {
        result.append(traverseInorder(left))
    }

    result.append(root.name)

    if (right != null) {
        result.append(traverseInorder(right))
    }

    return result.toString()
}

/**
 * 후위 순회
 */
private fun traversePostorder(
    root: Node
): String {
    val result = StringBuilder()
    val left = root.left
    val right = root.right

    if (left != null) {
        result.append(traversePostorder(left))
    }

    if (right != null) {
        result.append(traversePostorder(right))
    }

    result.append(root.name)

    return result.toString()
}

private class Node(
    val name: Char,
    var parent: Node? = null,
    var left: Node? = null,
    var right: Node? = null
)
