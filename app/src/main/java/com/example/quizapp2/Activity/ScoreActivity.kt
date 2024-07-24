package com.example.quizapp2.Activity

import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.example.quizapp2.databinding.ActivityScoreBinding

class ScoreActivity : AppCompatActivity() {
    var score = 0
    private lateinit var binding: ActivityScoreBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityScoreBinding.inflate(layoutInflater)
        setContentView(binding.root)

        score = intent.getIntExtra("Score", 0)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )

        binding.apply {
            tvScore.text = score.toString()
            btnBack.setOnClickListener {

                startActivity(
                    Intent(
                        this@ScoreActivity, MainActivity::class.java
                    )
                )
                finish()
            }
        }
    }
}