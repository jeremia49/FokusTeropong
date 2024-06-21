package my.id.jeremia.fokusteropong.ViewModel

import android.os.Build
import android.os.StrictMode
import android.os.StrictMode.ThreadPolicy
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.first
import my.id.jeremia.fokusteropong.repositories.MachineRepository
import javax.inject.Inject


@HiltViewModel
class MenuViewModel @Inject constructor(
    val machineRepository: MachineRepository,
) : BaseViewModel() {

    private val _status: MutableLiveData<String> = MutableLiveData("UNKNOWN")
    val status: LiveData<String>
        get() = _status


    companion object {
        val TAG = "MenuViewModel"
    }

    suspend fun getStatus() {
        launchNetwork {
            val req = machineRepository.getStatus()
                .first()
            _status.postValue(req.status!!)
        }
    }

}