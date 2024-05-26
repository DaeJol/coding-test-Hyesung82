import java.util.StringTokenizer

/**
 * 한 줄로 서기
 * https://www.acmicpc.net/problem/1138
 */
fun main() {
    val n = readln().toInt()
    val heightAndCount = Array(n) { IntArray(2) }
    val st = StringTokenizer(readln())

    for (i in 0 until n) {
        heightAndCount[i] = intArrayOf(
            // i번째 사람의 키
            i + 1,
            // i번째 사람보다 왼쪽이고 키가 큰 사람의 수
            st.nextToken().toInt()
        )
    }

    println(
        solution(heightAndCount).joinToString(" ") { "${it[0]}" }
    )
}

private fun solution(
    heightAndCount: Array<IntArray>
): List<IntArray> {
    // i번째 사람의 왼쪽에 선 사람이 i번째 사람보다 큰 사람 수(heightAndCount[i][1])보다 많아야 하므로
    // 왼쪽 사람 중 자기보다 큰 사람 수를 기준으로 오름차순 정렬
    val result = heightAndCount.sortedBy { it[1] }.toMutableList()

    for (i in result.indices) {
        val temp = result[i]
        val (height, count) = temp
        var taller = 0
        var index = i

        for (j in 0 until i) {
            if (result[j][0] > height) {
                // i번째 사람보다 왼쪽에 있고 키가 큰 사람 수 카운트
                taller++

                // i번째 사람보다 오른쪽에 있어야 하는 사람을 찾으면 index로 표시
                if (taller == count + 1) {
                    index = j
                    break
                }
            }
        }

        // i번째 사람을 index번째 사람의 왼쪽으로 이동
        result.removeAt(i)
        result.add(index, temp)
    }

    return result
}
