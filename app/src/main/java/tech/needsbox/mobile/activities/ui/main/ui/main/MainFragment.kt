package tech.needsbox.mobile.activities.ui.main.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import tech.needsbox.mobile.NeedsBoxApplication
import tech.needsbox.mobile.R
import tech.needsbox.mobile.api.NeedsBoxClient
import tech.needsbox.mobile.api.getNextPage

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = NeedsBoxApplication.INSTANCE.providerFactory.create(MainViewModel::class.java)
        viewModel.viewModelScope.launch {
            val categories = NeedsBoxClient.miscService.getCategories()
            val categories2 = (categories to categories.getNextPage())
            println(categories2)

        }
    }

}