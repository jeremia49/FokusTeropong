package my.id.jeremia.fokusteropong

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import my.id.jeremia.fokusteropong.init.CoilInitializer
import javax.inject.Inject


@HiltAndroidApp
class FokusTeropongApp : Application() {

    companion object {
        const val TAG = "PotholeTrackerApp"
    }

    @Inject
    lateinit var coilInit: CoilInitializer

    override fun onCreate() {
        super.onCreate()

        coilInit.init()
    }
}