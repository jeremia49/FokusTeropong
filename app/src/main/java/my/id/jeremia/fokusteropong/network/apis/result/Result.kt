package my.id.jeremia.fokusteropong.network.apis.result


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Result(
    @Json(name = "data")
    val `data`: String?,
    @Json(name = "status")
    val status: String?
)