import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.LinkedList
import java.util.Queue
import java.util.StringTokenizer

/**
 * 치즈
 * https://www.acmicpc.net/problem/2638
 */
private const val INNER = -1
private const val OUTER = 0
private const val CHEESE = 1
private val dy = listOf(-1, 1, 0, 0)
private val dx = listOf(0, 0, -1, 1)

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val (n, m) = br.readLine().split(" ").map { it.toInt() }
    val grid = Array(n) { IntArray(m) } // 모눈종이
    val spaces = mutableListOf<IntArray>() // 치즈 내부 공간의 시작 좌표 후보
    val cheese: Queue<IntArray> = LinkedList() // 치즈의 녹지 않은 부분의 좌표

    for (i in 0 until n) {
        val st = StringTokenizer(br.readLine())

        for (j in 0 until m) {
            val input = st.nextToken().toInt()
            val curr = intArrayOf(i, j)

            if (input == CHEESE) {
                grid[i][j] = input
                cheese.add(curr)
            } else if (i in 1..<n && j in 1..<m &&
                grid[i - 1][j] == CHEESE &&
                grid[i][j - 1] == CHEESE) {
                // 모눈종이의 맨 가장자리가 아니면서
                // 치즈 내부 공간의 최상단 최좌측 좌표 후보(치즈가 없는 부분이면서 좌측과 상단에 치즈가 있음)
                spaces.add(curr)
            }
        }
    }

    br.close()
    println(solution(grid, spaces, cheese))
}

private fun solution(
    grid: Array<IntArray>,
    points: List<IntArray>,
    cheese: Queue<IntArray>
): Int {
    // 치즈 내부 공간의 한 점에서 시작하여 이웃한 빈 칸들을 치즈 내부 공간으로 표시
    for ((y, x) in points) {
        bfs(grid, y, x)
    }

    val n = grid.size
    val m = grid[0].size
    // 한 시간 후 녹아 없어질 치즈의 좌표
    val melting = mutableListOf<IntArray>()
    var time = 0

    while (cheese.isNotEmpty()) {
        // 현재 존재하는 치즈의 모든 부분을 순회
        val size = cheese.size

        for (i in 0 until size) {
            val curr = cheese.poll()
            val y = curr[0]
            val x = curr[1]
            // 실내온도의 공기(외부)와 접촉한 변의 수
            var count = 0

            // 상하좌우로 외부와 접촉하는지 검사
            for (j in 0..3) {
                val ny = y + dy[j]
                val nx = x + dx[j]

                if (ny in 0 until n &&
                    nx in 0 until m &&
                    grid[ny][nx] == OUTER) {
                    count++
                }
            }

            if (count >= 2) {
                // 두 변 이상이 외부와 접촉하면 녹아 없어짐
                melting.add(curr)
            } else {
                // 녹지 않은 부분은 다시 큐에 삽입
                cheese.add(curr)
            }
        }

        // 녹은 부분 표시
        for ((y, x) in melting) {
            grid[y][x] = OUTER
            // (y, x) 칸의 치즈가 녹으면서 외부로 드러난 칸 갱신
            update(grid, y, x)
        }

        time++
    }

    return time
}

/**
 * 치즈 내부 공간 표시
 */
private fun bfs(
    grid: Array<IntArray>,
    sy: Int,
    sx: Int
) {
    val q: Queue<IntArray> = LinkedList<IntArray>().apply {
        add(intArrayOf(sy, sx))
    }
    val n = grid.size
    val m = grid[0].size
    val visited = Array(n) { BooleanArray(m) }.apply {
        this[sy][sx] = true
    }
    val result = mutableListOf<IntArray>()

    while (q.isNotEmpty()) {
        val curr = q.poll()
        val y = curr[0]
        val x = curr[1]

        result.add(curr)

        for (i in 0..3) {
            val ny = y + dy[i]
            val nx = x + dx[i]

            if (ny in 0 until n &&
                nx in 0 until m &&
                grid[ny][nx] == OUTER &&
                visited[ny][nx].not()) {
                // 맨 가장자리를 포함하고 있으면 치즈 내부 공간일 수 없음
                if (y == 0 || y == n - 1 || x == 0 || x == m - 1) {
                    result.clear()
                    return
                }

                visited[ny][nx] = true
                q.add(intArrayOf(ny, nx))
            }
        }
    }

    for ((y, x) in result) {
        grid[y][x] = INNER
    }
}

/**
 * 치즈가 녹아서 내부에 있던 공간이 외부 공기와 접촉하게 된 경우 업데이트
 */
private fun update(
    grid: Array<IntArray>,
    sy: Int,
    sx: Int
) {
    val q: Queue<IntArray> = LinkedList<IntArray>().apply {
        // 녹은 칸 (sy, sx)를 시작으로 이웃한 칸들을 업데이트
        add(intArrayOf(sy, sx))
    }
    val n = grid.size
    val m = grid[0].size

    while (q.isNotEmpty()) {
        val curr = q.poll()
        val y = curr[0]
        val x = curr[1]

        for (i in 0..3) {
            val ny = y + dy[i]
            val nx = x + dx[i]

            if (ny in 0 until n &&
                nx in 0 until m &&
                grid[ny][nx] == INNER) {
                // 치즈 내부 공간이었던 칸을 외부로 표시
                grid[ny][nx] = OUTER
                q.add(intArrayOf(ny, nx))
            }
        }
    }
}
