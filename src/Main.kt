import kotlin.jvm.internal.PropertyReference0Impl

fun main(){
    var num1 = 5F;
    var num2 = 12F;

    var obj = Fraction(num1,num2,"1/4");

    println("Fraction = " + obj.Fract(obj))

    println("${num1} + ${num2} = "+obj.Addition());
    println("${num1} - ${num2} = "+obj.Subtraction());
    println("${num1} * ${num2} = "+obj.Multiplication());
    println("${num1} / ${num2} = "+obj.Division());
}

class Fraction(private var num1: Float, private var num2: Float, private var fract: String){

    fun Addition(): Float{
        return num1 + num2;
    }

    fun Subtraction(): Float{
        return num1 - num2;
    }

    fun Multiplication(): Float{
        return num1 * num2;
    }

    fun Division(): Float{
        return num1 / num2;
    }

    fun Fract(other: Any?): Double {
        if(other is Fraction){
            if (fract.contains("/")) {
                val rat: List<String> = fract.split("/")
                return rat[0].toDouble() / rat[1].toDouble()
            }
        }
        return 0.0
    }

}