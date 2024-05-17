import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*
import kotlin.math.max
import kotlin.math.min

/**
 * 최대공약수
 * https://www.acmicpc.net/problem/2824
 */
private const val DIVISOR = 1_000_000_000

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val n = br.readLine().toInt()
    val a = LongArray(n)
    val st1 = StringTokenizer(br.readLine())
    val m = br.readLine().toInt()
    val b = LongArray(m)
    val st2 = StringTokenizer(br.readLine())

    br.close()

    for (i in 0 until n) {
        a[i] = st1.nextToken().toLong()
    }

    for (i in 0 until m) {
        b[i] = st2.nextToken().toLong()
    }

    println(solution(a, b))
}

private fun solution(
    a: LongArray,
    b: LongArray
): String {
    // result가 9자리보다 긴지 구분하는 플래그
    var overflow = false
    var result = 1L

    // A와 B의 모든 약수 쌍에 대해 최대공약수 계산
    for (i in a.indices) {
        for (j in b.indices) {
            val gcd = gcd(a[i], b[j])

            // 최종 최대공약수에 포함
            result *= gcd
            // 사용된 약수 제거
            a[i] /= gcd
            b[j] /= gcd

            // result가 9자리보다 길면 플래그 변경하고 9자리가 되도록 나머지 연산 수행
            if (result >= DIVISOR) {
                overflow = true
                result %= DIVISOR
            }
        }
    }

    return if (overflow) {
        // result가 원래 9자리보다 길면 9자리가 되도록 소실된 0을 덧붙임
        String.format("%09d", result)
    } else {
        result.toString()
    }
}

/**
 * a와 b의 최대공약수
 */
private fun gcd(
    a: Long,
    b: Long
): Long {
    val min = min(a, b)
    val max = max(a, b)

    return if (max % min == 0L) min else gcd(max % min, min)
}
