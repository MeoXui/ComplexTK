package fpoly.huynkph38086.complex

import kotlin.math.PI
import kotlin.math.atan
import kotlin.math.sqrt

open class Complex {
    var re: Double
    var im: Imagi

    constructor(r: Number = 0, i: Imagi = i(0)) {
        this.re = r.toDouble()
        this.im = i
    }

    constructor(r: Number = 0, i: Number = 0) {
        this.re = r.toDouble()
        this.im = i(i)
    }

    fun mod(): Double {
        return sqrt(re*re - im*im)
    }

    fun arg(): Double {
        if (re > 0) return if (im.toReal()>=0) atan(im.toReal()/re)
        else 2 * PI + atan(im.toReal()/re)
        if (re < 0) return PI + atan(im.toReal()/re)
        if (im.toReal() > 0) return PI / 2
        if (im.toReal() < 0) return 3 * PI / 2
        @Suppress("DIVISION_BY_ZERO")
        return 0.0/0.0
    }

    fun cjg(): Complex {
        return c(re, -im)
    }

    override operator fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Complex

        if (re != other.re) return false
        if (im != other.im) return false

        return true
    }

    override fun hashCode(): Int {
        var result = re.hashCode()
        result = 31 * result + im.hashCode()
        return result
    }

    override fun toString(): String {
        return (if (re != 0.0) "$re" + (if(im.toReal()>0) "+" else "") else "") +
                if(im.toReal() == 0.0) "" else "$im"
    }
}