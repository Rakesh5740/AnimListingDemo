package com.animlistingdemo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.animlistingdemo.databinding.AnimDetailsActivityBinding

class AnimDetailsActivity: AppCompatActivity() {

    private lateinit var binding: AnimDetailsActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = AnimDetailsActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }
}