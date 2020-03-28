package de.kneke.common.setting

import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.russhwolf.settings.AndroidSettings
import com.russhwolf.settings.Settings
import de.kneke.common.util.libContext

actual fun getSettingStorage(): Settings {
    val sharedPrefs: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(libContext!!)
    return AndroidSettings(sharedPrefs)
}