import java.io.BufferedReader
import java.io.InputStreamReader

/**
 * 개똥벌레
 * https://www.acmicpc.net/problem/3020
 */
fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    // 동굴의 길이(장애물의 개수)와 높이
    val (n, h) = br.readLine().split(" ").map { it.toInt() }
    // 각 구간에 존재하는 석순과 종유석의 개수
    val bottomCount = IntArray(h)
    val topCount = IntArray(h)

    for (i in 0 until n) {
        // 장애물의 크기(편의상 구간을 0번째부터 취급하기 위해 -1)
        val size = br.readLine().toInt() - 1

        if (i % 2 == 0) {
            // 석순이면 0번째 구간 기준
            bottomCount[size]++
        } else {
            // 종유석이면 h - 1번째 구간 기준
            topCount[h - size - 1]++
        }
    }

    br.close()

    // 크기가 i + 1 이상인 장애물의 수를 구해야 하므로
    // 크기가 큰 장애물을 시작으로 누적 합 계산
    for (i in 1 until h) {
        bottomCount[h - i - 1] += bottomCount[h - i]
        topCount[i] += topCount[i - 1]
    }

    // 첫 번째 혹은 마지막 구간으로 날아가면
    // (동굴의 길이 / 2)개의 장애물을 파괴해야 함
    var min = n / 2
    var count = 0

    for (i in 0 until h) {
        // i번째 구간에서 파괴해야 하는 장애물의 개수
        val destroy = bottomCount[i] + topCount[i]

        if (destroy < min) {
            min = destroy
            count = 1
        } else if (destroy == min) {
            count++
        }
    }

    println("$min $count")
}
