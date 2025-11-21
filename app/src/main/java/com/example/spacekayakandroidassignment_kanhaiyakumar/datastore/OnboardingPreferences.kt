package com.example.spacekayakandroidassignment_kanhaiyakumar.datastore


import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.map

val Context.dataStore by preferencesDataStore("onboarding_prefs")

object OnboardingPrefKeys {
    val ONBOARDING_DONE = booleanPreferencesKey("onboarding_done")
}

suspend fun setOnboardingDone(context: Context) {
    context.dataStore.edit {
        it[OnboardingPrefKeys.ONBOARDING_DONE] = true
    }
}

fun isOnboardingDone(context: Context) =
    context.dataStore.data.map {
        it[OnboardingPrefKeys.ONBOARDING_DONE] ?: false
    }
