package my.id.jeremia.fokusteropong.network.apis.input


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AddKainInput(
    @Json(name = "nama")
    val nama: String? = null,
    @Json(name = "image")
    val image: String? = null,
)