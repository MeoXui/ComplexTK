package fpoly.huynkph38086.complex

open class Imagi(private val value: Number = 0) {
    fun toReal() : Double {
        return value.toDouble()
    }

    fun toComplex(): Complex {
        return c(0.0, this)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Imagi

        return value == other.value
    }

    override fun hashCode(): Int {
        return value.hashCode()
    }

    override fun toString(): String {
        return (when (toReal()) {
            -1.0 -> "-"
            1.0 -> ""
            else -> "${toReal()}"
        }) + if(toReal() == 0.0) "" else "i"
    }
}