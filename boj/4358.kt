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

    val result = mutableListOf<Item>()

    for ((k, v) in species) {
        result.add(Item(k, v / count * 100))
    }

    // 각 종의 이름에 따라 사전순 정렬
    result.sortBy { it.specie }
    println(result.joinToString("\n"))
}

private data class Item(
    val specie: String,
    val percentage: Float
) {
    override fun toString(): String {
        // 각 종의 이름과 비율을 소수점 4번째자리까지 반올림한 값
        return "$specie ${String.format("%.4f", percentage)}"
    }
}
