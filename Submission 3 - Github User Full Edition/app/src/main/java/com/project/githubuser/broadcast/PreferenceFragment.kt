package com.project.githubuser.broadcast

import android.content.SharedPreferences
import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.SwitchPreference
import com.project.githubuser.R

class PreferenceFragment: PreferenceFragmentCompat(), SharedPreferences.OnSharedPreferenceChangeListener {
    private lateinit var ALARM: String

    private lateinit var alarmPreference: SwitchPreference

    private lateinit var alarm: AlarmReceiver

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        addPreferencesFromResource(R.xml.preferences)
        init()
    }

    override fun onSharedPreferenceChanged(sharedPreferences: SharedPreferences?, key: String?) {
        if (key == ALARM) {
            if (alarmPreference.isChecked) {
                context?.let {
                    alarm.setRepeatingAlarm(it, AlarmReceiver.TYPE_REPEATING,getString(R.string.reminder_message))
                }
            }
            else{
                context?.let { alarm.cancelAlarm(it) }
            }
        }
    }

    private fun init() {
        ALARM = resources.getString(R.string.key_alarm)

        alarm = AlarmReceiver()
        alarmPreference= findPreference<SwitchPreference>(ALARM) as SwitchPreference
    }



    override fun onPause() {
        super.onPause()
        preferenceScreen.sharedPreferences.unregisterOnSharedPreferenceChangeListener(this)
    }

    override fun onResume() {
        super.onResume()
        preferenceScreen.sharedPreferences.registerOnSharedPreferenceChangeListener(this)
    }
}