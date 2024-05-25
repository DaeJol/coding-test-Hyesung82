import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

/**
 * 보이는 점의 개수
 * https://www.acmicpc.net/problem/2725
 */
private const val MAX = 1000

fun main() {
    // dp[n]: 0 <= x,y <= n일 때 원점에서 보이는 좌표의 개수
    val dp = solution()
    val br = BufferedReader(InputStreamReader(System.`in`))
    val c = br.readLine().toInt()
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    for (i in 1..c) {
        val n = br.readLine().toInt()
        bw.write("${dp[n] + 1}\n")
    }

    br.close()
    bw.flush()
    bw.close()
}

private fun solution(): IntArray {
    val dp = IntArray(MAX + 1)

    for (x in 1..MAX) {
        // 누적 합
        dp[x] = dp[x - 1]

        // 보이는 점의 위치는 선분 y = x를 기준으로 대칭이므로
        // 선분 y = x 아래에 있는 점에 대해서만 확인 후 +2
        for (y in 0 until x) {
            // 기울기가 y/x인 선분 위의 점 중 원점과 가장 가까운 점이면
            // (x와 y의 최대공약수가 1이면)
            // 보이는 점으로 카운트
            if (gcd(x, y) == 1) dp[x] += 2
        }
    }

    return dp
}

/**
 * a와 b의 최대공약수
 */
private fun gcd(
    a: Int, // 큰 값
    b: Int // 작은 값
): Int = if (b == 0) a else gcd(b, a % b)

// 참고: https://www.acmicpc.net/board/view/45028
