import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

/**
 * N과 M (2)
 * https://www.acmicpc.net/problem/15650
 */
private val bw = BufferedWriter(OutputStreamWriter(System.out))

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val (n, m) = br.readLine().split(" ").map { it.toInt() }

    br.close()

    dfs(n, m)

    bw.flush()
    bw.close()
}

private fun dfs(
    n: Int,
    m: Int,
    curr: Int = 1,
    index: Int = 0,
    arr: IntArray = IntArray(m)
) {
    if (index == m) {
        bw.write(arr.joinToString(" ") + "\n")
        return
    }

    for (i in curr..n) {
        arr[index] = i
        dfs(n, m, i + 1, index + 1, arr)
    }
}
