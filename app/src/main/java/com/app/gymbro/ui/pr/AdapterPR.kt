package com.app.gymbro.ui.pr

import PR
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.gymbro.databinding.PrCardviewBinding

/**
 * Adapter for the PR RecyclerView.
 * This class is used to populate a RecyclerView with Personal Records.
 * It provides methods for updating the elements in the RecyclerView and binding data to the viewholder.
 *
 * @param list A list of Personal Records to be displayed in the RecyclerView
 *
 * @author Gymbro Team
 */

class AdapterPR(private val list: ArrayList<PR> = arrayListOf()) :
    RecyclerView.Adapter<AdapterPR.ViewHolder>() {

    /**
     * Creates the viewholder for each item in the RecyclerView.
     *
     * @param parent The parent ViewGroup
     * @param viewType The view type of the new View
     * @return A new instance of the ViewHolder
     */

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(PrCardviewBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    /**
     * Binds data to the viewholder for a given position in the RecyclerView.
     *
     * @param holder The ViewHolder to bind data to
     * @param position The position in the RecyclerView
     */

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(list[position])
    }

    /**
     * Returns the number of items in the RecyclerView.
     *
     * @return The number of items in the RecyclerView
     */

    override fun getItemCount(): Int {
        return list.size
    }

    /**
     * Updates the elements in the RecyclerView with a new list of Personal Records.
     *
     * @param updatedList The new list of Personal Records
     */

    fun updateElements(updatedList: ArrayList<PR>) {
        list.apply {
            clear()
            addAll(updatedList)
        }
        notifyDataSetChanged()
    }

    /**
     * The ViewHolder class for the AdapterPR class.
     */

    inner class ViewHolder(private val binding: PrCardviewBinding) : RecyclerView.ViewHolder(binding.root) {

        /**
         * Binds data to the viewholder.
         *
         * @param data The Personal Record data to be bound to the viewholder
         */

        fun bindItems(data: PR) = with(binding) {
            ejerciciotextview.text = data.ejercicio
            fechatextview.text = data.fecha
            pesotextview.text = data.peso
            repeticionestextview.text = data.repeticiones
        }
    }
}
