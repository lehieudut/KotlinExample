package jp.bizloco.lib


/**
 * Created by Hieu Le on 12/19/18.
 */
class MyClass {

    companion object {

        @JvmStatic
        fun main(arg: Array<String>) {
            val a = Person("Hieu")
            println(a.isHandsome())
            val b = Student("Hieu")
            println(b.isHandsome())
        }

    }
}

open class Person(open var name:String) {

    var old: Int? = null

    fun isHandsome(): Boolean {
        return name.equals("Hieu")
    }
}

class Student(override var name:String) : Person(name) {
    var id:String? = null
}
