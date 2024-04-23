/**
 * 미로 탈출 명령어
 * https://school.programmers.co.kr/learn/courses/30/lessons/150365
 */
import java.util.*

private const val IMPOSSIBLE = "impossible"

class Solution {
    // 네 방향을 사전 순으로 탐색 -> 하좌우상
    private val dir = listOf('d', 'l', 'r', 'u')
    private val dx = listOf(1, 0, 0, -1)
    private val dy = listOf(0, -1, 1, 0)

    fun solution(n: Int, m: Int, x: Int, y: Int, r: Int, c: Int, k: Int): String {
        return bfs(n, m, k, x, y, r, c)
    }

    private fun bfs(
        n: Int,
        m: Int,
        k: Int,
        sx: Int,
        sy: Int,
        ex: Int,
        ey: Int
    ): String {
        // dp[x][y]: (x, y)에 사전 순으로 가장 빠른 방법으로 도달한 경로
        val dp = Array(n + 1) { Array(m + 1) { "" } }
        val q: Queue<Item> = LinkedList<Item>().apply {
            add(Item(sx, sy, ""))
        }

        // i: 현재까지 이동한 거리
        for (i in 0..k) {
            val size = q.size

            for (j in 0 until size) {
                val curr = q.poll()
                val x = curr.x
                val y = curr.y
                val path = curr.path

                if (i == k) {
                    // k칸 이동해서 탈출 지점에 도착한 경우
                    if (x == ex && y == ey) {
                        return path
                    }

                    continue
                }

                // (x, y)에 도달할 수 있는 사전 순으로 더 빠른 경로가 이미 있는 경우
                if (dp[x][y].length == path.length && dp[x][y] < path) {
                    continue
                }

                dp[x][y] = path

                for (d in 0..3) {
                    val nx = x + dx[d]
                    val ny = y + dy[d]

                    if (nx !in 1..n || ny !in 1..m) {
                        continue
                    }

                    q.add(Item(nx, ny, path + dir[d]))
                }
            }
        }

        return IMPOSSIBLE
    }

    data class Item(val x: Int, val y: Int, val path: String)
}
