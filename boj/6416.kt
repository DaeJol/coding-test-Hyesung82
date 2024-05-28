import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.StringTokenizer

/**
 * 트리인가?
 * https://www.acmicpc.net/problem/6416
 */
fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    // 각 노드의 진입 차수
    val inDegree = mutableMapOf<String, Int>()
    val graph = mutableMapOf<String, List<String>>()
    var st = StringTokenizer(br.readLine())
    var k = 1

    while (true) {
        if (st.hasMoreTokens().not()) {
            st = StringTokenizer(br.readLine())
            continue
        }

        // 정수쌍의 범위가 주어지지 않았으므로 String으로 표현
        val u = st.nextToken()
        val v = st.nextToken()

        // 입력의 끝
        if (u[0] == '-' && v[0] == '-') {
            break
        }

        // 테스트 케이스의 끝
        if (u[0] == '0' && v[0] == '0') {
            if (isTree(inDegree, graph)) {
                bw.write("Case $k is a tree.\n")
            } else {
                bw.write("Case $k is not a tree.\n")
            }

            inDegree.clear()
            graph.clear()
            k++
            continue
        }

        inDegree[v] = (inDegree[v] ?: 0) + 1
        graph[u] = (graph[u] ?: listOf()) + v
    }

    br.close()
    bw.flush()
    bw.close()
}

private fun isTree(
    inDegree: Map<String, Int>,
    graph: Map<String, List<String>>
): Boolean {
    // 노드의 개수가 0개
    if (graph.isEmpty()) return true

    // 노드의 개수가 1개 이상
    for (node in graph.keys) {
        // 루트 노드가 존재
        if (inDegree[node] == null) {
            val visited = mutableSetOf<String>()
            // 루트에서 다른 노드로 가는 경로는 반드시 가능하며 유일함
            // -> 순환하지 않고 루트에서 모든 노드를 방문할 수 있어야 함
            return isAcyclic(graph, visited, node) && visited.size == inDegree.size + 1
        }
    }

    return false
}

/**
 * graph가 순환하면 false, 순환하지 않으면 true 반환
 */
private fun isAcyclic(
    graph: Map<String, List<String>>,
    visited: MutableSet<String>,
    curr: String
): Boolean {
    if (visited.contains(curr)) {
        return false
    }

    visited.add(curr)

    for (next in graph[curr] ?: listOf()) {
        val res = isAcyclic(graph, visited, next)
        if (res.not()) return false
    }

    return true
}
