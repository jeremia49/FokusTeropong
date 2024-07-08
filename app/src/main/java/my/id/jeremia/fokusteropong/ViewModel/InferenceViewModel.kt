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
class InferenceViewModel @Inject constructor(
    val machineRepository: MachineRepository,
) : BaseViewModel() {

    private val _status: MutableLiveData<String> = MutableLiveData("UNKNOWN")
    val status: LiveData<String>
        get() = _status


    companion object {
        val TAG = "InferenceViewModel"
    }

    suspend fun getImage() {

    }

    suspend fun getStatus() {
        println("Getting status ...");
        launchNetwork {
            val req = machineRepository.getStatus()
                .first()
//            if(req.status == "Gambar Salah!" ||req.status == "Gambar Benar!" ){
//                _status.postValue("${req.status!!}\n${req.data!!}")
//            }else{
                _status.postValue(req.status!!)
//            }
        }
    }

    suspend fun start(){
        launchNetwork {
            val req = machineRepository.start()
                .first()
        }
    }
    suspend fun stop(){
        launchNetwork {
            val req = machineRepository.stop()
                .first()
        }
    }

    suspend fun setHistoryID(id:Int){
        launchNetwork {
            val req = machineRepository.setHistoryID(id)
                .first()
        }
    }

}