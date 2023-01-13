package com.app.gymbro.ui.pr

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.gymbro.classes.PR
import com.app.gymbro.databinding.PrCardviewBinding

class AdapterPR(private val list: ArrayList<PR> = arrayListOf()) :
    RecyclerView.Adapter<AdapterPR.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(PrCardviewBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun updateElements(updatedList: ArrayList<PR>) {
        list.apply {
            clear()
            addAll(updatedList)
        }
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val binding: PrCardviewBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bindItems(data: PR) = with(binding) {
            ejerciciotextview.text = data.ejercicio
            fechatextview.text = data.fecha
            pesotextview.text = data.peso
            repeticionestextview.text = data.repeticiones
        }
    }
}