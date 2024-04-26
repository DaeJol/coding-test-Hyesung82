fun main() {
    val input = readlnOrNull()!!
    val bulb = StringBuilder(input)
    var count = 0

    // i번 전구는 i번 이후의 전구만 제어할 수 있으므로
    // 켜져 있는 전구를 순서대로 끄면 모든 전구를 끌 수 있음
    for (i in bulb.indices) {
        if (bulb[i] == 'Y') {
            val inter = i + 1
            var j = i

            while (j < bulb.length) {
                bulb[j] = if (bulb[j] == 'Y') 'N' else 'Y'
                j += inter
            }

            count++
        }
    }

    println(if (bulb.all { it == 'N' }) count else -1)
}

// 참고: https://nahwasa.com/entry/%EB%B0%B1%EC%A4%80-12927-%EC%9E%90%EB%B0%94-%EB%B0%B0%EC%88%98-%EC%8A%A4%EC%9C%84%EC%B9%98-BOJ-12927-JAVA
