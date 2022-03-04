package com.example.harrypotterspells.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.harrypotterspells.databinding.ActivityMainBinding
import com.example.harrypotterspells.viewmodel.MainViewModel
import android.util.Log
import android.view.KeyEvent
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import android.widget.Toast
import com.example.harrypotterspells.model.SpellResponse
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModel()
    private val recyclerviewAdapter: MainAdapter by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initBinding()
        initViewModel()
        observeData()

    }

    private fun initViewModel(){
        with(viewModel){
            getSpells()
        }
    }
    private fun initBinding(){
        with(binding){
            recyclerView.run{
                adapter = recyclerviewAdapter
            }
            searchBtn.setOnClickListener {
                setSearch(searchEdt.text.toString())
            }
            searchEdt.setOnEditorActionListener(object : TextView.OnEditorActionListener {
                override fun onEditorAction(v: TextView?, actionId: Int, event: KeyEvent?): Boolean {
                    if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                        setSearch(searchEdt.text.toString())
                        return true
                    }
                    return false
                }
            })
        }
    }
    private fun observeData(){
        viewModel.getSpellResponseLiveData.observe(this) {
            Log.d("here? : ",it.toString())
            it.forEach{ spellResponse ->
                recyclerviewAdapter.setList(spellResponse.spell)
            }
        }
    }

    private fun setSearch(s:String){
        val filteredList = ArrayList<SpellResponse>()
        viewModel.getSpellResponseLiveData.observe(this) {
            if (s != "") {
                for (p in it) {
                    if (p.spell.uppercase().contains(s.uppercase())) {
                        filteredList.add(p)
                    }
                }
                if (filteredList.isNotEmpty()) {
                    recyclerviewAdapter.clearList()
                    filteredList.forEach { spells ->
                        recyclerviewAdapter.setList(spells.spell)
                    }
                } else {
                    Toast.makeText(
                        this.applicationContext, "we don't have that spell!", Toast.LENGTH_SHORT).show()
                }
            } else {
                recyclerviewAdapter.clearList()
                it.forEach{ spellResponse ->
                    recyclerviewAdapter.setList(spellResponse.spell)
                }
            }
        }

    }
}