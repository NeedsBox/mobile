package tech.needsbox.mobile.activities.ui.list.service

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import tech.needsbox.mobile.R

import tech.needsbox.mobile.api.model.services.Service
import tech.needsbox.mobile.databinding.FragmentServiceBinding

/**
 * [RecyclerView.Adapter] that can display a [tech.needsbox.mobile.api.model.services.Service].
 */
class MyServiceRecyclerViewAdapter(
    private val values: List<Service>
) : RecyclerView.Adapter<MyServiceRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            FragmentServiceBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.titleView.text = item.title
        holder.posterView.text = item.user.name.ifBlank { item.user.username }
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(binding: FragmentServiceBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val titleView: TextView = binding.titleView
        val posterView: TextView = binding.posterView

        override fun toString(): String {
            return super.toString() + " '" + posterView.text + "'"
        }
    }

}