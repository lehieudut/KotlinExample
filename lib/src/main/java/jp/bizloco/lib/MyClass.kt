package jp.bizloco.lib

import java.awt.Button


/**
 * Created by Hieu Le on 12/19/18.
 */
class MyClass {
    private val test: String? = null

    companion object {

        @JvmStatic
        fun main(arg: Array<String>) {
            //Kotlin Variable
            //val Cannot re-assign
            //var Can re-assign

//            val a = 12
//            a = 13

//            val a = "string"
//            val b = 12
//            val c: String = "cast"

            //String template
//            val a: String = "Le Trung Hieu"
//            val b = 18
//            val c = 20
//            print("Length: ${a.length}")
//            print("Ten: $a, tuoi: $b.")
//            print(" Tong la: ${b + c}")

            //Nullable type
//            val a:String = null
//            // ? cho phép null -> ko lỗi, !! ko cho phép null -> NullPointerException
//            print(a!!.length)

            //function
            //print(cong(12,14))

            //Lamda function: như một hàm, bỏ override ko cần thiết, ngắn gọn code
//            val tinhtong = { a: Int, b: Int ->
//                print("Ket qua: ${a + b}")
//                a + b
//            }
//            print(2 + tinhtong(1, 6))

            //when selector
        }
    }


}

fun cong(a: Int, b: Int): String {
    return (a + b).toString()
}

//Reference https://www.youtube.com/watch?v=EFL6zwMMhME&list=PLWBrqglnjNl15KrJh1S5AWOZSoQ27RxjI&index=1


