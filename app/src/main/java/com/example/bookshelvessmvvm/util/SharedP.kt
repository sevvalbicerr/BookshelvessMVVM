package com.example.bookshelvessmvvm.util

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import androidx.core.content.edit

class SharedP {
    companion object {

        private val ZAMAN = "zaman"
        private var sharedPreferences : SharedPreferences? = null


        @Volatile private var instance : SharedP? = null
        private val lock = Any()
        operator fun invoke(context: Context) : SharedP = instance ?: synchronized(lock) {
            instance ?: ozelSharedPreferencesYap(context).also {
                instance = it
            }
        }

        private fun ozelSharedPreferencesYap(context: Context): SharedP{
            sharedPreferences = androidx.preference.PreferenceManager.getDefaultSharedPreferences(context)
            return SharedP()
        }


    }

    fun zamaniKaydet(zaman: Long){
        sharedPreferences?.edit(commit = true){
            putLong(ZAMAN,zaman)
        }
    }

    fun zamaniAl() = sharedPreferences?.getLong(ZAMAN,0)
}