/**
 * 풍선 터트리기
 * https://school.programmers.co.kr/learn/courses/30/lessons/68646
 */
class Solution {
    fun solution(a: IntArray): Int {
        val len = a.size
        // asc[i]: 풍선이 0번째부터 i번째까지 있을 때 i번째 풍선의 번호가 가장 작은지
        val asc = BooleanArray(len).also { it[0] = true }
        // desc[i]: 풍선이 len - 1번째부터 i번째까지 있을 때 i번째 풍선의 번호가 가장 작은지
        val desc = BooleanArray(len).also { it[len - 1] = true }

        var min = a[0]

        // 오름차순으로 탐색
        for (i in 1 until len) {
            if (a[i] < min) {
                asc[i] = true
                min = a[i]
            }
        }

        min = a[len - 1]

        // 내림차순으로 탐색
        for (i in len - 2 downTo 1) {
            if (a[i] < min) {
                desc[i] = true
                min = a[i]
            }
        }

        // asc[i] == true일 때
        // i - 1번째부터 0번째 풍선을 i번째 풍선과 비교하여 모두 터트리고
        // 나머지 풍선도 최소값이 써진 풍선과 비교하여 터트린 후
        // 마지막으로 i번째 풍선과 최소값이 써진 풍선 중 번호가 더 작은 풍선을 터트림
        // desc[i] == true일 때는 방향을 바꿔서 똑같이 동작
        return (0 until len).count {
            asc[it] || desc[it]
        }
    }
}
