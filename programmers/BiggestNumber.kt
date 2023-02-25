import java.util.* 

class Solution {
  
      fun solution1(numbers: IntArray): String {
        val temp = arrayListOf<String>()

        numbers.forEach {
            temp.add(it.toString())
        }

        temp.sortWith(Comparator{ o1, o2 ->
            when{
                o1.length == o2.length -> o2.compareTo(o1)
                else -> (o2 + o1).compareTo(o1 + o2)
            }
        })

        val result = StringBuffer()
        temp.forEachIndexed{ index, s ->
            if(index == 0 && s == "0"){
                return "0"
            }
            result.append(s)
        }

        return result.toString()
    }
  
    //다른 풀이
    fun solution2(numbers: IntArray): String {
        var answer = ""        
        numbers.sortedWith(Comparator({num1: Int, num2: Int -> "$num2$num1".compareTo("$num1$num2")})).forEach { answer += it }
        if ("(0*)".toRegex().replace(answer, "").isEmpty()) { //0이 반복해서 나오는 경우 그냥 0으로 치환
            answer = "0"
        }
        return answer
    }
    
    //다른 풀이 스택 이용
    fun solution3(number: String, k: Int): String {
        var answer = ""
        var kCnt = k
        val numberStack : Stack<Char> = Stack()
        var numArray = CharArray(number.length-k)


        number.forEach {
            while (!numberStack.isEmpty()&&numberStack.peek()<it&&kCnt!=0){
                numberStack.pop()
                kCnt--
            }
            numberStack.push(it)
        }

        for (i in 0 until kCnt){
            numberStack.pop()
        }

        numberStack.forEachIndexed { index, c ->
            numArray[index] = c
        }

        return String(numArray)
    }
}
