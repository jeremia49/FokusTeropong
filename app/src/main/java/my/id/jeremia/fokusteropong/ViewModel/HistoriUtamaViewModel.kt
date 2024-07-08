package my.id.jeremia.fokusteropong.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.first
import my.id.jeremia.fokusteropong.network.apis.result.ResultHistory
import my.id.jeremia.fokusteropong.network.apis.result.ResultHistoryUtama
import my.id.jeremia.fokusteropong.repositories.MachineRepository
import javax.inject.Inject

@HiltViewModel
class HistoriUtamaViewModel  @Inject constructor(
    val machineRepository: MachineRepository,
) : BaseViewModel() {


    private val _histori: MutableLiveData<ResultHistoryUtama?> = MutableLiveData(null)
    val histori: LiveData<ResultHistoryUtama?>
        get() = _histori

    init {
        getAllHistory()
    }

    companion object {
        val TAG = "HistoriUtamaViewModel"
    }

    fun getAllHistory() {
        launchNetwork {
            val req = machineRepository
                .getAllHistory()
                .first()

            _histori.value = req
        }
    }

    fun resetHistory(){
        launchNetwork {
            val req = machineRepository
                .deleteAllHistory()
                .first()
            getAllHistory()
        }
    }


}