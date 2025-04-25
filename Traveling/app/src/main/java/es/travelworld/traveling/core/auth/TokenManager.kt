package es.travelworld.traveling.core.auth

import android.content.Context
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton
import androidx.core.content.edit

@Singleton
class TokenManager @Inject constructor(
    @ApplicationContext private val ctx: Context,
) {
    private val masterKeyAlias = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC)
    private val prefs = EncryptedSharedPreferences.create(
        "auth_prefs",
        masterKeyAlias,
        ctx,
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )

    companion object {
        private const val KEY_AUTH_TOKEN = "KEY_AUTH_TOKEN"
    }

    fun saveToken(token: String) {
        prefs.edit { putString(KEY_AUTH_TOKEN, token) }
    }

    fun clearToken() {
        prefs.edit { remove(KEY_AUTH_TOKEN) }
    }

    fun fetchToken(): String? {
       return prefs.getString(KEY_AUTH_TOKEN, null)
    }
}
