package fpoly.huynkph38086.complex

import kotlin.math.PI
import kotlin.math.atan
import kotlin.math.sqrt

open class Complex {
    var re: Double
    var im: Double

    constructor(r: Number = 0, i: Imagi = i(0)) {
        this.re = r.toDouble()
        this.im = i.toReal()
    }

    constructor(r: Number = 0, i: Number = 0) {
        this.re = r.toDouble()
        this.im = i.toDouble()
    }

    fun mod(): Double {
        return sqrt(re*re + im*im)
    }

    fun arg(): Double {
        if (re > 0) return if (im>=0) atan(im/re)
        else 2 * PI + atan(im/re)
        if (re < 0) return PI + atan(im/re)
        if (im > 0) return PI / 2
        if (im < 0) return 3 * PI / 2
        @Suppress("DIVISION_BY_ZERO")
        return 0.0/0.0
    }

    fun cjg(): Complex {
        return c(re, -im)
    }

    override fun toString(): String {
        return (if (re != 0.0) "$re" + (if(im>0) "+" else "") else "") +
                if(im == 0.0) "" else "${i(im)}"
    }
}