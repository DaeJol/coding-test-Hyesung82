import java.io.BufferedReader
import java.io.InputStreamReader

/**
 * N과 M (10)
 * https://www.acmicpc.net/problem/15664
 */
private lateinit var nums: List<Int>
private val arrs = mutableSetOf<String>()

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val (n, m) = br.readLine().split(" ").map { it.toInt() }

    // 오름차순 정렬
    nums = br.readLine()
        .split(" ")
        .map { it.toInt() }
        .sorted()

    br.close()

    dfs(IntArray(m))

    // 사전 순으로 증가하도록 정렬
    val answer = arrs.sortedBy {
        it.split(" ")
            // 주어지는 수가 10,000 미만이므로 네 자리 문자열로 변환해서 비교
            .joinToString("") { num -> String.format("%4s0", num) }
    }

    println(answer.joinToString("\n"))
}

private fun dfs(
    arr: IntArray,
    numIndex: Int = 0,
    arrIndex: Int = 0
) {
    if (arrIndex == arr.size) {
        // 중복 확인(Set)을 위해 문자열로 변환
        arrs.add(arr.joinToString(" "))
        return
    }

    // 비내림차순
    for (i in numIndex until nums.size) {
        arr[arrIndex] = nums[i]
        dfs(arr, i + 1, arrIndex + 1)
    }
}
