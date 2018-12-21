package jp.bizloco.lib


/**
 * Created by Hieu Le on 12/19/18.
 */
class MyClass {

    companion object {

        @JvmStatic
        fun main(arg: Array<String>) {
            val a = Student(1233, "10D3")
            val b = Student(1234, "10ES", "Good")
            b.name = "Hieu"
            println(b.isHandsome())

        }
    }
}

open class Person() {

    var name: String? = null

    fun isHandsome(): Boolean {
        return name.equals("Hieu")
    }
}

class Student(var id: Int, var className: String) : Person() {

    constructor(id: Int, className: String, rank: String) : this(id, className)

}
