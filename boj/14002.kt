import java.util.*
import kotlin.math.max

/**
 * 가장 긴 증가하는 부분 수열 4
 * https://www.acmicpc.net/problem/14002
 */
fun main() {
    val n = readln().toInt()
    val st = StringTokenizer(readln())
    val arr = IntArray(n)

    for (i in 0 until n) {
        arr[i] = st.nextToken().toInt()
    }

    // dp[i]: i번째 수까지 있을 때 i번째 수를 포함하는 가장 긴 증가하는 부분 수열의 길이
    val dp = IntArray(n) { 1 }

    for (i in 1 until n) {
        for (j in i - 1 downTo 0) {
            // arr[i]보다 작은 수로 구성된 부분 수열을 찾아서 arr[i]를 포함시킨 길이로 업데이트
            if (arr[i] > arr[j]) dp[i] = max(dp[i], dp[j] + 1)
        }
    }

    // 부분 수열에 포함시켜야 하는 수의 개수
    var count = dp.max()
    // 수열의 가장 마지막 값에 해당하는 수의 인덱스
    var index = -1

    for (i in 0 until n) {
        if (dp[i] == count) {
            index = i
            break
        }
    }

    val result = mutableListOf(arr[index])

    count--

    for (i in index - 1 downTo 0) {
        if (count == 0) break

        // 현재 수열의 첫 번째 값보다 작으면서 부분 수열에서 count번째에 해당하는 수 찾기
        if (arr[i] < result[0] && dp[i] == count) {
            result.add(0, arr[i])
            count--
        }
    }

    println("${result.size}\n${result.joinToString(" ")}")
}
