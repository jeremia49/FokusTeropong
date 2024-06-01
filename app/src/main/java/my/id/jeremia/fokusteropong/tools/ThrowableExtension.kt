package my.id.jeremia.fokusteropong.tools


import android.util.Log
import retrofit2.HttpException
import java.io.IOException
import java.net.ConnectException

const val THROWABLE_API_ERROR_TAG = "THROWABLE_API_ERROR"

fun Throwable.toApiErrorResponse(): String {
    val defaultResponse = "Unknown Error!"
    try {
        return when (this) {
            is ConnectException ->
                return "Koneksi Gagal"

            is IOException ->
                return "Tidak ada koneksi"

            is HttpException -> {
                return this.response()?.body().toString() ?: ""
            }

            else -> {
                return defaultResponse
            }
        }
    } catch (e: IOException) {
        Log.e(THROWABLE_API_ERROR_TAG, e.toString())
    } catch (e: NullPointerException) {
        Log.e(THROWABLE_API_ERROR_TAG, e.toString())
    }
    return defaultResponse
}