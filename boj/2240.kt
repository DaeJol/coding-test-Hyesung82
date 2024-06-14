import java.io.BufferedReader
import java.io.InputStreamReader
import kotlin.math.max

/**
 * 자두나무
 * https://www.acmicpc.net/problem/2240
 */
fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val (t, w) = br.readLine().split(" ").map { it.toInt() }
    // dp[i][j][k]: i초일 때 j번째 나무에 아래에 있고 남은 이동 횟수가 k번일 때 받을 수 있는 자두의 수
    val dp = Array(t) { Array(2) { Array(w + 1) { 0 } } }

    for (i in 0 until t) {
        // 자두가 떨어지는 나무의 번호
        // 계산 편의 상 0번, 1번으로 표시
        val n = br.readLine().toInt() - 1
        // 자두가 떨어지지 않는 나무의 번호
        val m = (n + 1) % 2

        if (i == 0) {
            if (n == 0) {
                // 처음 위치(0번 자두나무 아래)에서 자두를 받는 경우
                dp[i][n][w] = 1
            } else {
                // 다른 나무 아래로 움직여서 자두를 받는 경우
                dp[i][n][w - 1] = 1
            }
        } else {
            for (j in 0..w) {
                // 기존 위치에서 자두를 받는 경우
                dp[i][n][j] = dp[i - 1][n][j] + 1
                // 기존 위치에서 자두를 받지 않는 경우
                dp[i][m][j] = dp[i - 1][m][j]

                if (j > 0) {
                    // 다른 나무 아래로 움직여서 자두를 받는 경우
                    dp[i][n][j - 1] = max(dp[i][n][j - 1], dp[i - 1][m][j] + 1)
                }
            }
        }
    }

    br.close()
    // T초가 지난 후 마지막 위치와 남은 이동 횟수에 관계 없이 가장 많은 자두를 받는 경우 출력
    println(dp[t - 1].flatten().max())
}

// 참고: https://1004dustkd.tistory.com/entry/C-%EB%B0%B1%EC%A4%80BOJ-2240-%EC%9E%90%EB%91%90%EB%82%98%EB%AC%B4
