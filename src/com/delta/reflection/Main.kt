package com.delta.reflection

import kotlin.reflect.full.findAnnotation
import kotlin.reflect.full.memberFunctions
import kotlin.reflect.full.memberProperties
import kotlin.reflect.full.superclasses
import kotlin.reflect.jvm.javaField
import kotlin.reflect.jvm.javaGetter
import kotlin.reflect.jvm.jvmName

/**
 * Created by Shufeng.Wu on 2017/7/10.
 */
var x = 1

val strs2 = listOf("a", "bc", "def")
val strs3 = listOf("abc", "124", "a70")
val String.lastChar: Char
    get() = this[length - 1]


fun main(args: Array<String>) {

    /**
     * 类引用
     */
    println("-----------------------------kotlin类引用----------------------------------")
    val c1 = Student::class
    println("simpleName名称：" + c1.simpleName)
    println("qualifiedName名称：" + c1.qualifiedName)
    println("jvmName名称：" + c1.jvmName)
    println()

    println("-----------------------------kotlin内部类引用----------------------------------")
    val c1_in = Student.Other::class
    println("simpleName名称：" + c1_in.simpleName)
    println("qualifiedName名称：" + c1_in.qualifiedName)
    println("jvmName名称：" + c1_in.jvmName)
    println()

    println("-----------------------------java类引用----------------------------------")
    val c2 = Teacher::class.java
    println("simpleName名称：" + c2.simpleName)
    println("canonicalName名称：" + c2.canonicalName)
    println("name名称：" + c2.name)
    println()


    println("-----------------------------java内部类引用----------------------------------")
    val c2_in = Teacher.Other::class.java
    println("simpleName名称：" + c2_in.simpleName)
    println("canonicalName名称：" + c2_in.canonicalName)
    println("name名称：" + c2_in.name)
    println()

    println("-----------------------------绑定的类引用----------------------------------")
    val student1: Student = Student("sfw", 20, "ysu")
    val c3 = student1::class
    println("qualifiedName名称：" + c3.qualifiedName)
    println()

    println("-----------------------------对象的精确类的引用----------------------------------")
    val student: Student = BadStudent("", 0, "")
    println("qualifiedName名称：" + student::class.qualifiedName)
    println()

    println("-----------------------------获取类的修饰符----------------------------------")
    println("是否为抽象类：" + c1.isAbstract)
    println("是否open：" + c1.isOpen)
    println("是否final：" + c1.isFinal)
    println("是否是数据类：" + c1.isData)
    println("是否是内部类：" + c1.isInner)
    println("是否是密封类：" + c1.isSealed)
    println()

    println("-----------------------------获取父类----------------------------------")
    for (c_super in c1.superclasses) {
        println("父类：" + c_super.simpleName + "---" + c_super.qualifiedName)
    }
    println()

    println("-----------------------------构造函数----------------------------------")
    for (c_cons in c1.constructors) {
        println("构造函数：" + c_cons)
    }
    println()

    println("-----------------------------属性、函数----------------------------------")
    for (c_mem in c1.members) {
        println("属性或函数：" + c_mem.name)
    }
    println()

    println("-----------------------------属性----------------------------------")
    for (c_mem in c1.memberProperties) {
        println("属性：" + c_mem.name)
    }
    println()

    println("-----------------------------函数----------------------------------")
    for (c_mem in c1.memberFunctions) {
        println("函数：" + c_mem.name)
    }
    println()

    /**
     * 函数引用
     */
    println("-----------------------------函数引用----------------------------------")
    //(Int)->Boolean
    fun isOdd(x: Int) = x % 2 != 0

    val numbers = listOf(1, 2, 3)
    println(numbers.filter(::isOdd))

    fun isOdd(s: String) = s == "brillig" || s == "slithy"
    val strs = listOf("brillig", "slithy", "tove")
    println(strs.filter(::isOdd))

    //将方法引用存储在具有显式指定类型的变量中
    val predicate: (String) -> Boolean = ::isOdd
    println(strs.filter(predicate))

    /**
     *
     */
    println("-----------------------------函数组合----------------------------------")
    fun <A, B, C> compose(f: (B) -> C, g: (A) -> B): (A) -> C {
        return { x -> f(g(x)) }
    }

    //length函数类型为(String)->Int
    //isOdd函数类型为(Int)->Boolean
    //组合后(String)->Boolean
    fun length(s: String) = s.length //(String)->Int
    val oddLength = compose(::isOdd, ::length)
    val strings = listOf("aghj", "abghj", "abckfjgk")
    println(strings.filter(oddLength))
    println()

    /**
     * 属性引用
     */
    println("-----------------------------属性引用----------------------------------")
    //属性值
    println(::x.get()) // 输出 "1"
    ::x.set(2)
    println(x)
    //属性名
    println("属性名称：" + ::x.name)

    //用在不需要参数的函数处
    println(strs2.map(String::length)) // 输出 [1, 2, 3]
    println()

    //访问属于类的成员的属性
    println("-----------------------------访问属于类的成员的属性----------------------------------")
    val p = Student::school
    println(p.get(student1))
    println()

    //扩展属性
    println("-----------------------------扩展属性----------------------------------")
    println(String::lastChar.get("abc"))
    println()

    println("-----------------------------与 Java 反射的互操作性----------------------------------")
    //查找一个用作 Kotlin 属性 getter 的 幕后字段或 Java方法
    println(A::p.javaGetter)
    println(A::p.javaField)

    //对应于 Java 类的 Kotlin 类
    println(Teacher().javaClass.kotlin)
    println()

    /**
     * 构造函数的引用
     */
    println("-----------------------------构造函数的引用----------------------------------")
    /**
     * 绑定的函数与属性引用
     */
    //正则表达式
    val numberRegex = "\\d+".toRegex()
    println(numberRegex.matches("29"))

    val isNumber = numberRegex::matches
    println(isNumber("29"))

    println(strs3.filter(isNumber))

    //属性绑定
    val prop = "abc"::length
    println(prop.get())   // 输出“3”

}


