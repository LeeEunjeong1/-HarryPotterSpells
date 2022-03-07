package com.example.harrypotterspells.ui.info

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.harrypotterspells.R
import com.example.harrypotterspells.databinding.ActivityInfoBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class InfoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityInfoBinding
    private val viewModel: TranslateViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        trans = false
        initBinding()
        observeTranslate()
    }

    private fun initBinding(){
        val secondIntent = intent
        val spell = secondIntent.getStringExtra("name")!!
        val use = secondIntent.getStringExtra("use")!!
        binding.spellName.text = spell
        binding.spellInfo.text = use
        binding.translateBtn.setOnClickListener {
            trans = if(trans){
                //한국어 -> 영어
            //    viewModel.translate("ko","en",binding.spellInfo.text.toString())
                binding.spellInfo.text = use
                binding.spellInfo.typeface = resources.getFont(R.font.lumos_latino)
                false
            }else{
                //영어 -> 한국어
                viewModel.translate("en","ko",binding.spellInfo.text.toString())
                true
            }
        }

    }
    private fun observeTranslate() {
        viewModel.translateData.observe(this) {
            binding.spellInfo.text = it
            binding.spellInfo.typeface = resources.getFont(R.font.safi)
        }
        viewModel.isError.observe(this){
            Toast.makeText(applicationContext,it,Toast.LENGTH_LONG).show()
        }
    }

    companion object{
        var trans: Boolean = false
    }
}
