package my.id.jeremia.fokusteropong.network.apis

import android.graphics.Bitmap
import my.id.jeremia.fokusteropong.network.apis.result.Result
import retrofit2.Call
import retrofit2.http.GET

interface MachineAPI {

    @GET("/status")
    suspend fun getStatus():Result

    @GET("/statusDeteksi")
    suspend fun getStatusDeteksi():Result

    @GET("/statusSensor")
    suspend fun getStatusSensor():Result

    @GET("/start")
    suspend fun start():Result

    @GET("/stop")
    suspend fun stop():Result

    @GET("/jenisKain")
    suspend fun jenisKain():Result

    @GET("/image")
    suspend fun getImage(): Bitmap
}