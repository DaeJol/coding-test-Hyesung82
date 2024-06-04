/**
 * 모음사전
 * https://school.programmers.co.kr/learn/courses/30/lessons/84512
 */
class Solution {
    private val vowels = listOf('A', 'E', 'I', 'O', 'U')
    private var answer = 0

    fun solution(word: String): Int {
        return dfs(word)
    }

    /**
     * 사전의 모든 단어를 순서대로 탐색하여 target이 몇 번째인지 반환
     * target이 curr로 시작하지 않거나 사전에 존재하지 않으면 -1 반환
     */
    private fun dfs(
        target: String,
        curr: String = ""
    ): Int {
        if (curr == target) {
            return answer
        }

        // 사전에 길이 5 이하의 단어가 수록되어 있으므로
        // 현재 단어의 길이가 5이면 curr로 시작하는 다음 단어를 탐색하지 않음
        if (curr.length == 5) {
            return -1
        }

        for (i in vowels) {
            // 새로운 단어를 탐색할 때마다 answer + 1
            answer++

            val res = dfs(target, curr + i)
            
            if (res != -1) {
                return res
            }
        }

        return -1
    }
}
