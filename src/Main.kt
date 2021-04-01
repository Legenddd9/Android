fun main(){
    for(i in 1..10){
        var result = isComposite(i);
        println(result)
    }
}


fun isComposite(num: Int): Boolean {
    var res = false
    for (i in 2..num / 2) {
        if (num % i == 0) {
            res = true
            break
        }
    }
    return res
}