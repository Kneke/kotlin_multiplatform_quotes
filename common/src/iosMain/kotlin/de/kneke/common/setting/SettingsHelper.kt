package de.kneke.common.setting

import com.russhwolf.settings.Settings
import com.russhwolf.settings.AppleSettings
import platform.Foundation.NSUserDefaults

actual fun getSettingStorage(): Settings {
    val userDefault = NSUserDefaults.standardUserDefaults
    return AppleSettings(userDefault)
}