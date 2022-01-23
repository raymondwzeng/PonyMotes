//package com.dinsfire.ponymotes
//
//import android.annotation.SuppressLint
//
//class FragmentPreferences : Activity() {
//    @SuppressLint("NewApi")
//    protected fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//
//        // Display the fragment as the main content.
//        getFragmentManager().beginTransaction().replace(android.R.id.content, PrefsFragment())
//            .commit()
//    }
//
//    @SuppressLint("NewApi")
//    class PrefsFragment : PreferenceFragment() {
//        fun onCreate(savedInstanceState: Bundle?) {
//            super.onCreate(savedInstanceState)
//
//            // Load the preferences from an XML resource
//            addPreferencesFromResource(R.xml.settings)
//        } /*
//	 * implements OnSharedPreferenceChangeListener {
//	 *
//	 * @Override public void onResume() { super.onResume();
//	 * getPreferenceManager
//	 * ().getSharedPreferences().registerOnSharedPreferenceChangeListener
//	 * (this);
//	 *
//	 * }
//	 *
//	 * @Override public void onPause() {
//	 * getPreferenceManager().getSharedPreferences
//	 * ().unregisterOnSharedPreferenceChangeListener(this); super.onPause();
//	 * }
//	 *
//	 * public void onSharedPreferenceChanged(SharedPreferences
//	 * sharedPreferences, String key) {
//	 *
//	 * if(key.equals("prefDirectory")) { Preference pref =
//	 * findPreference(key); //EditTextPreference editPref =
//	 * (EditTextPreference) pref;
//	 * pref.setSummary(sharedPreferences.getString(key, ""));
//	 *
//	 * }
//	 *
//	 * }
//	 */
//    }
//}