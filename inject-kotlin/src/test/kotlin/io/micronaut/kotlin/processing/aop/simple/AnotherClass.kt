/*
 * Copyright 2017-2020 original authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.micronaut.kotlin.processing.aop.simple

/**
 * @author Graeme Rocher
 * @since 1.0
 */
@Mutating("name")
open class AnotherClass<A> {

    // protected methods not proxied
    protected fun testProtected(name: String): String {
        return "Name is $name"
    }

    // protected methods not proxied
    private fun testPrivate(name: String): String {
        return "Name is $name"
    }

    fun test(name: String): String {
        return "Name is $name"
    }

    @Mutating("age")
    fun test(age: Int): String {
        return "Age is $age"
    }

    fun test(name: String, age: Int): String {
        return "Name is $name and age is $age"
    }

    fun test(): String {
        return "noargs"
    }

    fun testVoid(name: String) {
        assert(name == "changed")
    }

    fun testVoid(name: String, age: Int) {
        assert(name == "changed")
        assert(age == 10)
    }

    fun testBoolean(name: String): Boolean {
        return name == "changed"
    }

    fun testBoolean(name: String, age: Int): Boolean {
        assert(age == 10)
        return name == "changed"
    }

    fun testInt(name: String): Int {
        return if (name == "changed") 1 else 0
    }

    @Mutating("age")
    fun testInt(name: String, age: Int): Int {
        assert(name == "test")
        return age
    }

    fun testLong(name: String): Long {
        return if (name == "changed") 1 else 0
    }

    @Mutating("age")
    fun testLong(name: String, age: Int): Long {
        assert(name == "test")
        return age.toLong()
    }

    fun testShort(name: String): Short {
        return (if (name == "changed") 1 else 0).toShort()
    }

    @Mutating("age")
    fun testShort(name: String, age: Int): Short {
        assert(name == "test")
        return age.toShort()
    }

    fun testByte(name: String): Byte {
        return (if (name == "changed") 1 else 0).toByte()
    }

    @Mutating("age")
    fun testByte(name: String, age: Int): Byte {
        assert(name == "test")
        return age.toByte()
    }

    fun testDouble(name: String): Double {
        return if (name == "changed") 1.0 else 0.0
    }

    @Mutating("age")
    fun testDouble(name: String, age: Int): Double {
        assert(name == "test")
        return age.toDouble()
    }

    fun testFloat(name: String): Float {
        return if (name == "changed") 1F else 0F
    }

    @Mutating("age")
    fun testFloat(name: String, age: Int): Float {
        assert(name == "test")
        return age.toFloat()
    }

    fun testChar(name: String): Char {
        return (if (name == "changed") 1 else 0).toChar()
    }

    @Mutating("age")
    fun testChar(name: String, age: Int): Char {
        assert(name == "test")
        return age.toChar()
    }

    fun testByteArray(name: String, data: ByteArray): ByteArray {
        assert(name == "changed")
        return data
    }

    fun <T : CharSequence> testGenericsWithExtends(name: T, age: Int): T {
        return "Name is $name" as T
    }

    fun <T> testListWithWildCardIn(name: T, p2: CovariantClass<in String>): CovariantClass<in String> {
        return CovariantClass(name.toString())
    }

    fun <T> testListWithWildCardOut(name: T, p2: CovariantClass<out String>): CovariantClass<out String> {
        return CovariantClass(name.toString())
    }

    fun testGenericsFromType(name: A, age: Int): A {
        return "Name is $name" as A
    }
}
