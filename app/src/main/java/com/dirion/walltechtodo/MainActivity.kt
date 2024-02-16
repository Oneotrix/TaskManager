package com.dirion.walltechtodo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatDelegate
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.dirion.walltechtodo.databinding.ActivityMainBinding
import com.dirion.walltechtodo.di.components.ActivityComponent
import javax.inject.Inject

class MainActivity : AppCompatActivity() {


     lateinit var activityComponent: ActivityComponent


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        activityComponent = App.appComponent.activityComponentBuilder()
            .activity(this@MainActivity)
            .build()
    }
}