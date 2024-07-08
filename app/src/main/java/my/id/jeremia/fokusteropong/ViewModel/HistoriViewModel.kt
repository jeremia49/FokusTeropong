package my.id.jeremia.fokusteropong.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.first
import my.id.jeremia.fokusteropong.network.apis.result.ResultHistory
import my.id.jeremia.fokusteropong.repositories.MachineRepository
import javax.inject.Inject


@HiltViewModel
class HistoriViewModel @Inject constructor(
    val machineRepository: MachineRepository,
) : BaseViewModel() {


    private val _histori: MutableLiveData<ResultHistory?> = MutableLiveData(null)
    val histori: LiveData<ResultHistory?>
        get() = _histori

    companion object {
        val TAG = "HistoriViewModel"
    }

    fun getAllHistory(id:Int) {
        launchNetwork {
            val req = machineRepository
                .getHistory(id)
                .first()

            _histori.value = req
        }
    }

}