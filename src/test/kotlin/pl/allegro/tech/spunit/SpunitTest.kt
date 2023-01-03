package pl.allegro.tech.spunit

import assertk.assertThat
import assertk.assertions.hasLength
import assertk.assertions.isEqualTo
import pl.allegro.tech.spunit.annotation.Spunit
import java.math.BigDecimal

class SpunitTest : SpunitHooks {
    var beforeEachRunCount = 0
    var afterEachRunCount = 0

    override fun beforeEachTest() {
        beforeEachRunCount += 1
    }

    override fun afterEachTest() {
        afterEachRunCount += 1
    }

    @Spunit
    fun `should run single test`() = test {
        given
        val foo = 1

        `when`
        val bar = foo + 1

        then
        assertThat(bar).isEqualTo(2)
    }

    @Spunit
    fun `should run test with one parameter`() = test("#foo") { foo: String ->
        expect
        assertThat(foo).hasLength(3)
    }.where {
        "foo" I listOf("abc", "def", "123")
    }

    @Spunit
    fun `should run test with two parameters`() = test("#foo + 1 = #expected") { foo: Int?, expected: String? ->
        given("bar")
        val bar = 1

        `when`("baz = foo + bar")
        val baz = foo?.let { it + bar }

        then("baz == expected")
        assertThat(baz?.toString()).isEqualTo(expected)
    }.where {
        "foo" II "expected"
        1     II "2"
        null  II null
        5     II "6"
    }

    @Spunit
    fun `should run test with three parameters`() = test { foo: Int?, bar: BigDecimal?, expected: String? ->
        `when`
        val baz = if (foo != null && bar != null) foo + bar.toInt() else null

        then
        assertThat(baz?.toString()).isEqualTo(expected)
    }.where {
        "foo" I "bar"             II "expected"
        1     I BigDecimal(2)     II "3"
        null  I BigDecimal(1)     II null
        5     I BigDecimal(1)     II "6"
        null  I null              II null
    }

    @Spunit
    fun `should run test with four parameters`() = test { foo: Int?, bar: BigDecimal?, baz: List<Int>, expected: String? ->
        `when`
        val qux = if (foo != null && bar != null) foo + bar.toInt() + baz.sum() else null

        then
        assertThat(qux?.toString()).isEqualTo(expected)
    }.where {
        "foo" I "bar"         I "baz"        II "expected"
        1     I BigDecimal(2) I listOf(3, 1) II "7"
        null  I BigDecimal(2) I listOf(4, 2) II null
        5     I null          I listOf(3)    II null
        null  I null          I listOf(7)    II null
    }

    // TODO: Make this test great again. Now assertions are too simple because they don't check number of runs.
    @Spunit
    fun `should run callbacks before and after each test with one parameter`() = test { i: Int ->
        expect
        assertThat(beforeEachRunCount >= i)

        and
        assertThat(afterEachRunCount >= i)
    }.where {
        "i" I listOf(1, 2, 3)
    }

    @Spunit
    fun `should work with 5 columns`() =
    test("#a1 + #a2 + #a3 + #a4 = #expected") { a1: Int, a2: Int, a3: Int, a4: Int, expected: Int ->
        expect
        assertThat(a1 + a2 + a3 + a4).isEqualTo(expected)
    }.where {
        "a1"    I   "a2"    I   "a3"    I   "a4"    II      "expected"
        1       I   2       I   3       I   4       II      10
    }

    @Spunit
    fun `should work with 6 columns`() =
        test("#a + #b + #c + #d + #e = #expected") { a: Int, b: Int, c: Int, d: Int, e: Int, expected: Int ->
            expect
            assertThat(a + b + c + d + e).isEqualTo(expected)
        }.where {
            "a" I "b"   I "c"   I "d"   I "e"   II  "expected"
            1   I 2     I 3     I 4     I 5     II  15
        }
}
