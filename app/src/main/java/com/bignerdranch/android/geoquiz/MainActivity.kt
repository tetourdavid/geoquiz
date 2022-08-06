package com.bignerdranch.android.geoquiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.bignerdranch.android.geoquiz.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val questions = listOf(
        Question(R.string.question_australia, true),
        Question(R.string.question_oceans, true),
        Question(R.string.question_mideast, false),
        Question(R.string.question_africa, false),
        Question(R.string.question_americas, true),
        Question(R.string.question_asia, true)
    )

    private var currentIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.trueButton.setOnClickListener { check(true) }
        binding.falseButton.setOnClickListener { check(false) }
        binding.nextButton.setOnClickListener {
            currentIndex = (currentIndex + 1) % questions.size
            updateQuestion()
        }
        updateQuestion()
    }

    private fun updateQuestion() = binding.questionTextView.setText(questions[currentIndex].textResId)

    private fun check(guess: Boolean) {
        val answer = questions[currentIndex].answer
        val replyId = if (guess == answer) R.string.correct_toast else R.string.incorrect_toast
        Toast.makeText(this, replyId, Toast.LENGTH_SHORT).show()
    }
}