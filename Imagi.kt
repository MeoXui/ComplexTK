package fpoly.huynkph38086.complex

open class Imagi(private val value: Number = 0) {
    fun toReal() : Double {
        return value.toDouble()
    }

    fun toComp(): Complex {
        return c(0.0, this)
    }

    override fun toString(): String {
        return (when (toReal()) {
            -1.0 -> "-"
            1.0 -> ""
            else -> "${toReal()}"
        }) + if(toReal() == 0.0) "" else "i"
    }
}