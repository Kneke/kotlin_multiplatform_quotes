package de.kneke.common.setting

import com.russhwolf.settings.ExperimentalJvm
import com.russhwolf.settings.JvmPropertiesSettings
import com.russhwolf.settings.Settings
import java.util.*

@ExperimentalJvm
actual fun getSettingStorage(): Settings {
    val properties = Properties()
    return JvmPropertiesSettings(properties)
}