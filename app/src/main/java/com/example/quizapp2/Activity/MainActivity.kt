package com.example.quizapp2.Activity

import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.example.quizapp2.Domain.QuestionModel
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
            constSinglePlayer.setOnClickListener {
                // consCreateQuiz tıklama olayı
                val intent = Intent(this@MainActivity, QuestionActivity::class.java)
                intent.putParcelableArrayListExtra("list", ArrayList(questionList()))
                startActivity(intent)
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

    private fun questionList(): MutableList<QuestionModel> {
        val question: MutableList<QuestionModel> = mutableListOf()
        question.add(
            QuestionModel(
                1, "Which planet is the largest planet in the solar system? ",
                "Earth",
                "Mars",
                "Neptune",
                "Jupiter",
                "d",
                5,
                "q_1",
                null
            )
        )
        question.add(
            QuestionModel(
                2, "Which country is the largest country in the land area? ",
                "Russia",
                "Canada",
                "US",
                "China",
                "a",
                5,
                "q_2",
                null
            )
        )
        question.add(
            QuestionModel(
                3, "Which of the following substances is used as an anti-cancer medication in the medical world? ",
                "Cheese",
                "lemon juice",
                "Cannabis",
                "Paspalum",
                "c",
                5,
                "q_3",
                null
            )
        )
        question.add(
            QuestionModel(
                4, "Which moon in the Earth solar system has an atmosphere? ",
                "Luna (Moon)",
                "Phobos (Mars's Moon)",
                "Venus's Moon",
                "None of the above",
                "d",
                5,
                "q_4",
                null
            )
        )
        question.add(
            QuestionModel(
                5, "Which of the following symbols represents the chemical element with the atomic number 6 ",
                "N",
                "H",
                "C",
                "None of the above",
                "c",
                5,
                "q_5",
                null
            )
        )
        question.add(
            QuestionModel(
                6, "Who is credited with inventing theater as we know it today? ",
                "Pacific",
                "Arthur Miller",
                "Askhkouri",
                "Ancient Greeks",
                "d",
                5,
                "q_6",
                null
            )
        )
        question.add(
            QuestionModel(
                7, "Which ocean is the largest Ocean in the world? ",
                "Pacific",
                "Atlantic",
                "Indian",
                "Arctic",
                "a",
                5,
                "q_7",
                null
            )
        )
        question.add(
            QuestionModel(
                8, "Which religons are among the most practiced religions in the world ",
                "Islam, Christianity, Judaism",
                "Buddhism, Hinduism, Sikhism",
                "Zoroastrianism, Brahmanism, Yandanism",
                "Taoismi, Shintoism, Confucianism",
                "a",
                5,
                "q_8",
                null
            )
        )
        question.add(
            QuestionModel(
                9, "In which continent are the most independent countries located? ",
                "Asia",
                "Europe",
                "Africa",
                "America",
                "c",
                5,
                "q_9",
                null
            )
        )
        question.add(
            QuestionModel(
                10, "Which ocean has the greatest average depth? ",
                "Pacific Ocean",
                "Atlantic Ocean",
                "Indian Ocean",
                "Southern ocean",
                "d",
                5,
                "q_10",
                null
            )
        )
        return question
    }
}
