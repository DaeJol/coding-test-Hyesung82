import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

/**
 * 십자뒤집기
 * https://www.acmicpc.net/problem/10472
 */
private const val SIZE = 3
private const val BLACK = '*'
private const val WHITE = '.'

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val p = br.readLine().toInt()
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    for (i in 0 until p) {
        val board = Array(SIZE) { CharArray(SIZE) }

        for (r in 0 until SIZE) {
            board[r] = br.readLine().toCharArray()
        }

        bw.write("${dfs(board)}\n")
    }

    br.close()
    bw.flush()
    bw.close()
}

/**
 * board를 target 형태로 바꾸는 데 필요한 최소 클릭 횟수 반환
 */
private fun dfs(
    target: Array<CharArray>,
    board: Array<CharArray> = Array(SIZE) { CharArray(SIZE) { WHITE } },
    index: Int = 0,
    count: Int = 0
): Int {
    if (board.isSameAs(target)) {
        return count
    }

    for (i in index until SIZE * SIZE) {
        val y = i / SIZE
        val x = i % SIZE

        board.click(y, x)

        val res = dfs(target, board, i + 1, count + 1)

        if (res >= 0) return res

        board.click(y, x)
    }

    return -1
}

/**
 * 입력으로 주어진 보드의 형태로 바뀌었으면 true 반환
 */
private fun Array<CharArray>.isSameAs(
    other: Array<CharArray>
): Boolean {
    for (i in 0 until SIZE) {
        for (j in 0 until SIZE) {
            if (this[i][j] != other[i][j]) {
                return false
            }
        }
    }

    return true
}

/**
 * (r, c) 칸 클릭
 */
private fun Array<CharArray>.click(
    r: Int,
    c: Int
) {
    // 클릭한 칸과 동서남북 네 칸
    val dy = listOf(0, 0, 0, 1, -1)
    val dx = listOf(0, 1, -1, 0, 0)

    for (d in 0 until 5) {
        val y = r + dy[d]
        val x = c + dx[d]

        if (y in this.indices && x in this[y].indices) {
            // 검은색 -> 흰색, 흰색 -> 검은색
            this[y][x] = if (this[y][x] == BLACK) WHITE else BLACK
        }
    }
}
