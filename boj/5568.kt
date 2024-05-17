import java.io.BufferedReader
import java.io.InputStreamReader

/**
 * 카드 놓기
 * https://www.acmicpc.net/problem/5568
 */
fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    // 총 카드 수
    val n = br.readLine().toInt()
    // 선택할 카드 수
    val k = br.readLine().toInt()
    val cards = Array(n) { "" }

    for (i in 0 until n) {
        cards[i] = br.readLine()
    }

    br.close()
    println(solution(cards, k))
}

private fun solution(
    cards: Array<String>,
    k: Int
): Int {
    val nums = mutableSetOf<String>()

    dfs(nums, cards, k)

    return nums.size
}

private fun dfs(
    // 카드를 선택해서 만든 정수 집합
    nums: MutableSet<String>,
    cards: Array<String>,
    // 선택할 카드 수
    k: Int,
    // 현재까지 선택한 카드를 나열하여 만든 정수
    curr: String = "",
    // 이미 사용한 카드 표시
    visited: BooleanArray = BooleanArray(cards.size)
) {
    if (k == 0) {
        nums.add(curr)
        return
    }

    for (i in cards.indices) {
        if (visited[i].not()) {
            visited[i] = true
            dfs(nums, cards, k - 1, curr + cards[i], visited)
            visited[i] = false
        }
    }
}
