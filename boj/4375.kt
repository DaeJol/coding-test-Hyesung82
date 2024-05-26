import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

/**
 * 1
 * https://www.acmicpc.net/problem/4375
 */
fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    while (true) {
        val input = br.readLine()

        if (input.isNullOrBlank()) break

        val n = input.toInt()

        bw.write("${solution(n)}\n")
    }

    br.close()
    bw.flush()
    bw.close()
}

private fun solution(
    n: Int
): Int {
    var r = 0 // 1...을 n으로 나눈 나머지
    var i = 1 // 전체 자리수

    while (true) {
        // 앞서 계산된 나머지에 1을 덧붙임(자리수 늘림)
        val temp = r * 10 + 1

        r = temp % n

        // 나누어 떨어지면 전체 자리수 반환
        if (r == 0) return i

        // 나누어 떨어지지 않으면 자리수를 늘림
        i++
    }
}
