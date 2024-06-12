package fpoly.huynkph38086.complex

import kotlin.math.PI
import kotlin.math.atan
import kotlin.math.sqrt

open class Complex {
    var r: Double
    var i: Double

    constructor(r: Number, i: Imagi) {
        this.r = r.toDouble()
        this.i = i.toReal
    }

    constructor(r: Number, i: Number) {
        this.r = r.toDouble()
        this.i = i.toDouble()
    }

    companion object {
        val O = c(0.0, 0.0)
        val I = c(0.0, 1.0)
        @Suppress("DIVISION_BY_ZERO")
        val NULL = c(0.0/0.0, 0.0)
    }

    fun mod(): Double {
        return sqrt(r*r + i*i)
    }

    fun arg(): Double {
        if (r > 0) return if (i>=0) atan(i/r)
        else 2 * PI + atan(i/r)
        if (r < 0) return PI + atan(i/r)
        if (i > 0) return PI / 2
        if (i < 0) return 3 * PI / 2
        @Suppress("DIVISION_BY_ZERO")
        return 0.0/0.0
    }

    fun cjg(): Complex {
        return c(r, -i)
    }

    fun isNull(): Boolean {
        return this == NULL
    }

    override fun toString(): String {
        return (if (r != 0.0) "$r" + (if(i>0) "+" else "") else "") +
                if(i == 0.0) "" else "${i(i)}"
    }
}