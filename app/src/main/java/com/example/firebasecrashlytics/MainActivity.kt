package com.example.firebasecrashlytics

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.google.firebase.FirebaseApp
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.crashlytics.FirebaseCrashlytics

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        // Initialize Firebase
        FirebaseApp.initializeApp(this)

        // Initialize Firebase Analytics
        FirebaseAnalytics.getInstance(this)

        // Initialize Firebase Crashlytics
        FirebaseCrashlytics.getInstance().setCrashlyticsCollectionEnabled(true)

        findViewById<Button>(R.id.btn).setOnClickListener {
            FirebaseCrashlytics.getInstance().log("Button clicked.")
            FirebaseCrashlytics.getInstance().setCustomKey("user_id", "123456")
            simulateCrash()
        }
    }

    private fun simulateCrash() {
        try {
            Log.e("simulateCrash: ","Test Crash" )
            throw RuntimeException("Test Crash 123") // Simulate a crash for testing
        } catch (e: Exception) {
            FirebaseCrashlytics.getInstance().recordException(e)
        }
            /*Log.e("simulateCrash: ","Test Crash" )
            throw RuntimeException("Test Crash") // Simulate a crash for testing*/

    }
}