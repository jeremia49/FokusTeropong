package my.id.jeremia.fokusteropong.DataStore

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking


private val Context.dataStore by preferencesDataStore(
    name = "LAST_SAVED"
)

val KAIN_KEY = stringPreferencesKey("kain")
val DETECTION_KEY = stringPreferencesKey("detect")
val IMGNETWORK_KEY = stringPreferencesKey("imgnetwork")


public fun saveKain(context: Context, kain: String, detectionKey: String, imgNetwork:String) {
    runBlocking {
        context.dataStore.edit { preferences ->
            preferences[KAIN_KEY] = kain
            preferences[DETECTION_KEY] = detectionKey
            preferences[IMGNETWORK_KEY] = imgNetwork
        }
    }
}

public fun getSavedKain(context: Context): String? {
    return runBlocking {
        val preferences = context.dataStore.data.first()
        preferences[KAIN_KEY]
    }
}

public fun getSavedDetection(context: Context): String? {
    return runBlocking {
        val preferences = context.dataStore.data.first()
        preferences[DETECTION_KEY]
    }
}

public fun getSavedImgNetwork(context: Context): String? {
    return runBlocking {
        val preferences = context.dataStore.data.first()
        preferences[IMGNETWORK_KEY]
    }
}

