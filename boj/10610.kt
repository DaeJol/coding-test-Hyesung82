/**
 * 30
 * https://www.acmicpc.net/problem/10610
 */
fun main() {
    // 숫자들을 섞어 만들어야 함 -> 조합 X, 모든 숫자를 사용해야 함
    val n = readln().map { it - '0' }
        // 가장 큰 수를 만들어야 함 -> 높은 자릿수에 큰 수가 오도록 내림차순 정렬
        .sortedWith(reverseOrder())
    // 모든 자릿수의 합이 3의 배수이면 3의 배수
    // 3의 배수이면서 1의 자릿수가 0이면 30의 배수
    val answer = if (n.last() == 0 && n.sum() % 3 == 0) n.joinToString("") else -1

    println(answer)
}
