import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

/**
 * 여행 가자
 * https://www.acmicpc.net/problem/1976
 */
fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val n = br.readLine().toInt() // 도시의 수
    val m = br.readLine().toInt() // 여행 계획에 속한 도시들의 수
    val plan = IntArray(m)
    // 연결된 도시들을 집합으로 표현
    // 각 도시가 속한 집합의 root(대표값)를 자기 자신으로 초기화
    val root = IntArray(n + 1) { it }

    for (i in 1..n) {
        val st = StringTokenizer(br.readLine())

        for (j in 1..n) {
            if (st.nextToken().toInt() == 1) {
                // i번 도시와 j번 도시가 연결되어 있으면 union
                union(root, i, j)
            }
        }
    }

    val st = StringTokenizer(br.readLine())

    for (i in 0 until m) {
        plan[i] = st.nextToken().toInt()
    }

    br.close()
    // 여행 계획에 속한 모든 도시들이 한 집합에 속하면(root가 같으면) 여행 계획이 가능함
    println(if (plan.map { root[it] }.all { it == root[plan[0]] }) "YES" else "NO")
}

/**
 * a와 b가 속한 집합 병합 -> 집합의 root(대표값)를 같게 만들기
 */
private fun union(
    root: IntArray,
    a: Int,
    b: Int
) {
    val aRoot = find(root, a)
    val bRoot = find(root, b)

    // 동일한 집합에 포함된 값이 다른 root를 갖지 않도록
    // 집합에서 가장 작은 값을 root로 설정
    if (aRoot < bRoot) {
        root[bRoot] = aRoot
    } else {
        root[aRoot] = bRoot
    }
}

/**
 * a가 포함된 집합의 root(대표값)를 갱신하면서 찾기
 */
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
