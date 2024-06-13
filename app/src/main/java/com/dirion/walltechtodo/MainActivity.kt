package com.dirion.walltechtodo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.view.forEach
import com.dirion.walltechtodo.databinding.ActivityMainBinding
import com.dirion.walltechtodo.di.navgation.NavigationComponent
import com.google.android.material.tabs.TabItem
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

        navigationComponent = App.navigationComponentFactory
            .create(this@MainActivity)

        panel = binding.vgTabLayout

        setTabListener()
    }

    private fun setTabListener() {
        binding.vgTabLayout.addOnTabSelectedListener(object : OnTabSelectedListener {
            override fun onTabSelected(p0: TabLayout.Tab) {
                when(p0.position) {
                    0 -> {
                        navigationComponent.navigationController().navigate(toOrders)
                    }

                    1 -> {
                        navigationComponent.navigationController().navigate(toTypography)
                    }

                    2 -> {
                        navigationComponent.navigationController().navigate(toLk)
                    }

                    3 -> {
                        navigationComponent.navigationController().navigate(toMyOrders)
                    }

                    4 -> {
                        navigationComponent.navigationController().navigate(toCustomers)
                    }

                    5 -> {
                        navigationComponent.navigationController().navigate(toEmployers)
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

        lateinit var navigationComponent: NavigationComponent
        lateinit var panel: TabLayout

        var toOrders = R.id.action_settingsFragment_to_tasksFragment
        var toTypography = R.id.action_tasksFragment_to_typographyFragment
        var toLk = R.id.action_tasksFragment_to_lkFragment
        var toMyOrders = R.id.action_tasksFragment_to_myOrdersFragment
        var toEmployers = R.id.action_tasksFragment_to_employersFragment
        var toCustomers = R.id.action_tasksFragment_to_customersFragment

    }
}


