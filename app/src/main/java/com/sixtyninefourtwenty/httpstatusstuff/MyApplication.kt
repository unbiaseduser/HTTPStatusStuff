package com.sixtyninefourtwenty.httpstatusstuff

import android.app.Application
import com.sixtyninefourtwenty.httpstatusstuff.data.repository.PreferencesRepository

class MyApplication : Application() {

    val prefs by lazy { PreferencesRepository(this) }

}