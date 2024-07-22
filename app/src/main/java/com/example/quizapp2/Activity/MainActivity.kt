package com.example.quizapp2.Activity

import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.quizapp2.R
import com.example.quizapp2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )

        binding.apply {
            consCreateQuiz.setOnClickListener {
                // consCreateQuiz tıklama olayı
            }

            bottomMenu.setItemSelected(R.id.home)  // Başlangıçta Home öğesini seçin

            bottomMenu.setOnItemSelectedListener { itemId ->
                when (itemId) {
                    R.id.home -> {
                        // Home menü öğesi seçildiğinde yapılacak işlemler
                        startActivity(Intent(this@MainActivity, LeaderActivity::class.java))

                    }
                    R.id.board -> {
                        // Board menü öğesi seçildiğinde LeaderActivity'yi aç
                        startActivity(Intent(this@MainActivity, LeaderActivity::class.java))
                    }
                }
            }
        }
    }
}
