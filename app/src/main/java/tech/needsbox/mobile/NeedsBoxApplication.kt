package tech.needsbox.mobile

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.ViewModelProvider
import io.sentry.Sentry
import tech.needsbox.mobile.activities.ui.login.NeedsBoxProviderFactory

class NeedsBoxApplication : Application() {
    override fun onCreate() {
        Thread.setDefaultUncaughtExceptionHandler { _, e ->
            Sentry.captureException(e)
        }
    }

    companion object {
        lateinit var INSTANCE: NeedsBoxApplication
    }

    init {
        INSTANCE = this
    }

    val preferences: SharedPreferences
        get() = applicationContext.getSharedPreferences(
            "needsbox", Context.MODE_PRIVATE
        )

    val providerFactory: ViewModelProvider.Factory = NeedsBoxProviderFactory(this)
}