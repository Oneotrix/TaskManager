package com.dirion.walltechtodo.view.features.settings

import android.os.Bundle
import android.view.View
import com.dirion.walltechtodo.MainActivity
import com.dirion.walltechtodo.R
import com.dirion.walltechtodo.databinding.FragmentSettingsBinding
import com.dirion.walltechtodo.view.features.BaseFragment

class SettingsFragment: BaseFragment<FragmentSettingsBinding>(FragmentSettingsBinding::inflate){


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initListeners()
    }

    private fun initListeners() {
        onNotificationFragment()
        onDateTimeFragment()
        onNotesFragment()
        onNameFragment()
        onVolumeFragment()
    }

    private fun onNotificationFragment() {
        binding.tvNotifications.setOnClickListener {
            MainActivity.activityComponent.navigationController().navigate(R.id.action_settingsFragment_to_notificationsFragment)
        }
    }

    private fun onDateTimeFragment() {
        binding.tvDate.setOnClickListener {
            MainActivity.activityComponent.navigationController().navigate(R.id.action_settingsFragment_to_dataTimeFragment)
        }
    }

    private fun onNotesFragment() {
        binding.tvNotes.setOnClickListener {
            MainActivity.activityComponent.navigationController().navigate(R.id.action_settingsFragment_to_notesFragment)
        }
    }

    private fun onNameFragment() {
        binding.tvName.setOnClickListener {
            MainActivity.activityComponent.navigationController().navigate(R.id.action_settingsFragment_to_namesFragment)
        }
    }

    private fun onVolumeFragment() {
        binding.tvVolume.setOnClickListener {
            MainActivity.activityComponent.navigationController().navigate(R.id.action_settingsFragment_to_volumeFragment)
        }
    }

    companion object {
        fun newInstance() = SettingsFragment()
    }

}