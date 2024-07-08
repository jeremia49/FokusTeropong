package my.id.jeremia.fokusteropong.network.apis.result


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ResultKain(
    @Json(name = "data")
    val `data`: List<Data?>? = null,
    @Json(name = "status")
    val status: String? = null // ok
) {
    @JsonClass(generateAdapter = true)
    data class Data(
        @Json(name = "id")
        val id: Int? = null, // 3
        @Json(name = "nama")
        val nama: String? = null, // kainbagus
        @Json(name = "path")
        val path: String? = null // kain/1TS45MVTM73PF1QRX6AA23XDWK0J8LAK.jpg
    )
}