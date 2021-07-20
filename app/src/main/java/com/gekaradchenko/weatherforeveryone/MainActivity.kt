package com.gekaradchenko.weatherforeveryone

import android.content.res.Configuration
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.ViewModelProvider
import java.util.*


class MainActivity : AppCompatActivity() {
    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this).get(MainViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        viewModel.language.observe(this, androidx.lifecycle.Observer {
            it?.let {
                setLocate(it)
            }
        })
        viewModel.modeNight.observe(this, androidx.lifecycle.Observer {
            viewModel.modeSet(it)
        })


    }

    private fun setLocate(language: String) {
        val locale = Locale(language)
        Locale.setDefault(locale)
        val configuration = Configuration()
        configuration.locale = locale
        this.resources.updateConfiguration(configuration, this.resources.displayMetrics)

    }
}