package tech.needsbox.mobile.activities.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import tech.needsbox.mobile.R
import tech.needsbox.mobile.activities.ui.main.ui.main.MainFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment.newInstance())
                .commitNow()
        }
    }
}