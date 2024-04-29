import java.util.*

/**
 * 이항 계수 2
 * https://www.acmicpc.net/problem/11051
 */
private const val DIVISOR = 10_007

fun main() {
    val sc = Scanner(System.`in`)
    val n = sc.nextInt()
    val k = sc.nextInt()

    sc.close()
    println(combination(n, k))
}

/**
 * 파스칼의 법칙
 * nCk = n-1Ck + n-1Ck-1
 */
private fun combination(
    n: Int,
    k: Int,
    // 파스칼의 삼각형에서 각 행의 첫 번째 칸과 마지막 칸은 항상 1이므로 해당 칸을 1로 초기화
    dp: Array<IntArray> = Array(n + 1) { i ->
        IntArray(n + 1) { j ->
            if (j == 0 || i == j) 1 else 0
        }
    }
): Int {
    // nCk가 초기화된 경우 값 반환
    if (dp[n][k] > 0) {
        return dp[n][k]
    }

    // nCk가 초기화되지 않은 경우 파스칼의 법칙을 이용하여 재귀적으로 계산
    dp[n][k] = (combination(n - 1, k, dp) + combination(n - 1, k - 1, dp)) % DIVISOR

    return dp[n][k]
}
