import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.StringTokenizer

/**
 * 내려가기
 * https://www.acmicpc.net/problem/2096
 */
private const val INF = Integer.MAX_VALUE
private const val SIZE = 3

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val n = br.readLine().toInt()
    val table = Array(n + 1) { IntArray(SIZE + 1) }

    for (i in 1..n) {
        val st = StringTokenizer(br.readLine())

        table[i][1] = st.nextToken().toInt()
        table[i][2] = st.nextToken().toInt()
        table[i][3] = st.nextToken().toInt()
    }

    br.close()

    // maxDp[i][j]: (i, j) 위치까지 내려갔을 때 최대 점수
    // minDp[i][j]: (i, j) 위치까지 내려갔을 때 최소 점수
    // 예외처리가 쉽도록 각 줄의 왼쪽과 오른쪽에 한 칸씩 추가
    val maxDp = Array(n + 1) { IntArray(SIZE + 2) }
    val minDp = Array(n + 1) { IntArray(SIZE + 2) }

    // 임의로 추가한 칸이 최소 점수 계산에 포함되지 않도록 무한대로 초기화
    for (i in 1..n) {
        minDp[i][0] = INF
        minDp[i][4] = INF
    }

    for (i in 1..n) {
        for (j in 1..3) {
            maxDp[i][j] = listOf(
                maxDp[i - 1][j - 1], // 바로 위의 수의 왼쪽에 붙어 있는 수
                maxDp[i - 1][j], // 바로 위의 수
                maxDp[i - 1][j + 1] // 바로 위의 수의 오른쪽에 붙어 있는 수
            ).max() + table[i][j]

            minDp[i][j] = listOf(
                minDp[i - 1][j - 1],
                minDp[i - 1][j],
                minDp[i - 1][j + 1]
            ).min() + table[i][j]
        }
    }

    println("${maxDp[n].max()} ${minDp[n].min()}")
}
