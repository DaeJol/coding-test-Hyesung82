/**
 * 소인수분해
 * https://www.acmicpc.net/problem/11653
 */
fun main() {
    val n = readln().toInt()

    println(factor(n).joinToString("\n"))
}

private fun factor(
    n: Int
): List<Int> {
    // 소수
    var pn = 2
    var num = n
    val result = mutableListOf<Int>()

    while (pn <= num) {
        if (num % pn == 0) {
            result.add(pn)
            num /= pn
        } else {
            // pn이 3에서 4로 증가하는 경우 4는 소수가 아니지만
            // 앞서 2로 나눌 수 없을 때까지 나눴기 때문에 4로 나누어 떨어지지 않음
            // 마찬가지로 소수가 아닌 수들은 모두 나누어 떨어질 수 없으므로 결과에 포함되지 않음
            pn++
        }
    }

    return result
}
