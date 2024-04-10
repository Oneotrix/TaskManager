package com.dirion.walltechtodo.data.datasource.localSource.shared_prefs

import android.content.SharedPreferences
import android.content.SharedPreferences.Editor
import javax.inject.Inject

class SharedPrefsHelper @Inject constructor(
    val writer: Editor,
    val reader: SharedPreferences
)