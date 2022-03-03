package com.example.harrypotterspells.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.harrypotterspells.databinding.ActivityMainBinding
import com.example.harrypotterspells.viewmodel.MainViewModel
import android.util.Log
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


        viewModel.getSpells()
        Log.d("MainAcrtivity : ",viewModel.getSpellResponseLiveData.toString())

        binding.recyclerView.run{
            adapter = recyclerviewAdapter
        }

        observeData()

    }

    private fun observeData(){
        viewModel.getSpellResponseLiveData.observe(this) {
            recyclerviewAdapter.clearList()
            it.forEach{ spellResponse ->
                recyclerviewAdapter.setList(spellResponse.spell)
            }
            recyclerviewAdapter.notifyDataSetChanged()
        }
    }
}