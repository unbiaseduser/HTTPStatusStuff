package com.sixtyninefourtwenty.httpstatusstuff.data.repository

import android.content.Context
import androidx.annotation.IdRes
import androidx.core.content.edit
import androidx.preference.PreferenceManager
import com.sixtyninefourtwenty.httpstatusstuff.R
import com.sixtyninefourtwenty.stuff.getStringValue
import com.sixtyninefourtwenty.stuff.preferences.StringPreferenceValue
import com.sixtyninefourtwenty.stuff.putStringValue

class PreferencesRepository(context: Context) {

    private val prefs = PreferenceManager.getDefaultSharedPreferences(context)

    enum class InitialPage(override val value: String, @IdRes val destResId: Int) : StringPreferenceValue {
        CAT("cat", R.id.nav_cat), DOG("dog", R.id.nav_dog), PIZZA("pizza", R.id.nav_pizza)
    }

    var initialPage: InitialPage
        get() = prefs.getStringValue("initial_page", InitialPage.entries, InitialPage.CAT)
        set(value) = prefs.edit { putStringValue("initial_page", value) }

    companion object {
        fun initDefaultValues(context: Context) {
            PreferenceManager.setDefaultValues(context, R.xml.root_preferences, false)
        }
    }

}