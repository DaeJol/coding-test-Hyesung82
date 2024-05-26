import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*
import kotlin.math.max
import kotlin.math.min

/**
 * 이진 검색 트리
 * https://www.acmicpc.net/problem/5639
 */
fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val root = Node(br.readLine().toInt()) // 트리의 루트 노드
    var curr = root
    val stack = Stack<Node>()

    while (true) {
        val input = br.readLine()

        if (input.isNullOrBlank()) break

        val num = input.toInt()
        val node = Node(num)

        // 전위 순회 순서대로 노드 삽입
        if (num < curr.key) {
            // 삽입할 키가 curr의 키보다 작으면 왼쪽 자식 노드로 설정
            curr.left = node.apply { parent = curr }
            // 이후에 오른쪽 자식 노드도 채울 수 있도록 stack에 저장
            stack.add(curr)
        } else {
            var prev1 = curr

            while (stack.isNotEmpty()) {
                val prev2 = stack.peek()
                val min = min(prev1.key, prev2.key)
                val max = max(prev1.key, prev2.key)

                // 삽입할 키가 curr(prev1)의 키값과 curr의 부모 노드(prev2)의 키값 사이의 값이면
                // curr(prev1)의 오른쪽 자식 노드로 설정
                if (num in min..max) {
                    prev1.right = node.apply { parent = prev1 }
                    break
                }

                // 사이의 값이 아니면 상위 레벨로 이동
                prev1 = stack.pop()
            }

            // stack의 모든 노드의 키값이 삽입될 키값보다 작으면
            // 오른쪽 자식이 없는 노드(stack에 있던 노드) 중 가장 상단 노드의 오른쪽 자식으로 설정
            if (stack.isEmpty()) {
                prev1.right = node.apply { parent = prev1 }
            }
        }

        curr = node
//        println("${curr.parent?.key} ${curr.parent?.left?.key} ${curr.parent?.right?.key}")
    }

    br.close()
    postOrder(root)
}

/**
 * 후위 순회(왼쪽-오른쪽-루트)
 */
private fun postOrder(
    root: Node,
) {
    // 왼쪽 자식 노드
    root.left?.let {
        postOrder(it)
    }
    // 오른쪽 자식 노드
    root.right?.let {
        postOrder(it)
    }
    // 루트 노드
    println(root.key)
}

private data class Node(
    val key: Int,
    var parent: Node? = null,
    var left: Node? = null,
    var right: Node? = null
)
