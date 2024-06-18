package fpoly.huynkph38086.complex

import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.pow
import kotlin.math.round
import kotlin.math.sin

val I = i()
val O = c(0,0)

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

//

fun Imagi.pow(n: Number): APC {
    val d = n.toDouble()
    val k0 = if (toReal() != 0.0) toReal().pow(d).arg(PI / 2 * d * if (toReal() > 0) 1 else 3)
    else O
    return APC(k0, d)
}

fun Complex.pow(n: Number): APC {
    val d = n.toDouble()
    val k0 = mod().pow(d).arg(arg() * d)
    return APC(k0, d)
}

fun sqrt(n: Number): Any {
    val d = n.toDouble()
    return if (d >= 0) kotlin.math.sqrt(d)
    else i(kotlin.math.sqrt(-d))
}

fun sqrt(c: Complex): APC {
    return c.pow(1.0 / 2.0)
}

fun cbrt(c: Complex): APC {
    return c.pow(1.0 / 3.0)
}

class APC(private var k0: Complex, private var exp: Double) : Complex(k0.re, k0.im) {
    operator fun get(k: Int): Complex {
        return if (k0 == O || k == 0) k0
        else k0.mod().arg(k0.arg() + (exp % 1.0) * k)
    }
}

fun Number.toComplex(): Complex {
    return this.arg(0.0)
}

fun Number.arg(arg: Double): Complex {
    val x = if ((arg + PI / 2) % PI == 0.0) 0.0 else cos(arg)
    val y = if (arg % PI == 0.0) 0.0 else sin(arg)
    return roundf14(this.toDouble() * x) +
            roundf14(this.toDouble() * y) * I
}

fun roundf14(d: Double): Double {
    return round(d * 1e14) / 1e14
}