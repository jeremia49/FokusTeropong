package my.id.jeremia.fokusteropong.repositories

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import my.id.jeremia.fokusteropong.network.apis.MachineAPI
import retrofit2.Call
import javax.inject.Inject


class MachineRepository @Inject constructor(
    private val machineAPI: MachineAPI
){
    suspend fun getStatus():Call<String> = machineAPI.getStatus()



}