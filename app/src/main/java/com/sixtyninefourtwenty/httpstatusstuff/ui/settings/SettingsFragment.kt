package com.sixtyninefourtwenty.httpstatusstuff.ui.settings

import android.os.Bundle
import androidx.navigation.fragment.findNavController
import androidx.preference.ListPreference
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import com.sixtyninefourtwenty.httpstatusstuff.R
import com.sixtyninefourtwenty.stuff.makeToast
import com.sixtyninefourtwenty.stuff.setOnPreferenceChange

class SettingsFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey)
        with(findPreference<Preference>("navigation_appearance_prefs")!!) {
            setOnPreferenceClickListener {
                findNavController().navigate(SettingsFragmentDirections.actionNavSettingsToNavigationAppearanceSettings())
                true
            }
        }
        with(findPreference<ListPreference>("initial_page")!!) {
            setOnPreferenceChange { _ ->
                requireContext().makeToast(R.string.change_applied_on_next_time_launch).show()
                true
            }
        }
    }
}