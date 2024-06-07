import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import kotlin.math.max
import kotlin.math.min
import kotlin.math.pow

/**
 * 소수를 분수로
 * https://www.acmicpc.net/problem/5376
 */
fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val t = br.readLine().toInt()
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    repeat(t) {
        val decimal = br.readLine()
        bw.write(decimal.toFraction() + "\n")
    }

    br.close()
    bw.flush()
    bw.close()
}

private fun String.toFraction(): String {
    val s = substring(2).split("(")
    // 소수점 아래 괄호로 감싸져있지 않은 수
    val left = s[0]
    // left를 나타내는 분모와 분자
    val lDivisor = 10.0.pow(left.len).toInt()
    val lDividend = if (left.isBlank()) 0 else left.toInt()
    // right를 나타내는 분자와 분모
    var rDividend = 0
    var rDivisor = 1

    // 무한소수 0.AAA...를 분수로 표현하는 방법
    // -> A / (10^(패턴 A의 길이) - 1)
    if (s.size > 1) {
        // 괄호로 감싸져있는 수
        val right = s[1].substring(0, s[1].lastIndex)

        rDividend = right.toInt()
        rDivisor = 10.0.pow(right.length).toInt() - 1
    }

    // 앞서 right 분모를 구하는 과정에서 left 분모를 곱해야 하지만
    // 통분 과정에서 나누어 떨어지므로(+ overflow를 피하기 위해)
    // right 분자에 left 분모를 곱하는 과정과 함께 생략
    // 10^6과 10^9는 각각 Int로 표현할 수 있지만 둘을 곱한 값은 Long으로 표현해야 함
    val dividend = lDividend * rDivisor.toLong() + rDividend
    val divisor = lDivisor * rDivisor.toLong()
    val gcd = gcd(divisor, dividend)

    return "${dividend / gcd}/${divisor / gcd}"
}

private fun gcd(
    a: Long,
    b: Long
): Long {
    var min = min(a, b)
    var max = max(a, b)

    while (true) {
        if (min == 0L) return max

        val temp = min
        min = max % min
        max = temp
    }
}

// 참고: https://measurezero.tistory.com/679
