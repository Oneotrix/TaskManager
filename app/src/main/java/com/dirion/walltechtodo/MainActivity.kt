package com.dirion.walltechtodo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dirion.walltechtodo.databinding.ActivityMainBinding
import com.dirion.walltechtodo.di.presentation.subcomponents.ActivityComponent
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.GONE
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import com.google.android.material.tabs.TabLayout.VISIBLE

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setTabListener()
        activityComponent = App.presentationComponent
            .activityComponentBuilder()
            .activity(this@MainActivity)
            .build()

    }

    private fun setTabListener() {
        binding.vgTabLayout.addOnTabSelectedListener(object : OnTabSelectedListener {
            override fun onTabSelected(p0: TabLayout.Tab) {
                when(p0.position) {
                    0 -> {
                        activityComponent.navigationController().navigate(R.id.action_settingsFragment_to_tasksFragment)
                    }

                    1 -> {
                        activityComponent.navigationController().navigate(R.id.action_tasksFragment_to_settingsFragment)
                    }
                }
            }

            override fun onTabUnselected(p0: TabLayout.Tab?) {

            }

            override fun onTabReselected(p0: TabLayout.Tab?) {
            }

        })
    }


    fun hideTabLayout() {
        binding.vgTabLayout.visibility = GONE
    }

    fun showTabLayout() {
        binding.vgTabLayout.visibility = VISIBLE
    }

    companion object {

        lateinit var activityComponent: ActivityComponent

    }

}


