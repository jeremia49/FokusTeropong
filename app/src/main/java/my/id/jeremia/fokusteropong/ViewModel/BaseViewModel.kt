package my.id.jeremia.fokusteropong.ViewModel
import my.id.jeremia.fokusteropong.tools.toApiErrorResponse
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


abstract class BaseViewModel(

) : ViewModel() {

    companion object {
        const val TAG = "BaseViewModel"
    }

    protected fun launchNetwork(
        error: (String) -> Unit = {},
        finish:()->Unit={},
        block: suspend CoroutineScope.() -> Unit
    ) {

        viewModelScope.launch {
            try {
                block()
            } catch (e: Throwable) {
                if (e is CancellationException) return@launch
                val errorResponse = e.toApiErrorResponse()
                error(errorResponse)
                e.message?.let { Log.e(TAG, it) }
            } finally {
                finish();
            }
        }
    }

}