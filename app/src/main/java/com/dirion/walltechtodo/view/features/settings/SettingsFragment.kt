package com.dirion.walltechtodo.view.features.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
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
    }

    private fun onNotificationFragment() {
        binding.tvNotifications.setOnClickListener {
            MainActivity.activityComponent.navigationController().navigate(R.id.action_settingsFragment_to_notificationsFragment)
        }
    }


    companion object {
        fun newInstance() = SettingsFragment()
    }

}