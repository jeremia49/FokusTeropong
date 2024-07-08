package my.id.jeremia.fokusteropong.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.first
import my.id.jeremia.fokusteropong.repositories.MachineRepository
import javax.inject.Inject

@HiltViewModel
class NewActivityViewModel @Inject constructor(
    val machineRepository: MachineRepository,
) : BaseViewModel() {

    companion object {
        val TAG = "NewActivityViewModel"
    }

    fun addKain(nama: String, image: String) {
        launchNetwork {
            val req = machineRepository
                .addKain(nama,image)
                .first()
            println(req.status);
        }
    }

}