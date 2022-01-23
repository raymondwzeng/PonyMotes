//package com.dinsfire.ponymotes
//
//import android.content.SharedPreferences
//import java.lang.Exception
//
//class Prefrences(prefs: SharedPreferences) {
//    private val prefs: SharedPreferences
//    fun includeNSFW(): Boolean {
//        return prefs.getBoolean("prefIncludeNSFW", false)
//    }
//
//    fun wantToDownloadEmotes(): Boolean {
//        return prefs.getBoolean("prefDownloadEmotes", true)
//    }
//
//    fun wantToDownloadMissingEmotes(): Boolean {
//        return prefs.getBoolean("prefDownloadMissingEmotes", true)
//    }
//
//    fun disableOnMobile(): Boolean {
//        return prefs.getBoolean("prefDisableMobile", false)
//    }
//
//    fun altSharingMethod(): Boolean {
//        return prefs.getBoolean("prefAltSharingMethod", false)
//    }
//
//    fun onboardingSession(): Boolean {
//        return prefs.getBoolean("prefShowOnboardingSession", true)
//    }
//
//    fun turnOnboardingSessionOff() {
//        prefs.edit().putBoolean("prefShowOnboardingSession", false).commit()
//    }
//
//    fun searchLimit(): String {
//        return prefs.getString("prefSearchResultLimit", "200")
//    }
//
//    fun numOfColumns(): Int {
//        return try {
//            Integer.valueOf(prefs.getString("prefNumOfColumns", "3"))
//        } catch (e: Exception) {
//            3
//        }
//    }
//
//    init {
//        this.prefs = prefs
//    }
//}