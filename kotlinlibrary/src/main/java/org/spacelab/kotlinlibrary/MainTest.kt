package org.spacelab.kotlinlibrary

abstract class MainTest {

    companion object {

        @JvmStatic
        fun main(args: Array<String>) {
            println("Hello kotlin!")
            println(sum(1, 2))
            println(sum(1, 2, 3))
            printSum(1, 2)
        }

        fun sum(a: Int, b: Int): Int {
            return a + b
        }

        fun sum(a: Int, b: Int, c: Int) = a + b + c

        fun printSum(a: Int, b: Int): Unit {
            println("sum of $a and $b is ${a + b}")
        }

        var a = 1
        var s1 = "a is $a"

        fun str (){

            println()
        }

    }

    val a: Int = 1
    val b = 2
    abstract val c: Int

    var x = 5

    /*fun xAdd (){
        x += 1
    }*/

    // 顶层变量
    val PI = 3.14



}