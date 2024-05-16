import java.io.BufferedReader
import java.io.InputStreamReader

/**
 * 생태학
 * https://www.acmicpc.net/problem/4358
 */
fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    // 나무 종 별 개수
    val species = HashMap<String, Int>()
    // 나무 전체 개수
    var count = 0f

    while (true) {
        val specie = br.readLine()

        if (specie.isNullOrBlank()) break

        species[specie] = (species[specie] ?: 0) + 1
        count++
    }

    br.close()

    // 각 종의 이름을 사전순 정렬
    val result = species.keys.sorted()

    println(
        result.joinToString("\n") {
            // 각 종의 이름과 각 종의 비율을 소수점 4번째자리까지 반올림한 값
            "$it ${String.format("%.4f", (species[it] ?: 0) / count * 100)}"
        }
    )
}
