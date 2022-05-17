package com.example.kulturklub

import android.app.Application
import android.util.Log
import com.parse.Parse
import com.parse.ParseObject


public class App : Application() {

    override fun onCreate() {
        super.onCreate()
        Parse.initialize(
            Parse.Configuration.Builder(this)
                .applicationId(getString(R.string.back4app_app_id)) // if defined
                .clientKey(getString(R.string.back4app_client_key))
                .server(getString(R.string.back4app_server_url))
                .build())

    }

}
