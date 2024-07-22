package com.example.quizapp2.Activity

import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.quizapp2.Adapter.LeaderAdapter
import com.example.quizapp2.Domain.UserModel
import com.example.quizapp2.R
import com.example.quizapp2.databinding.ActivityLeaderBinding

class LeaderActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLeaderBinding
    private val leaderAdapter by lazy { LeaderAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLeaderBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )

        binding.apply {
            tvScore1.text = loadData().get(0).score.toString()
            tvScore2.text = loadData().get(1).score.toString()
            tvScore3.text = loadData().get(2).score.toString()
            tvFirstsName.text = loadData().get(0).name
            tvSecondsName.text = loadData().get(1).name
            tvThirdsName.text = loadData().get(2).name

            val drawableResourceId1: Int = binding.root.resources.getIdentifier(
                loadData().get(0).pic, "drawable", binding.root.context.packageName
            )

            val drawableResourceId2: Int = binding.root.resources.getIdentifier(
                loadData().get(1).pic, "drawable", binding.root.context.packageName
            )
            val drawableResourceId3: Int = binding.root.resources.getIdentifier(
                loadData().get(2).pic, "drawable", binding.root.context.packageName
            )

            Glide.with(root.context)
                .load(drawableResourceId1)
                .into(ivFirstLiner)
            Glide.with(root.context)
                .load(drawableResourceId2)
                .into(ivSecondLiner)
            Glide.with(root.context)
                .load(drawableResourceId3)
                .into(ivThirdLiner)

            bottomMenu.setItemSelected(R.id.board)

            // ChipNavigationBar item selection listener
            bottomMenu.setOnItemSelectedListener { id ->
                when (id) {
                    R.id.home -> {
                        // Open MainActivity
                        val intent = Intent(this@LeaderActivity, MainActivity::class.java)
                        startActivity(intent)
                        finish()  // Optionally finish LeaderActivity
                    }

                    R.id.board -> Toast.makeText(this@LeaderActivity, "Board selected", Toast.LENGTH_SHORT).show()
                    R.id.favorites -> Toast.makeText(this@LeaderActivity, "Favorites selected", Toast.LENGTH_SHORT).show()
                    R.id.profile -> Toast.makeText(this@LeaderActivity, "Profile selected", Toast.LENGTH_SHORT).show()
                    else -> Toast.makeText(this@LeaderActivity, "Unknown item selected", Toast.LENGTH_SHORT).show()
                }
            }
            var list: MutableList<UserModel> = loadData()
            list.removeAt(0)
            list.removeAt(1)
            list.removeAt(2)
            leaderAdapter.differ.submitList(list)
            leaderView.apply {
                layoutManager = LinearLayoutManager(this@LeaderActivity)
                adapter = leaderAdapter
            }
        }
    }

    private fun loadData(): MutableList<UserModel> {
        val users: MutableList<UserModel> = mutableListOf()
        users.add(UserModel(1, "Sophia", "person1", 4850))
        users.add(UserModel(2, "Daniel", "person2", 4550))
        users.add(UserModel(3, "James", "person3", 4350))
        users.add(UserModel(4, "John Smith", "person4", 4250))
        users.add(UserModel(5, "Emily Johnson", "person5", 4050))
        users.add(UserModel(6, "David Brown", "person6", 3850))
        users.add(UserModel(7, "Sarah Wilson", "person7", 3550))
        users.add(UserModel(8, "Michael Davis", "person8", 3450))
        users.add(UserModel(9, "lincoln", "person9", 2850))
        users.add(UserModel(10, "Gratchen", "person10", 2150))
        return users
    }
}
