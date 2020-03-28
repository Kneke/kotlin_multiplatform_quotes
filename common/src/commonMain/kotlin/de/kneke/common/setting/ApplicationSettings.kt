package de.kneke.common.setting

class ApplicationSettings : Settings {

    private val settingStorage = getSettingStorage()

    override fun clear() {
        settingStorage.clear()
    }

    override fun remove(key: String) {
        settingStorage.remove(key)
    }

    override fun hasKey(key: String): Boolean {
        return settingStorage.hasKey(key)
    }

    /* BOOLEAN */
    override fun putBoolean(key: String, value: Boolean) {
        settingStorage.putBoolean(key, value)
    }

    override fun getBoolean(key: String, defaultValue: Boolean?): Boolean? {
        defaultValue?.let {
            return settingStorage.getBoolean(key, defaultValue)
        }
        return settingStorage.getBooleanOrNull(key)
    }

    /* INT */
    override fun putInt(key: String, value: Int) {
        settingStorage.putInt(key, value)
    }

    override fun getInt(key: String, defaultValue: Int?): Int? {
        defaultValue?.let {
            return settingStorage.getInt(key, defaultValue)
        }
        return settingStorage.getIntOrNull(key)
    }

    /* LONG */
    override fun putLong(key: String, value: Long) {
        settingStorage.putLong(key, value)
    }

    override fun getLong(key: String, defaultValue: Long?): Long? {
        defaultValue?.let {
            return settingStorage.getLong(key, defaultValue)
        }
        return settingStorage.getLongOrNull(key)
    }

    /* FLOAT */
    override fun putFloat(key: String, value: Float) {
        settingStorage.putFloat(key, value)
    }

    override fun getFloat(key: String, defaultValue: Float?): Float? {
        defaultValue?.let {
            return settingStorage.getFloat(key, defaultValue)
        }
        return settingStorage.getFloatOrNull(key)
    }

    /* DOUBLE */
    override fun putDouble(key: String, value: Double) {
        settingStorage.putDouble(key, value)
    }

    override fun getDouble(key: String, defaultValue: Double?): Double? {
        defaultValue?.let {
            return settingStorage.getDouble(key, defaultValue)
        }
        return settingStorage.getDoubleOrNull(key)
    }

    /* STRING */
    override fun putString(key: String, value: String) {
        settingStorage.putString(key, value)
    }

    override fun getString(key: String, defaultValue: String?): String? {
        defaultValue?.let {
            return settingStorage.getString(key, defaultValue)
        }
        return settingStorage.getStringOrNull(key)
    }
}