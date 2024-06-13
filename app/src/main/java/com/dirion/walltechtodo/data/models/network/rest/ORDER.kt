package com.dirion.walltechtodo.data.models.network.rest

data class ORDER(
    val id: String = "",
    val acceptance_date: String = "",
    val binding_type: String = "",
    val completion_date: String = "",
    val customer: String = "",
    val edition: String = "",
    val employer: String = "",
    val format: String = "",
    val pages_count: Int = 0,
    val paper_type: String = "",
    val prepayment: Boolean = false,
    val printing: Int = 0,
    val product_price: Int = 0,
    val product_type: String = "",
    val tipography_id: String = "",
){
    enum class Format(val value: String) {
        a("84x108/8"),
        b("70x108/8"),
        c("70x100/8"),
        d("60x90/8"),
        e("60x84/8"),
        f("84x108/16"),
        g("70x108/16"),
        h("70x100/16"),
        i("70x90/16"),
        j("60x90/16"),
        k("60x84/16");

        companion object {
            fun convertToFormat(format: String): Format {
                return when(format) {
                    "84x108/8"  -> a
                    "70x108/8"  -> b
                    "70x100/8"  -> c
                    "60x90/8"   -> d
                    "60x84/8"   -> e
                    "84x108/16" -> f
                    "70x108/16" -> g
                    "70x100/16" -> h
                    "70x90/16"  -> i
                    "60x90/16"  -> j
                    "60x84/16"  -> k

                    else -> a
                }
            }

            fun convertToString(format: Format): String {
                return when(format) {
                    a -> "84x108/8"
                    b -> "70x108/8"
                    c -> "70x100/8"
                    d -> "60x90/8"
                    e -> "60x84/8"
                    f -> "84x108/16"
                    g -> "70x108/16"
                    h -> "70x100/16"
                    i -> "70x90/16"
                    j -> "60x90/16"
                    k -> "60x84/16"

                    else -> "84x108/8"
                }

            }
        }

    }
}


