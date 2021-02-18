package tech.needsbox.mobile.activities.ui.list.service

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.coroutines.runBlocking
import tech.needsbox.mobile.R
import tech.needsbox.mobile.api.NeedsBoxClient
import tech.needsbox.mobile.api.model.services.Service

/**
 * A fragment representing a list of Items.
 */
class ServiceFragment(val services: List<Service>) : Fragment() {

    constructor() : this(emptyList())

    private var values: Array<Service> = emptyArray()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            values = it.getParcelableArray(ARG_VALUE) as Array<Service>
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_service_list, container, false)

        // Set the adapter
        if (view is RecyclerView) {
            with(view) {
                layoutManager = LinearLayoutManager(context)
                adapter = MyServiceRecyclerViewAdapter(services)
            }
        }
        return view
    }

    companion object {

        const val ARG_VALUE = "value"

        @JvmStatic
        fun newInstance(serviceList: List<Service>) =
            ServiceFragment(serviceList).apply {
                arguments = Bundle().apply {
                    putParcelableArray(ARG_VALUE, serviceList.toTypedArray())
                }
            }
    }
}