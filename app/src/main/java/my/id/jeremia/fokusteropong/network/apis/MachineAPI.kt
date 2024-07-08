package my.id.jeremia.fokusteropong.network.apis

import android.graphics.Bitmap
import my.id.jeremia.fokusteropong.network.apis.input.AddKainInput
import my.id.jeremia.fokusteropong.network.apis.result.ResultHistoryUtama
import my.id.jeremia.fokusteropong.network.apis.result.Result
import my.id.jeremia.fokusteropong.network.apis.result.ResultHistory
import my.id.jeremia.fokusteropong.network.apis.result.ResultKain
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

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

    @POST("/addKain")
    suspend fun addKain(@Body request: AddKainInput):Result

    @GET("/kains")
    suspend fun getAllKains(): ResultKain

    @GET("/deleteKain/{idkain}")
    suspend fun deleteKain(@Path("idkain") idkain: Int): Result

    @GET("/history")
    suspend fun getAllHistory(): ResultHistoryUtama

    @GET("/getHistory/{id}")
    suspend fun getHistory(@Path("id") id: Int): ResultHistory

    @GET("/resethistory")
    suspend fun deleteAllHistory() : Result

    @GET("/addHistory/{nama}")
    suspend fun addNewHistory(@Path("nama") nama:String) : Result
    @GET("/setHistoriID/{id}")
    suspend fun setHistoryID(@Path("id") id:Int) : Result


}