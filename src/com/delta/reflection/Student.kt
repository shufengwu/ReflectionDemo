package com.delta.reflection

/**
 * Created by Shufeng.Wu on 2017/7/10.
 */
@Fancy("wsf", 28)
@Address("biglangfang")
open class Student(name: String, age: Int, @property:Address("qinhuangdao") var school: String) : People(name, age) {

    @Address("field test")
    var f: Int = 0

    constructor(name: String, age: Int, school: String, other: String) : this(name, age, school)

    override fun testPeople() {
        println("Student is People")
    }

    @Address("testStudent")
    fun testStudent(@Address("方法参数注解") str: String) {
        println(str)
    }

    class Other


}