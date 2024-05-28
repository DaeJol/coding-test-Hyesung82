import java.util.*
import kotlin.math.pow

/**
 * 수학은 너무 쉬워
 * https://www.acmicpc.net/problem/2904
 */
fun main() {
    val n = readln().toInt()
    // 종이 N장에 적혀 있는 수
    val nums = IntArray(n)
    val st = StringTokenizer(readln())

    for (i in 0 until n) {
        nums[i] = st.nextToken().toInt()
    }

    println(solution(nums).joinToString(" "))
}

/**
 * A를 소수 X로 나눈 후 B에 X를 곱하는 행동
 * -> A의 소인수를 B에게 주는 것
 */
private fun solution(
    nums: IntArray
): IntArray {
    val n = nums.size
    // nums의 모든 수의 소인수를 소인수 별로 카운트
    val primeNums = mutableMapOf<Int, Int>()
    // nums의 각 수를 소인수분해로 표현
    val mNums = Array(n) { mapOf<Int, Int>() }

    for (i in nums.indices) {
        mNums[i] = factor(nums[i], primeNums)
    }

    // 최대공약수의 최댓값을 소인수분해로 표현
    val gcdMap = getGcd(n, primeNums)
    // 최대공약수의 최댓값을 만들기 위해 해야 하는
    // A를 소수 X로 나눈 후 B에 X를 곱하는 행동 횟수
    var count = 0

    mNums.forEach {
        count += getDiff(it, gcdMap)
    }

    return intArrayOf(calc(gcdMap), count)
}

/**
 * num을 소인수분해하고 primeNums에 각 소인수의 개수 덧셈
 */
private fun factor(
    num: Int,
    primeNums: MutableMap<Int, Int>
): Map<Int, Int> {
    val result = mutableMapOf<Int, Int>()
    var n = num
    var p = 2

    while (n > 1) {
        if (n % p == 0) {
            n /= p
            result[p] = (result[p] ?: 0) + 1
            primeNums[p] = (primeNums[p] ?: 0) + 1
        } else {
            p++
        }
    }

    return result
}

/**
 * n개의 수를 소인수분해한 primeNums를 사용하여
 * 가능한 최대공약수 중 가장 큰 값 계산
 * -> 모든 수의 소인수를 모아서 균등하게 배분
 */
private fun getGcd(
    n: Int,
    primeNums: Map<Int, Int>
): Map<Int, Int> {
    val gcd = mutableMapOf<Int, Int>()

    for ((num, count) in primeNums) {
        gcd[num] = count / n
    }

    return gcd
}

/**
 * 숫자 B(bMap)가 gcdMap을 약수로 가지기 위해 X를 곱해야 하는 횟수 반환
 */
private fun getDiff(
    bMap: Map<Int, Int>,
    gcdMap: Map<Int, Int>
): Int {
    var result = 0

    gcdMap.forEach { (x, v) ->
        val count = bMap[x] ?: 0

        if (v > count) {
            result += v - count
        }
    }

    return result
}

/**
 * 소인수분해로 표현된 gcdMap을 계산
 */
private fun calc(
    gcdMap: Map<Int, Int>
): Int {
    var result = 1

    gcdMap.forEach { (k, v) ->
        result *= k.toFloat().pow(v.toFloat()).toInt()
    }

    return result
}
