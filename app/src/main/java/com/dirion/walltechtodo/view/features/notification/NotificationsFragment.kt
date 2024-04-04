package com.dirion.walltechtodo.view.features.notification


import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.dirion.walltechtodo.App
import com.dirion.walltechtodo.databinding.FragmentNotificationsBinding
import com.dirion.walltechtodo.utils.TestData
import com.dirion.walltechtodo.view.features.BaseFragment
import com.dirion.walltechtodo.view.features.notification.recycler.AdapterNotification
import javax.inject.Inject

class NotificationsFragment: BaseFragment<FragmentNotificationsBinding>(FragmentNotificationsBinding::inflate){


    private val adapter by lazy {
        AdapterNotification()
    }

    @Inject
    lateinit var viewModelFactory: NotificationViewModelFactory

    private val viewModel: NotificationViewModel by viewModels { viewModelFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        App.presentationComponent.notificationFragmentComponentBuilder()
            .build()
            .inject(this@NotificationsFragment)

        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.test()
        binding.rvNotifications.adapter = adapter
        adapter.submitList(TestData.notificationModel.notifications)

        super.onViewCreated(view, savedInstanceState)
    }





    companion object {
        fun newInstance() = NotificationsFragment()
    }

}