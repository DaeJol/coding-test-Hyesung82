/**
 * 혼자 놀기의 달인
 * https://school.programmers.co.kr/learn/courses/30/lessons/131130
 */
class Solution {
    fun solution(cards: IntArray): Int {
        // root[i]: i번 상자가 속한 그룹, 처음에는 i번 그룹에 i번 상자만 있음
        val root = IntArray(cards.size) { it }
        // count[i]: i번 그룹에 포함된 상자의 수, 처음에는 각 그룹에 상자가 하나씩 있음
        val count = IntArray(cards.size) { 1 }

        for (i in cards.indices) {
            // 편의상 상자 번호는 0부터 시작하도록 함 -> (카드에 적힌 숫자) - 1
            val j = cards[i] - 1

            // i번 상자와 j번 상자를 한 그룹으로 병합
            union(root, count, i, j)
        }

        // 각 그룹에 포함된 상자 수를 내림차순 정렬
        count.sortDescending()

        // 가장 많은 상자를 포함한 그룹 두 개의 상자 수를 곱하면 최고 점수가 됨
        return count[0] * count[1]
    }

    private fun union(
        root: IntArray,
        count: IntArray,
        a: Int,
        b: Int
    ) {
        // a번 상자가 속한 그룹
        val aRoot = find(root, a)
        // b번 상자가 속한 그룹
        val bRoot = find(root, b)

        if (aRoot < bRoot) {
            root[bRoot] = aRoot
            // b번 상자가 속한 그룹의 상자 개수를 a번 상자가 속한 그룹에 포함시킴
            count[aRoot] += count[bRoot]
            count[bRoot] = 0
        } else if (bRoot < aRoot) {
            root[aRoot] = bRoot
            // a번 상자가 속한 그룹의 상자 개수를 b번 상자가 속한 그룹에 포함시킴
            count[bRoot] += count[aRoot]
            count[aRoot] = 0
        }
    }

    private fun find(
        root: IntArray,
        a: Int
    ): Int {
        if (a == root[a]) {
            return a
        }

        root[a] = find(root, root[a])

        return root[a]
    }
}
