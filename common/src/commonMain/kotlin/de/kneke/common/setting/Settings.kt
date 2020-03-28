package de.kneke.common.setting

interface Settings {

    fun clear()

    fun remove(key: String)

    fun hasKey(key: String): Boolean

    fun putBoolean(key: String, value: Boolean)

    fun getBoolean(key: String, defaultValue: Boolean? = false): Boolean?

    fun putInt(key: String, value: Int)

    fun getInt(key: String, defaultValue: Int? = 0): Int?

    fun putLong(key: String, value: Long)

    fun getLong(key: String, defaultValue: Long? = 0): Long?

    fun putFloat(key: String, value: Float)

    fun getFloat(key: String, defaultValue: Float? = 0f): Float?

    fun putDouble(key: String, value: Double)

    fun getDouble(key: String, defaultValue: Double? = 0.0): Double?

    fun putString(key: String, value: String)

    fun getString(key: String, defaultValue: String? = ""): String?
}