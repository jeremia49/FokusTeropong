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


public fun saveKain(context: Context, kain: String) {
    runBlocking {
        context.dataStore.edit { preferences ->
            preferences[KAIN_KEY] = kain
        }
    }
}

public fun getSavedKain(context: Context): String? {
    return runBlocking {
        val preferences = context.dataStore.data.first()
        preferences[KAIN_KEY]
    }
}