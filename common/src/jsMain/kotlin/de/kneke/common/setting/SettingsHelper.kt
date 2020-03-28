package de.kneke.common.setting

import com.russhwolf.settings.JsSettings
import com.russhwolf.settings.Settings

actual fun getSettingStorage(): Settings = JsSettings()