package com.example.harrypotterspells.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.harrypotterspells.databinding.ItemSpellBinding

class MainAdapter : RecyclerView.Adapter<MainAdapter.MainViewHolder>() {

    data class Spells(var spell: String, var use: String)

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
    fun setList(spell: String, use: String) {
        spellsList.add(Spells(spell,use))
        notifyDataSetChanged()
    }



    class MainViewHolder(private val binding: ItemSpellBinding) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(item: Spells) {
            with(binding){
                name.text = item.spell
                itemView.setOnClickListener {
                    val intent = Intent(it.context, InfoActivity::class.java)

                    intent.putExtra("name", item.spell)
                    intent.putExtra("use", item.use)
                    it.context.startActivity(intent)
                }
            }
        }
    }
}