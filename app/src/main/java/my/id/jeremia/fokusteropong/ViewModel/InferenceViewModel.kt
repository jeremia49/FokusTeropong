package my.id.jeremia.fokusteropong.ViewModel

import android.os.Build
import android.os.StrictMode
import android.os.StrictMode.ThreadPolicy
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import my.id.jeremia.fokusteropong.repositories.MachineRepository
import javax.inject.Inject


@HiltViewModel
class InferenceViewModel @Inject constructor(
    val machineRepository: MachineRepository,
):BaseViewModel() {

    private val _status : MutableLiveData<String> = MutableLiveData("UNKNOWN")
    val status : LiveData<String>
        get() = _status


    companion object{
        val TAG = "InferenceViewModel"
    }

    suspend fun getImage(){

    }

    suspend fun getStatus(){
//        launchNetwork {
//
//        }
        val SDK_INT = Build.VERSION.SDK_INT
        if (SDK_INT > 8) {
            val policy = ThreadPolicy.Builder()
                .permitAll().build()
            StrictMode.setThreadPolicy(policy)

            //your codes here

            val req = machineRepository.getStatus()
            val res = req.execute();
            val value = res.body() ?: ""
            _status.postValue(value)
        }
//            .collect{
//                val response = it.execute()
//                val value = response.body() ?: ""
//                _status.postValue(value)
//                Log.d(TAG, it.toString())
//            }
    }

}