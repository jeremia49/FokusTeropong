package my.id.jeremia.fokusteropong.repositories

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import my.id.jeremia.fokusteropong.network.apis.MachineAPI
import my.id.jeremia.fokusteropong.network.apis.result.Result
import retrofit2.Call
import retrofit2.http.GET
import javax.inject.Inject


class MachineRepository @Inject constructor(
    private val machineAPI: MachineAPI
){

    suspend fun getStatus():Flow<Result> =
        flow {
            emit(machineAPI.getStatus())
        }

    suspend fun getStatusDeteksi():Flow<Result> =
        flow {
            emit(machineAPI.getStatusDeteksi())
        }

    suspend fun getStatusSensor():Flow<Result> =
        flow {
            emit(machineAPI.getStatusSensor())
        }

    suspend fun start():Flow<Result> =
        flow {
            emit(machineAPI.start())
        }

    suspend fun stop():Flow<Result> =
        flow {
            emit(machineAPI.stop())
        }


    suspend fun jenisKain():Flow<Result> =
        flow {
            emit(machineAPI.jenisKain())
        }





}