package my.id.jeremia.fokusteropong.network.apis.result


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ResultHistoryUtama(
    @Json(name = "data")
    val `data`: List<Data?>? = null,
    @Json(name = "status")
    val status: String? = null // ok
) {
    @JsonClass(generateAdapter = true)
    data class Data(
        @Json(name = "id")
        val id: Int? = null, // 1
        @Json(name = "nama")
        val nama: String? = null // Kerjaan 1
    )
}