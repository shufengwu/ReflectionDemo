package com.delta.reflection

/**
 * Created by Shufeng.Wu on 2017/7/10.
 */
@Target(AnnotationTarget.CLASS, AnnotationTarget.FUNCTION,
        AnnotationTarget.VALUE_PARAMETER, AnnotationTarget.EXPRESSION)
@Retention(AnnotationRetention.RUNTIME)
@MustBeDocumented
annotation class Fancy(val myName: String, val myAge: Int)