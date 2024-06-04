import java.util.StringTokenizer
import kotlin.math.max
import kotlin.math.min

/**
 * 최대공약수 하나 빼기
 * https://www.acmicpc.net/problem/14476
 */
fun main() {
    // 정수의 개수
    val n = readln().toInt()
    // n개의 수
    val nums = IntArray(n)
    val st = StringTokenizer(readln())

    for (i in 0 until n) {
        nums[i] = st.nextToken().toInt()
    }

    solution(nums).let {
        println(if (it[0] < 1) -1 else it.joinToString(" "))
    }
}

private fun solution(
    nums: IntArray
): IntArray {
    val n = nums.size
    // left[i]: nums[0]부터 nums[i]까지의 최대공약수
    val left = IntArray(n).apply {
        this[0] = nums[0]
    }
    // right[i]: nums[i]부터 nums[n - 1]까지의 최대공약수
    val right = IntArray(n).apply {
        this[n - 1] = nums[n - 1]
    }

    // 왼쪽부터 누적해서 계산
    for (i in 1 until n) {
        // (직전까지의 수들의 최대공약수)와 (현재 수)의 최대공약수 계산
        left[i] = gcd(left[i - 1], nums[i])
    }

    // 오른쪽부터 누적해서 계산
    for (i in n - 2 downTo 0) {
        // (직전까지의 수들의 최대공약수)와 (현재 수)의 최대공약수 계산
        right[i] = gcd(right[i + 1], nums[i])
    }

     // 최대공약수, 뺀 수
    val result = intArrayOf(0, 0)

    for (i in 0 until n) {
        // 뺀 수
        val k = nums[i]
        // 가장 왼쪽 혹은 오른쪽 수를 빼는 경우 연속된 누적 계산의 결과만 비교
        val a = if (i == 0) 0 else left[i - 1]
        val b = if (i == n - 1) 0 else right[i + 1]

        gcd(a, b).let {
            // k를 뺐을 때 나머지 수들의 최대공약수가
            // k의 약수가 아니면서 가장 큰 경우
            if (it > result[0] && k % it != 0) {
                result[0] = it
                result[1] = k
            }
        }
    }

    return result
}

private fun gcd(
    a: Int,
    b: Int
): Int {
    val min = min(a, b)
    val max = max(a, b)

    return if (min == 0) max else gcd(min, max % min)
}
