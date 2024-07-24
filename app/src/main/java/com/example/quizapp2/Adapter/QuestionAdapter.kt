package com.example.quizapp2.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.quizapp2.R
import com.example.quizapp2.databinding.ViewholderQuestionBinding

class QuestionAdapter(
    val correctAnswer: String,
    var users: MutableList<String> = mutableListOf(),
    var returnScore: score
) : RecyclerView.Adapter<QuestionAdapter.ViewHolder>() {

    interface score {
        fun amount(number: Int, clickedOption: String)
    }

    private lateinit var binding: ViewholderQuestionBinding

    inner class ViewHolder : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionAdapter.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        binding = ViewholderQuestionBinding.inflate(inflater, parent, false)
        return ViewHolder()
    }

    override fun onBindViewHolder(holder: QuestionAdapter.ViewHolder, position: Int) {
        val binding = ViewholderQuestionBinding.bind(holder.itemView)
        binding.tvAnswer.text = differ.currentList[position]
        var currentPosition = 0
        when (correctAnswer) {
            "a" -> {
                currentPosition = 0
            }

            "b" -> {
                currentPosition = 1
            }

            "c" -> {
                currentPosition = 2
            }

            "d" -> {
                currentPosition = 3
            }
        }

        if (differ.currentList.size == 5 && currentPosition == position) {
            binding.tvAnswer.setBackgroundResource(com.example.quizapp2.R.drawable.green_bg)
            val drawable = ContextCompat.getDrawable(binding.root.context, com.example.quizapp2.R.drawable.tick)
            binding.tvAnswer.setCompoundDrawablesRelativeWithIntrinsicBounds(
                null,
                null,
                drawable,
                null
            )
        }
        if (differ.currentList.size == 5) {
            var clickedPosition = 0
            when (differ.currentList[4]) {
                "a" -> {
                    clickedPosition = 0
                }

                "b" -> {
                    clickedPosition = 1
                }

                "c" -> {
                    clickedPosition = 2
                }

                "d" -> {
                    clickedPosition = 3
                }
            }

            if (clickedPosition == position && clickedPosition != currentPosition) {
                binding.tvAnswer.setBackgroundResource(R.drawable.red_bg)

                val drawable = ContextCompat.getDrawable(binding.root.context, com.example.quizapp2.R.drawable.thieves)
                binding.tvAnswer.setCompoundDrawablesRelativeWithIntrinsicBounds(
                    null,
                    null,
                    drawable,
                    null
                )
            }
        }
        if (position == 4) {
            binding.root.visibility = View.GONE
        }

        holder.itemView.setOnClickListener {
            var str = ""
            when (position) {
                0 -> {
                    str = "a"
                }

                1 -> {
                    str = "b"
                }

                2 -> {
                    str = "c"
                }

                3 -> {
                    str = "d"
                }
            }
            users.add(4, str)
            notifyDataSetChanged()

            if (currentPosition == position) {
                binding.tvAnswer.setBackgroundResource(R.drawable.green_bg)
                val drawable = ContextCompat.getDrawable(binding.root.context, com.example.quizapp2.R.drawable.tick)
                binding.tvAnswer.setCompoundDrawablesRelativeWithIntrinsicBounds(
                    null,
                    null,
                    drawable,
                    null
                )
                returnScore.amount(5, str)
            } else {
                binding.tvAnswer.setBackgroundResource(R.drawable.red_bg)
                val drawable = ContextCompat.getDrawable(binding.root.context, com.example.quizapp2.R.drawable.thieves)
                binding.tvAnswer.setCompoundDrawablesRelativeWithIntrinsicBounds(
                    null,
                    null,
                    drawable,
                    null
                )
                returnScore.amount(0, str)

            }
        }

        if (differ.currentList.size == 5) holder.itemView.setOnClickListener(null)
    }

    override fun getItemCount() = differ.currentList.size


    private val differCallback = object : DiffUtil.ItemCallback<String>() {
        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }
    }
    val differ = AsyncListDiffer(this, differCallback)
}