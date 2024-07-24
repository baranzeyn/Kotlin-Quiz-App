package com.example.quizapp2.Activity

import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.quizapp2.Adapter.QuestionAdapter
import com.example.quizapp2.Domain.QuestionModel
import com.example.quizapp2.databinding.ActivityQuestionBinding

class QuestionActivity : AppCompatActivity(), QuestionAdapter.score {
    private lateinit var binding: ActivityQuestionBinding
    var position: Int = 0
    var receivedList: MutableList<QuestionModel> = mutableListOf()
    var allScore = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuestionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )

        receivedList = intent.getParcelableArrayListExtra<QuestionModel>("list")!!.toMutableList()

        binding.apply {
            ivBackArrow.setOnClickListener { finish() }
            progressbar.progress = 1

            tvQuestion.text = receivedList[position].question
            val drawableResourceId: Int = binding.root.resources.getIdentifier(
                receivedList[position].ivPath, "drawable", binding.root.context.packageName
            )
            Glide.with(this@QuestionActivity)
                .load(drawableResourceId)
                .centerCrop()
                .apply(RequestOptions.bitmapTransform(RoundedCorners(60)))
                .into(ivQuestion)

            loadAnswers()

            ivRightArrow.setOnClickListener {
                if (progressbar.progress == 10) {
                    val intent = Intent(this@QuestionActivity, ScoreActivity::class.java)
                    intent.putExtra("Score", allScore)
                    startActivity(intent)
                    finish()
                    return@setOnClickListener
                }

                position++
                progressbar.progress = progressbar.progress + 1
                tvQuestionNumber.text = "Question " + progressbar.progress + "/10"
                tvQuestion.text = receivedList[position].question

                val drawableResourceId: Int = binding.root.resources.getIdentifier(
                    receivedList[position].ivPath,
                    "drawable",
                    binding.root.context.packageName
                )
                Glide.with(this@QuestionActivity)
                    .load(drawableResourceId)
                    .centerCrop()
                    .apply(RequestOptions.bitmapTransform(RoundedCorners(60)))
                    .into(ivQuestion)

                loadAnswers()

            }
            ivLeftArrow.setOnClickListener {
                if (progressbar.progress == 1) {
                    return@setOnClickListener
                }

                position--
                progressbar.progress = progressbar.progress - 1
                tvQuestionNumber.text = "Question " + progressbar.progress + "/10"
                tvQuestion.text = receivedList[position].question

                val drawableResourceId: Int = binding.root.resources.getIdentifier(
                    receivedList[position].ivPath,
                    "drawable",
                    binding.root.context.packageName
                )
                Glide.with(this@QuestionActivity)
                    .load(drawableResourceId)
                    .centerCrop()
                    .apply(RequestOptions.bitmapTransform(RoundedCorners(60)))
                    .into(ivQuestion)

                loadAnswers()

            }
        }
    }

    fun loadAnswers() {
        val users: MutableList<String> = mutableListOf()
        users.add(receivedList[position].firstOption.toString())
        users.add(receivedList[position].secondOption.toString())
        users.add(receivedList[position].thirdOption.toString())
        users.add(receivedList[position].fourthOption.toString())

        if (receivedList[position].clickedAnswer != null) users.add(receivedList[position].clickedAnswer.toString())

        val questionAdapter by lazy {
            QuestionAdapter(receivedList[position].answer.toString(), users, this)
        }
        questionAdapter.differ.submitList(users)
        binding.questionList.apply {
            layoutManager = LinearLayoutManager(this@QuestionActivity)
            adapter = questionAdapter
        }
    }

    override fun amount(number: Int, clickedAnswer: String) {
        allScore += number
        receivedList[position].clickedAnswer = clickedAnswer

    }
}