import kotlin.math.max

/**
 * 문자열 교환
 * https://www.acmicpc.net/problem/1522
 */
fun main() {
    val str = readln()
    val len = str.length
    val count = str.count { it == 'a' }

    // 연속하는 a를 만들기 위해 a의 총 개수만큼의 크기의 윈도우 사용
    var start = 0
    var end = count - 1
    var curr = str.substring(start, end + 1).count { it == 'a' }
    // 고정된 윈도우 안에 최대한 많은 a가 들어오는 경우 탐색
    var max = curr

    while (true) {
        if (str[start] == 'a') {
            curr--
        }

        // 윈도우를 오른쪽으로 한 칸 이동
        // 문자열의 처음과 끝이 서로 인접해 있음
        start = (start + 1) % len
        end = (end + 1) % len

        if (str[end] == 'a') {
            curr++
        }

        // 윈도우가 한 바퀴를 모두 돌았으면 종료
        if (start == 0) {
            break
        }

        max = max(max, curr)
    }

    println(count - max)
}

// 참고: https://velog.io/@kksshh0612/%EB%B0%B1%EC%A4%80-1522-%EB%AC%B8%EC%9E%90%EC%97%B4-%EA%B5%90%ED%99%98-java
