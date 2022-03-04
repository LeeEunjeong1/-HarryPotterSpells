package com.example.harrypotterspells.ui

import android.content.Intent
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.harrypotterspells.databinding.ItemSpellBinding


class MainAdapter : RecyclerView.Adapter<MainAdapter.MainViewHolder>() {

    data class Spells(var spell: String)

    private var spellsList = ArrayList<Spells>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val binding = ItemSpellBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MainViewHolder(binding)
    }

    override fun getItemCount() = spellsList.size

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        (holder as? MainViewHolder)?.onBind(spellsList[position])
    }

    fun  clearList(){
        spellsList.clear()
    }
    fun setList(spell: String) {
        spellsList.add(Spells(spell))
        notifyDataSetChanged()
    }



    class MainViewHolder(private val binding: ItemSpellBinding) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(item: Spells) {
            with(binding){
                name.text = item.spell
            }
        }
    }
}