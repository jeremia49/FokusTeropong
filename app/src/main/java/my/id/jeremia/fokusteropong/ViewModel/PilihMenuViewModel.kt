package my.id.jeremia.fokusteropong.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import my.id.jeremia.fokusteropong.network.apis.result.Result
import my.id.jeremia.fokusteropong.network.apis.result.ResultKain
import my.id.jeremia.fokusteropong.repositories.MachineRepository
import javax.inject.Inject

@HiltViewModel
class PilihMenuViewModel @Inject constructor(
    val machineRepository: MachineRepository,
) : BaseViewModel() {


    private val _kains: MutableLiveData<ResultKain?> = MutableLiveData(null)
    val kain: LiveData<ResultKain?>
        get() = _kains

    init {
        getAllKain()
    }

    companion object {
        val TAG = "NewActivityViewModel"
    }

    fun getAllKain() {
        launchNetwork {
            val req = machineRepository
                .getAllKain()
                .first()

            _kains.postValue(
                req
            )

        }
    }

    fun deleteKain(id: Int) {
        launchNetwork {
            val req = machineRepository
                .deleteKain(id)
                .first()

            getAllKain()
        }
    }

    suspend fun addNewHistory(nama:String):Result{
        return machineRepository
            .addNewHistory(nama)
            .first()

    }

}