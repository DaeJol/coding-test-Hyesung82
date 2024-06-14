import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.StringTokenizer

/**
 * 동전
 * https://www.acmicpc.net/problem/9084
 */
fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val t = br.readLine().toInt()
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    repeat(t) {
        val n = br.readLine().toInt()
        val st = StringTokenizer(br.readLine())
        val coins = IntArray(n) {
            st.nextToken().toInt()
        }
        val target = br.readLine().toInt()

        bw.write("${solution(coins, target)}\n")
    }

    br.close()
    bw.flush()
    bw.close()
}

private fun solution(
    coins: IntArray,
    target: Int,
): Int {
    // dp[i]: 금액 i를 만드는 모든 방법의 수
    // 금액 0을 만드는 방법은 한 가지
    val dp = IntArray(target + 1).also { it[0] = 1 }

    for (coin in coins) {
        // 동전 coin원으로 금액 i를 만드는 방법의 수 계산
        for (i in coin..target) {
            /*
            동전 5원과 7원이 있을 때
            10원을 만드는 방법 dp[10] += dp[5]
            17원을 만드는 방법 dp[17] += dp[10]
            -> 현재 사용하려는 동전 7원을 사용 후
                앞서 계산한 동전 5원을 사용하여
                나머지 값(i - coin)을 만드는 방법의 수 덧셈
             */
            dp[i] += dp[i - coin]
        }
    }

    return dp[target]
}

// 참고: https://d-cron.tistory.com/23
