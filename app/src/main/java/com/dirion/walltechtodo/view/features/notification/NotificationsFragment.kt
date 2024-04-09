package com.dirion.walltechtodo.view.features.notification


import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.dirion.walltechtodo.App
import com.dirion.walltechtodo.MainActivity
import com.dirion.walltechtodo.R
import com.dirion.walltechtodo.databinding.FragmentNotificationsBinding
import com.dirion.walltechtodo.view.features.BaseFragment
import com.dirion.walltechtodo.view.features.notification.recycler.AdapterNotification
import javax.inject.Inject

class NotificationsFragment: BaseFragment<FragmentNotificationsBinding>(FragmentNotificationsBinding::inflate){


    private val adapter by lazy {
        AdapterNotification { model ->
            viewModel.updateSwitcherModel(model)
        }
    }

    @Inject
    lateinit var viewModelFactory: NotificationViewModelFactory

    private val viewModel: NotificationViewModel by viewModels { viewModelFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        App.settingsComponent.notificationFragmentComponentBuilder()
            .build()
            .inject(this@NotificationsFragment)

        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {


        setOnBackListener()
        setRecycler()

        super.onViewCreated(view, savedInstanceState)
    }

    private fun setRecycler() {
        binding.rvNotifications.adapter = adapter
        adapter.submitList(viewModel.data.value.model.notifications)
    }

    private fun setOnBackListener() {
        binding.btnBack.setOnClickListener {
            MainActivity.navigationComponent.navigationController().navigate(R.id.action_notificationsFragment_to_settingsFragment)
        }
    }

    companion object {
        fun newInstance() = NotificationsFragment()
    }

}