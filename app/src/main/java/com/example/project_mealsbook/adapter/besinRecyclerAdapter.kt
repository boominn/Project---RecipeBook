package com.example.project_mealsbook.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.project_mealsbook.databinding.BesinRecyclerRowBinding
import com.example.project_mealsbook.model.Besin
import com.example.project_mealsbook.util.gorselIndir
import com.example.project_mealsbook.util.placeHolderYap
import com.example.project_mealsbook.view.BesinListeFragmentDirections

class besinRecyclerAdapter(val besinListesi : ArrayList<Besin>): RecyclerView.Adapter<besinRecyclerAdapter.besinViewHolder>() {

    class besinViewHolder(val binding: BesinRecyclerRowBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): besinViewHolder {
        val binding = BesinRecyclerRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return besinViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return besinListesi.size
    }

    fun besinListesiGuncelle(yeniBesinListesi: List<Besin>) {
        besinListesi.clear()
        besinListesi.addAll(yeniBesinListesi)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: besinViewHolder, position: Int) {
        holder.binding.isim.text = besinListesi[position].besinIsim
        holder.binding.kalori.text = besinListesi[position].besinKalori

        holder.itemView.setOnClickListener {
            val action = BesinListeFragmentDirections.actionBesinListeFragment3ToBesinDetayFragment3(besinListesi[position].uuid)
            Navigation.findNavController(it).navigate(action)
        }
        holder.binding.imageView.gorselIndir(besinListesi[position].besinGorsel, placeHolderYap(holder.itemView.context))


    }


}