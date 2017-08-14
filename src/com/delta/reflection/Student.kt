package com.delta.reflection

/**
 * Created by Shufeng.Wu on 2017/7/10.
 */
@Fancy
open class Student(name: String, age: Int, val school: String) : People(name, age) {

    constructor(name: String, age: Int, school: String,other:String):this(name,age,school){

    }

    override fun testPeople() {
        println("Student is People")
    }

    fun testStudent() {
        println("Student")
    }

    class Other


}