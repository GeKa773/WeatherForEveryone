package com.gekaradchenko.weatherforeveryone.preferences

import android.content.Context
import android.content.SharedPreferences

private const val PREFERENCES_SETTING = "setting"
private const val LOCAL_SETTING = "local"
private const val MODE_SETTING = "mode"
const val ENG = "en"
const val RUS = "ru"


class PreferencesSetting(context: Context) {

    private val sharedSetting: SharedPreferences =
        context.getSharedPreferences(PREFERENCES_SETTING, Context.MODE_PRIVATE)
    private val editor: SharedPreferences.Editor =
        context.getSharedPreferences(PREFERENCES_SETTING, Context.MODE_PRIVATE).edit()


    suspend fun getDefaultLanguage(): String =
        sharedSetting.getString(LOCAL_SETTING, ENG).toString()

    suspend fun saveDefaultLanguage(language: String) {
        editor.putString(LOCAL_SETTING, language)
        editor.apply()
    }

    suspend fun getDefaultMode(): Boolean =
        sharedSetting.getBoolean(MODE_SETTING, false)

    suspend fun saveDefaultMode(mode: Boolean) {
        editor.putBoolean(MODE_SETTING, mode)
        editor.apply()
    }
}