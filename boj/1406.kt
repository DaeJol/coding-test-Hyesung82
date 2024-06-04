import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

/**
 * 에디터
 * https://www.acmicpc.net/problem/1406
 */
// 커서를 기준으로 앞에 있는 문자(가장 앞에 있는 문자부터 push)
private val left = Stack<Char>()
// 커서를 기준으로 뒤에 있는 문자(가장 뒤에 있는 문자부터 push)
private val right = Stack<Char>()

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))

    left.addAll(br.readLine().toList())

    val m = br.readLine().toInt()

    for (i in 0 until m) {
        val command = br.readLine()

        when (command[0]) {
            'L' -> l()
            'D' -> d()
            'B' -> b()
            else -> p(command[2])
        }
    }

    br.close()
    println((left.toList() + right.toList().reversed()).joinToString(""))
}

/**
 * 커서를 왼쪽으로 한 칸 옮김
 */
private fun l() {
    if (left.isNotEmpty()) {
        right.push(left.pop())
    }
}

/**
 * 커서를 오른쪽으로 한 칸 옮김
 */
private fun d() {
    if (right.isNotEmpty()) {
        left.push(right.pop())
    }
}

/**
 * 커서 왼쪽에 있는 문자를 삭제함
 */
private fun b(){
    if (left.isNotEmpty()) {
        left.pop()
    }
}

/**
 * 문자 c를 커서 왼쪽에 추가함
 */
private fun p(c: Char) {
    left.push(c)
}
