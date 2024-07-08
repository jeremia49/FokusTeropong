package my.id.jeremia.fokusteropong.network.apis.result


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ResultHistory(
    @Json(name = "data")
    val `data`: Data? = null,
    @Json(name = "status")
    val status: String? = null // ok
) {
    @JsonClass(generateAdapter = true)
    data class Data(
        @Json(name = "error_alat")
        val errorAlat: Int? = null, // 14
        @Json(name = "error_deteksi")
        val errorDeteksi: Int? = null, // 0
        @Json(name = "gambar")
        val gambar: List<Gambar?>? = null,
        @Json(name = "id")
        val id: Int? = null, // 7
        @Json(name = "nama")
        val nama: String? = null // Kerjaan 1
    ) {
        @JsonClass(generateAdapter = true)
        data class Gambar(
            @Json(name = "id")
            val id: Int? = null, // 1937
            @Json(name = "path")
            val path: String? = null,// history/XQB82FSKY3DBN1246EFIURAR24PGNL1A.jpg,
            @Json(name = "timestamp")
            val timestamp: Long? = null // image/jpeg
        )
    }
}