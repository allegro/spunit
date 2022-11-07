package pl.allegro.tech.spunit.util

data class Quadruple<out A, out B, out C, out D>(
    val first: A,
    val second: B,
    val third: C,
    val fourth: D
) {
    override fun toString(): String = "($first, $second, $third, $fourth)"
}

data class Quintuple<out A, out B, out C, out D, out E>(
    val first: A,
    val second: B,
    val third: C,
    val fourth: D,
    val fifth: E,
) {
    override fun toString(): String = "($first, $second, $third, $fourth, $fifth)"
}

data class Sextuple<out A, out B, out C, out D, out E, out F>(
    val first: A,
    val second: B,
    val third: C,
    val fourth: D,
    val fifth: E,
    val sixth: F,
) {
    override fun toString(): String = "($first, $second, $third, $fourth, $fifth, $sixth)"
}

fun <P1, P2, P3> List<Pair<Pair<P1, P2>, P3>>.flattenTriple(): List<Triple<P1, P2, P3>> = map { Triple(it.first.first, it.first.second, it.second) }

fun <P1, P2, P3, P4> List<Pair<Pair<Pair<P1, P2>, P3>, P4>>.flattenQuadruple(): List<Quadruple<P1, P2, P3, P4>> = map { Quadruple(it.first.first.first, it.first.first.second, it.first.second, it.second) }

fun <P1, P2, P3, P4, P5> List<Pair<Pair<Pair<Pair<P1, P2>, P3>, P4>, P5>>.flattenQuintuple(): List<Quintuple<P1, P2, P3, P4, P5>> = map { Quintuple(it.first.first.first.first, it.first.first.first.second, it.first.first.second, it.first.second, it.second) }

fun <P1, P2, P3, P4, P5, P6> List<Pair<Pair<Pair<Pair<Pair<P1, P2>, P3>, P4>, P5>, P6>>.flattenSextuple(): List<Sextuple<P1, P2, P3, P4, P5, P6>> = map { Sextuple(it.first.first.first.first.first, it.first.first.first.first.second, it.first.first.first.second, it.first.first.second, it.first.second, it.second) }

