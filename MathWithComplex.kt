package fpoly.huynkph38086.complex

import kotlin.math.E
import kotlin.math.PI
import kotlin.math.abs
import kotlin.math.cos
import kotlin.math.ln
import kotlin.math.pow
import kotlin.math.round
import kotlin.math.sin

val I = i()
val O = c(0, 0)

fun i(value: Number = 1): Imagi {
    return Imagi(value)
}

fun c(r: Number = 0, i: Imagi = i(0)): Complex {
    return Complex(r, i)
}

fun c(r: Number = 0, i: Number = 0): Complex {
    return Complex(r, i)
}

operator fun Number.plus(i: Imagi): Complex {
    return c(this, i)
}

operator fun Number.plus(c: Complex): Complex {
    return this.toDouble() + c.re + c.im
}

operator fun Imagi.plus(n: Number): Complex {
    return n + this
}

operator fun Imagi.plus(i: Imagi): Imagi {
    return i(toReal() + i.toReal())
}

operator fun Imagi.plus(c: Complex): Complex {
    return c.re + (this + c.im)
}

operator fun Complex.plus(n: Number): Complex {
    return re + n.toDouble() + im
}

operator fun Complex.plus(i: Imagi): Complex {
    return re + (im + i)
}

operator fun Complex.plus(c: Complex): Complex {
    return re + c.re + (im + c.im)
}

operator fun Number.minus(i: Imagi): Complex {
    return c(this, -i)
}

operator fun Number.minus(c: Complex): Complex {
    return toDouble() - c.re - c.im
}

operator fun Imagi.minus(n: Number): Complex {
    return -n.toDouble() + this
}

operator fun Imagi.minus(i: Imagi): Imagi {
    return i(toReal() - i.toReal())
}

operator fun Imagi.minus(c: Complex): Complex {
    return -c.re + (this - c.im)
}

operator fun Complex.minus(n: Number): Complex {
    return re - n.toDouble() + im
}

operator fun Complex.minus(i: Imagi): Complex {
    return re + (im - i)
}

operator fun Complex.minus(c: Complex): Complex {
    return re - c.re + (im - c.im)
}

operator fun Imagi.unaryMinus(): Imagi {
    return i(-toReal())
}

operator fun Complex.unaryMinus(): Complex {
    return -re - im
}

operator fun Number.times(i: Imagi): Imagi {
    return i(toDouble() * i.toReal())
}

operator fun Number.times(c: Complex): Complex {
    return this.toDouble() * c.re + this.toDouble() * c.im
}

operator fun Imagi.times(n: Number): Imagi {
    return i(toReal() * n.toDouble())
}

operator fun Imagi.times(i: Imagi): Double {
    return -toReal() * i.toReal()
}

operator fun Imagi.times(c: Complex): Complex {
    return this * c.im + this * c.re
}

operator fun Complex.times(n: Number): Complex {
    return re * n.toDouble() + im * n.toDouble()
}

operator fun Complex.times(i: Imagi): Complex {
    return im * i + re * i
}

operator fun Complex.times(c: Complex): Complex {
    val r = re * c.re + im * c.im
    val i = re * c.im + im * c.re
    return r + i
}

operator fun Number.div(i: Imagi): Imagi {
    return i(toDouble() / -i.toReal())
}

operator fun Number.div(c: Complex): Complex {
    return this * rec(c)
}

operator fun Imagi.div(n: Number): Imagi {
    return i(toReal() / n.toDouble())
}

operator fun Imagi.div(i: Imagi): Double {
    return toReal() / i.toReal()
}

operator fun Imagi.div(c: Complex): Complex {
    return this * rec(c)
}

operator fun Complex.div(n: Number): Complex {
    return re / n.toDouble() + im / n.toDouble()
}

operator fun Complex.div(i: Imagi): Complex {
    return im / i + re / i
}

operator fun Complex.div(c: Complex): Complex {
    return this * rec(c)
}

fun rec(c: Complex): Complex {
    return c.cjg() / (c.re * c.re - c.im * c.im)
}

//fun Number.pow(n: Number): MultivaluedResult {
//    val d = n.toDouble()
//    val k0 = if (toDouble() > 0) E.pow(ln(toDouble())*d) else if (toDouble() < 0) E.pow(ln(toDouble())*d)
//    return
//}
// a^b = E^(ln(a)*b + 2*k*PI*b*i)
// (-a)^b = E^(ln(a)*b + PI*b*i + 2*k*PI*b*i)

fun Number.pow(i: Imagi): MultivaluedResult {
    val d = i.toReal()
    val k0 = (if (toDouble() > 0) 1 else if (toDouble() < 0) 1 / E.pow(PI * d) else 0)
        .arg(ln(abs(toDouble())) * d)
    return MR(k0, d, 2)
}
// a = E^(ln(a) + 2*k*PI*i)
// a^(di) = E^(ln(a)*d*i - 2*k*PI*d)
//        = E^(ln(a)*d*i) / E^(2*k*PI*d)
//        = cos(ln(a)*d) + sin(ln(a)*d)i / E^(2*k*PI*d)
// k0 = cos(ln(a)*d) + sin(ln(a)*d)i = 1 arg(ln(a)*d)

// -a = E^(ln(a) + PI*i + 2*k*PI*i)
// (-a)^(di) = E^(ln(a)*d*i - PI*d - 2*k*k*PI*d)
//        = E^(ln(a)*d*i) / E^(PI*d) / E^(2*k*PI*d)
//        = cos(ln(a)*d) + sin(ln(a)*d)i / E^(PI*d) / E^((2*k+1)*PI*d)
// k0 = cos(ln(a)*d) + sin(ln(a)*d)i / E^(PI*d) = (1/E^(PI*d)) arg(ln(a)*d)

//fun Number.pow(c: Complex): MultivaluedResult {
//    return pow(c.re)
//}

fun Imagi.pow(n: Number): MultivaluedResult {
    val d = n.toDouble()
    val k0 = if (toComplex() != O) toReal().pow(d).arg(PI / 2 * d) else O
    return MR(k0, d, 1)
}

fun Complex.pow(n: Number): MultivaluedResult {
    val d = n.toDouble()
    val k0 = if (this != O) mod().pow(d).arg(arg() * d) else O
    return MR(k0, d, 1)
}

fun sqrt(n: Number): Any {
    val d = n.toDouble()
    return if (d >= 0) kotlin.math.sqrt(d)
    else i(kotlin.math.sqrt(-d))
}

fun sqrt(c: Complex): MultivaluedResult {
    return c.pow(1.0 / 2.0)
}

fun cbrt(c: Complex): MultivaluedResult {
    return c.pow(1.0 / 3.0)
}

fun MR(k0: Complex, exp: Double, mode: Int): MultivaluedResult {
    return MultivaluedResult(k0, exp, mode)
}

class MultivaluedResult(private var k0: Complex, private var exp: Double, private val mode: Int) :
    Complex(k0.re, k0.im) {
    operator fun get(k: Int): Complex {
        if (k0 == O || k == 0) return k0
        return when (mode) {
            0 -> k0*1//
            1 -> mod().arg(arg() + (exp % 1.0) * k)
            2 -> k0 / E.pow(2 * k * PI * exp)
            else -> k0
        }
    }
}

fun Number.toComplex(): Complex {
    return this.arg(0.0)
}

fun Number.arg(arg: Number): Complex {
    val x = if ((arg.toDouble() + PI / 2) % PI == 0.0) 0.0 else cos(arg.toDouble())
    val y = if (arg.toDouble() % PI == 0.0) 0.0 else sin(arg.toDouble())
    return roundf14(toDouble() * x) +
            roundf14(toDouble() * y) * I
}

fun roundf14(d: Double): Double {
    return round(d * 1e14) / 1e14
}