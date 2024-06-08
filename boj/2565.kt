import java.io.BufferedReader
import java.io.InputStreamReader
import kotlin.math.max

/**
 * 전깃줄
 * https://www.acmicpc.net/problem/2565
 */
fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val n = br.readLine().toInt()
    val wires = Array(n) { IntArray(2) }

    for (i in 0 until n) {
        wires[i] = br.readLine()
            .split(" ")
            .map { it.toInt() }
            .toIntArray()
    }

    br.close()
    // 전봇대 A에 연결되는 위치를 기준으로 정렬
    wires.sortBy { it[0] }

    // dp[i]: 전깃줄이 i번째까지 있을 때 전깃줄 i를 포함하면서
    // 전봇대 B에 연결되는 위치가 증가하는 가장 긴 부분수열의 길이
    val dp = IntArray(n) { 1 }

    for (i in 0 until n) {
        for (j in 0 until i) {
            if (wires[i][1] > wires[j][1]) {
                dp[i] = max(dp[i], dp[j] + 1)
            }
        }
    }

    // 전체 전깃줄 수에서 증가하는(교차하지 않는) 전깃줄의 수를 뺄셈
    println(n - dp.max())
}

// 참고: https://velog.io/@noion0511/PythonKotlin-%EB%B0%B1%EC%A4%80-2565%EB%B2%88-%EC%A0%84%EA%B9%83%EC%A4%84
