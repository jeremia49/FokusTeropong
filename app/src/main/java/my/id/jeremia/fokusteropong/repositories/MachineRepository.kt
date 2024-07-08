package my.id.jeremia.fokusteropong.repositories

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import my.id.jeremia.fokusteropong.network.apis.MachineAPI
import my.id.jeremia.fokusteropong.network.apis.input.AddKainInput
import my.id.jeremia.fokusteropong.network.apis.result.ResultHistoryUtama
import my.id.jeremia.fokusteropong.network.apis.result.Result
import my.id.jeremia.fokusteropong.network.apis.result.ResultHistory
import my.id.jeremia.fokusteropong.network.apis.result.ResultKain
import javax.inject.Inject


class MachineRepository @Inject constructor(
    private val machineAPI: MachineAPI
) {

    suspend fun getStatus(): Flow<Result> =
        flow {
            emit(machineAPI.getStatus())
        }

    suspend fun getStatusDeteksi(): Flow<Result> =
        flow {
            emit(machineAPI.getStatusDeteksi())
        }

    suspend fun getStatusSensor(): Flow<Result> =
        flow {
            emit(machineAPI.getStatusSensor())
        }

    suspend fun start(): Flow<Result> =
        flow {
            emit(machineAPI.start())
        }

    suspend fun stop(): Flow<Result> =
        flow {
            emit(machineAPI.stop())
        }


    suspend fun jenisKain(): Flow<Result> =
        flow {
            emit(machineAPI.jenisKain())
        }


    suspend fun addKain(nama: String, image: String): Flow<Result> =
        flow {
            emit(
                machineAPI.addKain(
                    AddKainInput(
                        nama,
                        image,
                    )
                )
            )
        }

    suspend fun getAllKain(): Flow<ResultKain> = flow{
        emit(
            machineAPI.getAllKains()
        )
    }
    suspend fun getAllHistory(): Flow<ResultHistoryUtama> = flow{
        emit(
            machineAPI.getAllHistory()
        )
    }

    suspend fun getHistory(id:Int): Flow<ResultHistory> = flow{
        emit(
            machineAPI.getHistory(id)
        )
    }


    suspend fun deleteKain(idKain:Int): Flow<Result> = flow{
        emit(
            machineAPI.deleteKain(idKain)
        )
    }

    suspend fun deleteAllHistory():Flow<Result> = flow{
        emit(
            machineAPI.deleteAllHistory()
        )
    }

    suspend fun addNewHistory(nama:String): Flow<Result> = flow{
        emit(
            machineAPI.addNewHistory(nama)
        )
    }

    suspend fun setHistoryID(id:Int) : Flow<Result> = flow{
        emit(
            machineAPI.setHistoryID(id)
        )
    }




}