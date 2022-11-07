package pl.allegro.tech.spunit

import org.junit.jupiter.api.DynamicTest
import pl.allegro.tech.spunit.util.flattenQuadruple
import pl.allegro.tech.spunit.util.flattenQuintuple
import pl.allegro.tech.spunit.util.flattenSextuple
import pl.allegro.tech.spunit.util.flattenTriple
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.contract
import kotlin.reflect.KType
import kotlin.reflect.full.isSubtypeOf
import kotlin.reflect.typeOf

fun test(caseDescription: String? = null, testBlock: SingleTestCase.() -> Unit): List<DynamicTest> =
    DynamicTest.dynamicTest(caseDescription ?: "Test") {
        SingleTestCase.testBlock()
    }.let(::listOf)

fun SpunitHooks.test(caseDescription: String? = null, testBlock: SingleTestCase.() -> Unit): List<DynamicTest> =
    DynamicTest.dynamicTest(caseDescription ?: "Test") {
        try {
            beforeEachTest()
            SingleTestCase.testBlock()
        } finally {
            afterEachTest()
        }
    }.let(::listOf)

inline fun <reified A> test(caseDescription: String? = null, noinline testBlock: OneParamTestCase<A>.(A) -> Unit): OneParamTestCase<A> =
    OneParamTestCase(testBlock = testBlock, aType = typeOf<A>(), caseDescription = caseDescription)

inline fun <reified A> SpunitHooks.test(caseDescription: String? = null, noinline testBlock: OneParamTestCase<A>.(A) -> Unit): OneParamTestCase<A> =
    OneParamTestCase(testBlock = testBlock, aType = typeOf<A>(), caseDescription = caseDescription, beforeEachCallback = ::beforeEachTest, afterEachCallback = ::afterEachTest)

inline fun <reified A, reified B> test(caseDescription: String? = null, noinline testBlock: TwoParamsTestCase<A, B>.(A, B) -> Unit): TwoParamsTestCase<A, B> =
    TwoParamsTestCase(testBlock = testBlock, aType = typeOf<A>(), bType = typeOf<B>(), caseDescription = caseDescription)

inline fun <reified A, reified B> SpunitHooks.test(caseDescription: String? = null, noinline testBlock: TwoParamsTestCase<A, B>.(A, B) -> Unit): TwoParamsTestCase<A, B> =
    TwoParamsTestCase(testBlock = testBlock, aType = typeOf<A>(), bType = typeOf<B>(), caseDescription = caseDescription, beforeEachCallback = ::beforeEachTest, afterEachCallback = ::afterEachTest)

inline fun <reified A, reified B, reified C> test(caseDescription: String? = null, noinline testBlock: ThreeParamsTestCase<A, B, C>.(A, B, C) -> Unit): ThreeParamsTestCase<A, B, C> =
    ThreeParamsTestCase(testBlock = testBlock, aType = typeOf<A>(), bType = typeOf<B>(), cType = typeOf<C>(), caseDescription = caseDescription)

inline fun <reified A, reified B, reified C> SpunitHooks.test(caseDescription: String? = null, noinline testBlock: ThreeParamsTestCase<A, B, C>.(A, B, C) -> Unit): ThreeParamsTestCase<A, B, C> =
    ThreeParamsTestCase(testBlock = testBlock, aType = typeOf<A>(), bType = typeOf<B>(), cType = typeOf<C>(), caseDescription = caseDescription, beforeEachCallback = ::beforeEachTest, afterEachCallback = ::afterEachTest)

inline fun <reified A, reified B, reified C, reified D> test(caseDescription: String? = null, noinline testBlock: FourParamsTestCase<A, B, C, D>.(A, B, C, D) -> Unit): FourParamsTestCase<A, B, C, D> =
    FourParamsTestCase(testBlock = testBlock, aType = typeOf<A>(), bType = typeOf<B>(), cType = typeOf<C>(), dType = typeOf<D>(), caseDescription = caseDescription)

inline fun <reified A, reified B, reified C, reified D> SpunitHooks.test(caseDescription: String? = null, noinline testBlock: FourParamsTestCase<A, B, C, D>.(A, B, C, D) -> Unit): FourParamsTestCase<A, B, C, D> =
    FourParamsTestCase(testBlock = testBlock, aType = typeOf<A>(), bType = typeOf<B>(), cType = typeOf<C>(), dType = typeOf<D>(), caseDescription = caseDescription, beforeEachCallback = ::beforeEachTest, afterEachCallback = ::afterEachTest)

inline fun <reified A, reified B, reified C, reified D, reified E> test(caseDescription: String? = null, noinline testBlock: FiveParamsTestCase<A, B, C, D, E>.(A, B, C, D, E) -> Unit): FiveParamsTestCase<A, B, C, D, E> =
    FiveParamsTestCase(testBlock = testBlock, aType = typeOf<A>(), bType = typeOf<B>(), cType = typeOf<C>(), dType = typeOf<D>(), eType = typeOf<E>(), caseDescription = caseDescription)

inline fun <reified A, reified B, reified C, reified D, reified E> SpunitHooks.test(caseDescription: String? = null, noinline testBlock: FiveParamsTestCase<A, B, C, D, E>.(A, B, C, D, E) -> Unit): FiveParamsTestCase<A, B, C, D, E> =
    FiveParamsTestCase(testBlock = testBlock, aType = typeOf<A>(), bType = typeOf<B>(), cType = typeOf<C>(), dType = typeOf<D>(), eType = typeOf<E>(), caseDescription = caseDescription, beforeEachCallback = ::beforeEachTest, afterEachCallback = ::afterEachTest)

inline fun <reified A, reified B, reified C, reified D, reified E, reified F> test(caseDescription: String? = null, noinline testBlock: SixParamsTestCase<A, B, C, D, E, F>.(A, B, C, D, E, F) -> Unit): SixParamsTestCase<A, B, C, D, E, F> =
    SixParamsTestCase(testBlock = testBlock, aType = typeOf<A>(), bType = typeOf<B>(), cType = typeOf<C>(), dType = typeOf<D>(), eType = typeOf<E>(), fType = typeOf<F>(), caseDescription = caseDescription)

inline fun <reified A, reified B, reified C, reified D, reified E, reified F> SpunitHooks.test(caseDescription: String? = null, noinline testBlock: SixParamsTestCase<A, B, C, D, E, F>.(A, B, C, D, E, F) -> Unit): SixParamsTestCase<A, B, C, D, E, F> =
    SixParamsTestCase(testBlock = testBlock, aType = typeOf<A>(), bType = typeOf<B>(), cType = typeOf<C>(), dType = typeOf<D>(), eType = typeOf<E>(), fType = typeOf<F>(), caseDescription = caseDescription, beforeEachCallback = ::beforeEachTest, afterEachCallback = ::afterEachTest)

interface SpunitHooks {
    fun beforeEachTest() {}

    fun afterEachTest() {}
}

interface TestCase {
    val given: Unit
        get() = Unit
    val `when`: Unit
        get() = Unit
    val then: Unit
        get() = Unit
    val expect: Unit
        get() = Unit
    val and: Unit
        get() = Unit

    fun given(message: String) {}

    fun `when`(message: String) {}

    fun then(message: String) {}

    fun expect(message: String) {}

    fun and(message: String) {}
}

object SingleTestCase : TestCase {
}

// TODO: Remove duplication of code
class OneParamTestCase<A>(private val testBlock: OneParamTestCase<A>.(A) -> Unit, val aType: KType, val caseDescription: String? = null, val beforeEachCallback: () -> Unit = {}, val afterEachCallback: () -> Unit = {}) : TestCase {
    lateinit var header: String
    var aParameters: MutableList<A> = mutableListOf()

    inline infix fun <reified T> String.I(rhs: List<T>) {
        handleParam(lhs = this, rhs = rhs)
    }

    inline infix fun <reified T> String.II(rhs: List<T>) {
        handleParam(lhs = this, rhs = rhs)
    }

    inline fun <reified T> handleParam(lhs: String, rhs: List<T>) {
        header = lhs
        rhs.forEach {
            aParameters.add(checkTypeAndCast(it, aType, header))
        }
    }

    fun where(whereBlock: OneParamTestCase<A>.() -> Unit): List<DynamicTest> {
        whereBlock()
        return aParameters.mapIndexed { i, a ->
            val name = if (caseDescription != null) {
                caseDescription.replace("#${header[0]}", a.toString())
            } else {
                "Test #${i + 1}"
            }
            DynamicTest.dynamicTest(name) {
                try {
                    beforeEachCallback()
                    testBlock(a)
                } finally {
                    afterEachCallback()
                }
            }
        }
    }
}

// TODO: Remove duplication of code
class TwoParamsTestCase<A, B>(private val testBlock: TwoParamsTestCase<A, B>.(A, B) -> Unit, val aType: KType, val bType: KType, val caseDescription: String? = null, val beforeEachCallback: () -> Unit = {}, val afterEachCallback: () -> Unit = {}) : TestCase {
    var header: MutableList<String> = mutableListOf()
    var aParameters: MutableList<A> = mutableListOf()
    var bParameters: MutableList<B> = mutableListOf()

    inline infix fun <reified T1, reified T2> T1.I(rhs: T2) {
        handleParam(this, rhs)
    }

    inline infix fun <reified T> T.I(rhs: Nothing?) {
        handleParam(this, Unit)
    }

    inline infix fun <reified T> Nothing?.I(rhs: T) {
        handleParam(Unit, rhs)
    }

    infix fun Nothing?.I(rhs: Nothing?) {
        handleParam(Unit, Unit)
    }

    inline infix fun <reified T1, reified T2> T1.II(rhs: T2) {
        handleParam(this, rhs)
    }

    inline infix fun <reified T> T.II(rhs: Nothing?) {
        handleParam(this, Unit)
    }

    inline infix fun <reified T> Nothing?.II(rhs: T) {
        handleParam(Unit, rhs)
    }

    infix fun Nothing?.II(rhs: Nothing?) {
        handleParam(Unit, Unit)
    }

    inline fun <reified T1, reified T2> handleParam(lhs: T1, rhs: T2) {
        if (header.isEmpty()) {
            assertHeaderIsString(lhs)
            header.add(lhs)
            assertHeaderIsString(rhs)
            header.add(rhs)
            return
        }
        aParameters.add(checkTypeAndCast(lhs, aType, header[0]))
        bParameters.add(checkTypeAndCast(rhs, bType, header[1]))
    }

    fun where(whereBlock: TwoParamsTestCase<A, B>.() -> Unit): List<DynamicTest> {
        whereBlock()
        return aParameters.zip(bParameters).mapIndexed { i, (a, b) ->
            val name = if (caseDescription != null) {
                val headerToValue = mapOf(header[0] to a, header[1] to b)
                header
                    .sortedByDescending { it.length }
                    .fold(caseDescription) { acc, s -> acc.replace("#${s}", headerToValue[s].toString()) }
            } else {
                "Test #${i + 1}"
            }
            DynamicTest.dynamicTest(name) {
                try {
                    beforeEachCallback()
                    testBlock(a, b)
                } finally {
                    afterEachCallback()
                }
            }
        }
    }
}

// TODO: Remove duplication of code
class ThreeParamsTestCase<A, B, C>(private val testBlock: ThreeParamsTestCase<A, B, C>.(A, B, C) -> Unit, val aType: KType, val bType: KType, val cType: KType, val caseDescription: String? = null, val beforeEachCallback: () -> Unit = {}, val afterEachCallback: () -> Unit = {}) : TestCase {
    var header: MutableList<String> = mutableListOf()
    var aParameters: MutableList<A> = mutableListOf()
    var bParameters: MutableList<B> = mutableListOf()
    var cParameters: MutableList<C> = mutableListOf()

    inline infix fun <reified T1, reified T2> T1.I(rhs: T2) {
        handleParam(lhs = this, rhs = rhs)
    }


    inline infix fun <reified T> T.I(rhs: Nothing?) {
        handleParam(this, Unit)
    }

    inline infix fun <reified T> Nothing?.I(rhs: T) {
        handleParam(Unit, rhs)
    }

    infix fun Nothing?.I(rhs: Nothing?) {
        handleParam(Unit, Unit)
    }

    inline infix fun <reified T1, reified T2> T1.II(rhs: T2) {
        handleParam(lhs = this, rhs = rhs)
    }


    inline infix fun <reified T> T.II(rhs: Nothing?) {
        handleParam(this, Unit)
    }

    inline infix fun <reified T> Nothing?.II(rhs: T) {
        handleParam(Unit, rhs)
    }

    infix fun Nothing?.II(rhs: Nothing?) {
        handleParam(Unit, Unit)
    }


    inline fun <reified T1, reified T2> handleParam(lhs: T1, rhs: T2) {
        if (header.size < NUMBER_OF_PARAMETERS) {
            if (header.isEmpty()) {
                assertHeaderIsString(lhs)
                header.add(lhs)
            }
            assertHeaderIsString(rhs)
            header.add(rhs)
            return
        }
        val paramsSize = aParameters.size + bParameters.size + cParameters.size
        when (paramsSize % NUMBER_OF_PARAMETERS) {
            0 -> {
                aParameters.add(checkTypeAndCast(lhs, aType, header[0]))
                bParameters.add(checkTypeAndCast(rhs, bType, header[1]))
            }

            1 -> {
                throw SpunitBug()
            }

            2 -> {
                cParameters.add(checkTypeAndCast(rhs, cType, header[2]))
            }
        }
    }

    fun where(whereBlock: ThreeParamsTestCase<A, B, C>.() -> Unit): List<DynamicTest> {
        whereBlock()
        return aParameters.zip(bParameters).zip(cParameters).flattenTriple().mapIndexed { i, (a, b, c) ->
            val name = if (caseDescription != null) {
                val headerToValue = mapOf(header[0] to a, header[1] to b, header[2] to c)
                header
                    .sortedByDescending { it.length }
                    .fold(caseDescription) { acc, s -> acc.replace("#${s}", headerToValue[s].toString()) }
            } else {
                "Test #${i + 1}"
            }
            DynamicTest.dynamicTest(name) {
                try {
                    beforeEachCallback()
                    testBlock(a, b, c)
                } finally {
                    afterEachCallback()
                }
            }
        }
    }

    companion object {
        const val NUMBER_OF_PARAMETERS: Int = 3
    }
}

// TODO: Remove duplication of code
class FourParamsTestCase<A, B, C, D>(private val testBlock: FourParamsTestCase<A, B, C, D>.(A, B, C, D) -> Unit, val aType: KType, val bType: KType, val cType: KType, val dType: KType, val caseDescription: String? = null, val beforeEachCallback: () -> Unit = {}, val afterEachCallback: () -> Unit = {}) : TestCase {
    var header: MutableList<String> = mutableListOf()
    var aParameters: MutableList<A> = mutableListOf()
    var bParameters: MutableList<B> = mutableListOf()
    var cParameters: MutableList<C> = mutableListOf()
    var dParameters: MutableList<D> = mutableListOf()

    inline infix fun <reified T1, reified T2> T1.I(rhs: T2) {
        handleParam(lhs = this, rhs = rhs)
    }

    inline infix fun <reified T> T.I(rhs: Nothing?) {
        handleParam(this, Unit)
    }

    inline infix fun <reified T> Nothing?.I(rhs: T) {
        handleParam(Unit, rhs)
    }

    infix fun Nothing?.I(rhs: Nothing?) {
        handleParam(Unit, Unit)
    }

    inline infix fun <reified T1, reified T2> T1.II(rhs: T2) {
        handleParam(lhs = this, rhs = rhs)
    }

    inline infix fun <reified T> T.II(rhs: Nothing?) {
        handleParam(this, Unit)
    }

    inline infix fun <reified T> Nothing?.II(rhs: T) {
        handleParam(Unit, rhs)
    }

    infix fun Nothing?.II(rhs: Nothing?) {
        handleParam(Unit, Unit)
    }

    inline fun <reified T1, reified T2> handleParam(lhs: T1, rhs: T2) {
        if (header.size < NUMBER_OF_PARAMETERS) {
            if (header.isEmpty()) {
                assertHeaderIsString(lhs)
                header.add(lhs)
            }
            assertHeaderIsString(rhs)
            header.add(rhs)
            return
        }
        val paramsSize = aParameters.size + bParameters.size + cParameters.size + dParameters.size
        when (paramsSize % NUMBER_OF_PARAMETERS) {
            0 -> {
                aParameters.add(checkTypeAndCast(lhs, aType, header[0]))
                bParameters.add(checkTypeAndCast(rhs, bType, header[1]))
            }

            1 -> {
                throw SpunitBug()
            }

            2 -> {
                cParameters.add(checkTypeAndCast(rhs, cType, header[2]))
            }

            3 -> {
                dParameters.add(checkTypeAndCast(rhs, dType, header[3]))
            }
        }
    }

    fun where(whereBlock: FourParamsTestCase<A, B, C, D>.() -> Unit): List<DynamicTest> {
        whereBlock()
        return aParameters.zip(bParameters).zip(cParameters).zip(dParameters).flattenQuadruple().mapIndexed { i, (a, b, c, d) ->
            val name = if (caseDescription != null) {
                val headerToValue = mapOf(header[0] to a, header[1] to b, header[2] to c, header[3] to d)
                header
                    .sortedByDescending { it.length }
                    .fold(caseDescription) { acc, s -> acc.replace("#${s}", headerToValue[s].toString()) }
            } else {
                "Test #${i + 1}"
            }
            DynamicTest.dynamicTest(name) {
                try {
                    beforeEachCallback()
                    testBlock(a, b, c, d)
                } finally {
                    afterEachCallback()
                }
            }
        }
    }

    companion object {
        const val NUMBER_OF_PARAMETERS: Int = 4
    }
}

class FiveParamsTestCase<A, B, C, D, E>(private val testBlock: FiveParamsTestCase<A, B, C, D, E>.(A, B, C, D, E) -> Unit, val aType: KType, val bType: KType, val cType: KType, val dType: KType, val eType: KType, val caseDescription: String? = null, val beforeEachCallback: () -> Unit = {}, val afterEachCallback: () -> Unit = {}) : TestCase {
    var header: MutableList<String> = mutableListOf()
    var aParameters: MutableList<A> = mutableListOf()
    var bParameters: MutableList<B> = mutableListOf()
    var cParameters: MutableList<C> = mutableListOf()
    var dParameters: MutableList<D> = mutableListOf()
    var eParameters: MutableList<E> = mutableListOf()

    inline infix fun <reified T1, reified T2> T1.I(rhs: T2) {
        handleParam(lhs = this, rhs = rhs)
    }

    inline infix fun <reified T> T.I(rhs: Nothing?) {
        handleParam(this, Unit)
    }

    inline infix fun <reified T> Nothing?.I(rhs: T) {
        handleParam(Unit, rhs)
    }

    infix fun Nothing?.I(rhs: Nothing?) {
        handleParam(Unit, Unit)
    }

    inline infix fun <reified T1, reified T2> T1.II(rhs: T2) {
        handleParam(lhs = this, rhs = rhs)
    }

    inline infix fun <reified T> T.II(rhs: Nothing?) {
        handleParam(this, Unit)
    }

    inline infix fun <reified T> Nothing?.II(rhs: T) {
        handleParam(Unit, rhs)
    }

    infix fun Nothing?.II(rhs: Nothing?) {
        handleParam(Unit, Unit)
    }

    inline fun <reified T1, reified T2> handleParam(lhs: T1, rhs: T2) {
        if (header.size < NUMBER_OF_PARAMETERS) {
            if (header.isEmpty()) {
                assertHeaderIsString(lhs)
                header.add(lhs)
            }
            assertHeaderIsString(rhs)
            header.add(rhs)
            return
        }
        val paramsSize = aParameters.size + bParameters.size + cParameters.size + dParameters.size + eParameters.size
        when (paramsSize % NUMBER_OF_PARAMETERS) {
            0 -> {
                aParameters.add(checkTypeAndCast(lhs, aType, header[0]))
                bParameters.add(checkTypeAndCast(rhs, bType, header[1]))
            }

            1 -> {
                throw SpunitBug()
            }

            2 -> {
                cParameters.add(checkTypeAndCast(rhs, cType, header[2]))
            }

            3 -> {
                dParameters.add(checkTypeAndCast(rhs, dType, header[3]))
            }

            4 -> {
                eParameters.add(checkTypeAndCast(rhs, eType, header[4]))
            }
        }
    }

    fun where(whereBlock: FiveParamsTestCase<A, B, C, D, E>.() -> Unit): List<DynamicTest> {
        whereBlock()
        return aParameters.zip(bParameters).zip(cParameters).zip(dParameters).zip(eParameters).flattenQuintuple().mapIndexed { i, (a, b, c, d, e) ->
            val name = if (caseDescription != null) {
                val headerToValue = mapOf(header[0] to a, header[1] to b, header[2] to c, header[3] to d, header[4] to e)
                header
                    .sortedByDescending { it.length }
                    .fold(caseDescription) { acc, s -> acc.replace("#${s}", headerToValue[s].toString()) }
            } else {
                "Test #${i + 1}"
            }
            DynamicTest.dynamicTest(name) {
                try {
                    beforeEachCallback()
                    testBlock(a, b, c, d, e)
                } finally {
                    afterEachCallback()
                }
            }
        }
    }

    companion object {
        const val NUMBER_OF_PARAMETERS: Int = 5
    }
}

class SixParamsTestCase<A, B, C, D, E, F>(private val testBlock: SixParamsTestCase<A, B, C, D, E, F>.(A, B, C, D, E, F) -> Unit, val aType: KType, val bType: KType, val cType: KType, val dType: KType, val eType: KType, val fType: KType, val caseDescription: String? = null, val beforeEachCallback: () -> Unit = {}, val afterEachCallback: () -> Unit = {}) : TestCase {
    var header: MutableList<String> = mutableListOf()
    var aParameters: MutableList<A> = mutableListOf()
    var bParameters: MutableList<B> = mutableListOf()
    var cParameters: MutableList<C> = mutableListOf()
    var dParameters: MutableList<D> = mutableListOf()
    var eParameters: MutableList<E> = mutableListOf()
    var fParameters: MutableList<F> = mutableListOf()

    inline infix fun <reified T1, reified T2> T1.I(rhs: T2) {
        handleParam(lhs = this, rhs = rhs)
    }

    inline infix fun <reified T> T.I(rhs: Nothing?) {
        handleParam(this, Unit)
    }

    inline infix fun <reified T> Nothing?.I(rhs: T) {
        handleParam(Unit, rhs)
    }

    infix fun Nothing?.I(rhs: Nothing?) {
        handleParam(Unit, Unit)
    }

    inline infix fun <reified T1, reified T2> T1.II(rhs: T2) {
        handleParam(lhs = this, rhs = rhs)
    }

    inline infix fun <reified T> T.II(rhs: Nothing?) {
        handleParam(this, Unit)
    }

    inline infix fun <reified T> Nothing?.II(rhs: T) {
        handleParam(Unit, rhs)
    }

    infix fun Nothing?.II(rhs: Nothing?) {
        handleParam(Unit, Unit)
    }

    inline fun <reified T1, reified T2> handleParam(lhs: T1, rhs: T2) {
        if (header.size < NUMBER_OF_PARAMETERS) {
            if (header.isEmpty()) {
                assertHeaderIsString(lhs)
                header.add(lhs)
            }
            assertHeaderIsString(rhs)
            header.add(rhs)
            return
        }
        val paramsSize = aParameters.size + bParameters.size + cParameters.size + dParameters.size + eParameters.size + fParameters.size
        when (paramsSize % NUMBER_OF_PARAMETERS) {
            0 -> {
                aParameters.add(checkTypeAndCast(lhs, aType, header[0]))
                bParameters.add(checkTypeAndCast(rhs, bType, header[1]))
            }

            1 -> {
                throw SpunitBug()
            }

            2 -> {
                cParameters.add(checkTypeAndCast(rhs, cType, header[2]))
            }

            3 -> {
                dParameters.add(checkTypeAndCast(rhs, dType, header[3]))
            }

            4 -> {
                eParameters.add(checkTypeAndCast(rhs, eType, header[4]))
            }

            5 -> {
                fParameters.add(checkTypeAndCast(rhs, fType, header[5]))
            }
        }
    }

    fun where(whereBlock: SixParamsTestCase<A, B, C, D, E, F>.() -> Unit): List<DynamicTest> {
        whereBlock()
        return aParameters.zip(bParameters).zip(cParameters).zip(dParameters).zip(eParameters).zip(fParameters).flattenSextuple().mapIndexed { i, (a, b, c, d, e, f) ->
            val name = if (caseDescription != null) {
                val headerToValue = mapOf(header[0] to a, header[1] to b, header[2] to c, header[3] to d, header[4] to e, header[5] to f)
                header
                    .sortedByDescending { it.length }
                    .fold(caseDescription) { acc, s -> acc.replace("#${s}", headerToValue[s].toString()) }
            } else {
                "Test #${i + 1}"
            }
            DynamicTest.dynamicTest(name) {
                try {
                    beforeEachCallback()
                    testBlock(a, b, c, d, e, f)
                } finally {
                    afterEachCallback()
                }
            }
        }
    }

    companion object {
        const val NUMBER_OF_PARAMETERS: Int = 6
    }
}

inline fun <reified T1, T2> checkTypeAndCast(value: T1, kType: KType, parameterName: String) =
    if (value == Unit) {
        assertTypeIsNullable(kType, parameterName)
        null as T2
    } else {
        assertParameterType(value, kType, parameterName)
        value as T2
    }

@OptIn(ExperimentalContracts::class)
inline fun <reified T> assertHeaderIsString(value: T) {
    contract {
        returns() implies (value is String)
    }
    require(value is String) { "Header $value should be a String, actual type is ${typeOf<T>()} " }
}

inline fun <reified T> assertParameterType(value: T, kType: KType, parameterName: String) {
    require(typeOf<T>().isSubtypeOf(kType)) { "Parameter $parameterName=$value should be a $kType type, actual type is ${typeOf<T>()} " }
}

fun assertTypeIsNullable(kType: KType, parameterName: String) {
    require(kType.isMarkedNullable) { "Parameter $parameterName, of type: $kType must be nullable because some of its values are null" }
}

class SpunitBug : RuntimeException("There is a bug in Spunit. Please contact the maintainers and send them the exception stack trace.")
