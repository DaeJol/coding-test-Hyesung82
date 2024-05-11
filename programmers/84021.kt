import java.util.*
import kotlin.math.*

/**
 * 퍼즐 조각 채우기
 * https://school.programmers.co.kr/learn/courses/30/lessons/84021
 */
class Solution {
    private val dy = listOf(-1, 1, 0, 0)
    private val dx = listOf(0, 0, -1, 1)
    // 좌표 집합의 좌표들을 순서대로 비교할 수 있도록 일정한 정렬 기준 적용
    private val comp = Comparator<IntArray>() { o1, o2 ->
        if (o1[0] < o2[0] || o1[0] == o2[0] && o1[1] < o2[1]) -1
        else if (o1[0] == o2[0] && o1[1] == o2[1]) 0
        else 1
    }

    fun solution(game_board: Array<IntArray>, table: Array<IntArray>): Int {
        var answer: Int = 0
        // 게임 보드의 빈 공간을 표현하는 좌표 집합, 연속된 빈칸을 List<IntArray>로 표현
        val spaces = getSpaces(game_board)
        // 테이블에 놓인 퍼즐 조각을 표현하는 좌표 집합, 조각 하나를 List<IntArray>로 표현
        val pieces = getPieces(table)

        // 빈 공간에 각 퍼즐 조각이 맞는지 확인
        spaces.forEach {
            for (i in pieces.indices) {
                val piece = pieces[i]

                if (isFit(it, piece)) {
                    // 채워 넣은 퍼즐 조각 제거
                    pieces.removeAt(i)
                    answer += it.size
                    break
                }
            }
        }

        return answer
    }

    private fun getSpaces(
        board: Array<IntArray>
    ): List<List<IntArray>> {
        val result = mutableListOf<List<IntArray>>()
        val n = board.size
        val visited = Array(n) { BooleanArray(n) }

        for (i in 0 until n) {
            for (j in 0 until n) {
                if (board[i][j] == 1 || visited[i][j]) continue

                result.add(getSpace(board, i, j, visited))
            }
        }

        return result
    }

    /**
     * board에서 (sy, sx)를 포함하는 빈 공간(연속된 빈칸)을 좌표 집합 형태로 반환
     */
    private fun getSpace(
        board: Array<IntArray>,
        sy: Int,
        sx: Int,
        visited: Array<BooleanArray>
    ): List<IntArray> {
        val n = board.size
        val q: Queue<IntArray> = LinkedList<IntArray>().apply {
            add(intArrayOf(sy, sx))
        }
        val coords = mutableListOf<IntArray>()

        while (q.isNotEmpty()) {
            val (y, x) = q.poll()

            if (visited[y][x]) continue

            coords.add(intArrayOf(y, x))
            visited[y][x] = true

            for (i in 0..3) {
                val ny = y + dy[i]
                val nx = x + dx[i]

                if (ny !in 0 until n || nx !in 0 until n ||
                    board[ny][nx] == 1) {
                    continue
                }

                q.add(intArrayOf(ny, nx))
            }
        }

        coords.sortWith(comp)

        return coords
    }

    private fun getPieces(
        table: Array<IntArray>
    ): MutableList<Array<List<IntArray>>> {
        val result = mutableListOf<Array<List<IntArray>>>()
        val n = table.size
        val visited = Array(n) { BooleanArray(n) }

        for (i in 0 until n) {
            for (j in 0 until n) {
                if (table[i][j] == 0 || visited[i][j]) continue

                // 퍼즐 조각을 회전시킨 모든 상태 저장
                val pieces = Array(4) { listOf<IntArray>() }

                pieces[0] = getPiece(table, i, j, visited)

                for (r in 1..3) {
                    pieces[r] = rotated(n, pieces[0], r)
                }

                result.add(pieces)
            }
        }

        return result
    }

    /**
     * table에서 (sy, sx)를 포함하는 조각을 구성하는 좌표 집합 반환
     */
    private fun getPiece(
        table: Array<IntArray>,
        sy: Int,
        sx: Int,
        visited: Array<BooleanArray>
    ): List<IntArray> {
        val n = table.size
        val q: Queue<IntArray> = LinkedList<IntArray>().apply {
            add(intArrayOf(sy, sx))
        }
        val coords = mutableListOf<IntArray>()

        while (q.isNotEmpty()) {
            val (y, x) = q.poll()

            if (visited[y][x]) continue

            coords.add(intArrayOf(y, x))
            visited[y][x] = true

            for (i in 0..3) {
                val ny = y + dy[i]
                val nx = x + dx[i]

                if (ny !in 0 until n || nx !in 0 until n ||
                    table[ny][nx] == 0) {
                    continue
                }

                q.add(intArrayOf(ny, nx))
            }
        }

        coords.sortWith(comp)

        return coords
    }

    /**
     * rotation에 따라 시계 방향으로 90도, 180도, 270도 회전시킨 좌표 집합 반환
     */
    private fun rotated(
        n: Int,
        coords: List<IntArray>,
        rotation: Int
    ): List<IntArray> {
        val result = mutableListOf<IntArray>()

        coords.forEach {
            val (i, j) = it

            when (rotation) {
                1 -> result.add(intArrayOf(j, n - 1 - i))
                2 -> result.add(intArrayOf(n - 1 - i, n - 1 - j))
                3 -> result.add(intArrayOf(n - 1 - j, i))
            }
        }

        result.sortWith(comp)

        return result
    }

    private fun isFit(
        space: List<IntArray>,
        piece: Array<List<IntArray>>
    ): Boolean {
        if (space.size != piece[0].size) return false

        for (curr in piece) {
            // 빈 공간과 퍼즐 조각의 최상단 좌측 좌표를 기준으로 offset 계산
            val yDiff = space[0][0] - curr[0][0]
            val xDiff = space[0][1] - curr[0][1]

            var isFit = true

            // 모든 좌표를 순서대로 비교
            for (i in space.indices) {
                val s = space[i]
                val p = curr[i]

                if (s[0] != (p[0] + yDiff) || s[1] != (p[1] + xDiff)) {
                    isFit = false
                    break
                }
            }

            if (isFit) return true
        }

        return false
    }
}
