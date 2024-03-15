package com.dirion.walltechtodo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dirion.walltechtodo.di.presentation.subcomponents.ActivityComponent

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        activityComponent = App.presentationComponent
            .activityComponentBuilder()
            .activity(this@MainActivity)
            .build()

    }



    companion object {

        lateinit var activityComponent: ActivityComponent

    }

}


