import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.StringTokenizer

/**
 * 주식
 * https://www.acmicpc.net/problem/11501
 */
fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    // 테스트 케이스 수
    val t = br.readLine().toInt()
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    for (i in 0 until t) {
        // 일수
        val n = br.readLine().toInt()
        val st = StringTokenizer(br.readLine())
        // 일별 주가
        val prices = IntArray(n)

        for (j in 0 until n) {
            prices[j] = st.nextToken().toInt()
        }

        bw.write("${solution(prices)}\n")
    }

    br.close()
    bw.flush()
    bw.close()
}

private fun solution(
    prices: IntArray,
): Long {
    // 일별 주가를 주가 낮은 순으로 정렬
    val mPrices = prices
        .mapIndexed { i, p -> intArrayOf(i, p) }
        .sortedBy { it[1] }
        .reversed()
    // 마지막 고점 날짜
    var lastHigh = -1
    // 총 이익
    // 답은 64bit 정수형으로 표현 가능하므로 Long 타입 사용
    var result = 0L

    for ((i, p) in mPrices) {
        // 마지막 고점 후부터 다음 고점(i번째 날) 전까지의 주식을 모두 구입하여 i번째 날 판매
        for (j in lastHigh + 1 until i) {
            result += p - prices[j]
        }

        lastHigh = i
    }

    return result
}
