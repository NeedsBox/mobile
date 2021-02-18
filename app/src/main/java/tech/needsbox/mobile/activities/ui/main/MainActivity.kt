package tech.needsbox.mobile.activities.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import tech.needsbox.mobile.NeedsBoxApplication
import tech.needsbox.mobile.R
import tech.needsbox.mobile.activities.data.main.MainViewModel
import tech.needsbox.mobile.activities.ui.list.service.ServiceFragment
import tech.needsbox.mobile.api.NeedsBoxClient
import tech.needsbox.mobile.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = NeedsBoxApplication.INSTANCE.providerFactory.create(MainViewModel::class.java)
        viewModel.viewModelScope.launch {

            val serviceFragment =
                ServiceFragment.newInstance(NeedsBoxClient.serviceService.getServices().results)
            supportFragmentManager.beginTransaction()
                .add(R.id.fragmentContainerView, serviceFragment)
                .commit()
        }
    }
}