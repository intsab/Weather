package com.intsab.intsabwether

import android.app.Activity
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.intsab.intsabwether.databinding.MainActivityBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: MainActivityBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}

