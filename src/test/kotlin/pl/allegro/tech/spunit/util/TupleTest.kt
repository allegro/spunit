package pl.allegro.tech.spunit.util

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test

class TupleTest {
    @Test
    fun `should flatten to triple`() {
        val nestedPairs = listOf(
            1 to 2 to 3,
            4 to 5 to 6,
            7 to 8 to 9,
        )

        assertThat(nestedPairs.flattenTriple())
            .isEqualTo(
                listOf(
                    Triple(1, 2, 3),
                    Triple(4, 5, 6),
                    Triple(7, 8, 9)
                )
            )
    }

    @Test
    fun `should flatten to quadruple`() {
        val nestedPairs = listOf(
            1 to 2 to 3 to 4,
            5 to 6 to 7 to 8,
            9 to 10 to 11 to 12
        )

        assertThat(nestedPairs.flattenQuadruple())
            .isEqualTo(
                listOf(
                    Quadruple(1, 2, 3, 4),
                    Quadruple(5, 6, 7, 8),
                    Quadruple(9, 10, 11, 12)
                )
            )
    }

    @Test
    fun `should flatten to quintuple`() {
        val nestedPairs = listOf(
            1 to 2 to 3 to 4 to 5,
            6 to 7 to 8 to 9 to 10,
            11 to 12 to 13 to 14 to 15
        )

        assertThat(nestedPairs.flattenQuintuple())
            .isEqualTo(
                listOf(
                    Quintuple(1, 2, 3, 4, 5),
                    Quintuple(6, 7, 8, 9, 10),
                    Quintuple(11, 12, 13, 14, 15)
                )
            )
    }

    @Test
    fun `should flatten to sextuple`() {
        val nestedPairs = listOf(
            1 to 2 to 3 to 4 to 5 to 6,
            7 to 8 to 9 to 10 to 11 to 12,
            13 to 14 to 15 to 16 to 17 to 18,
        )

        assertThat(nestedPairs.flattenSextuple())
            .isEqualTo(
                listOf(
                    Sextuple(1, 2, 3, 4, 5, 6),
                    Sextuple(7, 8, 9, 10, 11, 12),
                    Sextuple(13, 14, 15, 16, 17, 18)
                )
            )
    }
}
