package my.id.jeremia.fokusteropong.network.apis

import android.graphics.Bitmap
import retrofit2.Call
import retrofit2.http.GET

interface MachineAPI {

    @GET("/status")
    fun getStatus(): Call<String>

    @GET("/image")
    suspend fun getImage(): Bitmap
}