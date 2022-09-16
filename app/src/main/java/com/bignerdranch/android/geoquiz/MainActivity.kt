package com.bignerdranch.android.geoquiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import com.bignerdranch.android.geoquiz.databinding.ActivityMainBinding

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val quizViewModel: QuizViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate(Bundle?) called")
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Log.d(TAG, "Got a quizViewModel: $quizViewModel")
        binding.trueButton.setOnClickListener { check(true) }
        binding.falseButton.setOnClickListener { check(false) }
        binding.nextButton.setOnClickListener { open(quizViewModel.nextQuestion) }
        open(quizViewModel.currentQuestion)
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart() called")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume() called")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause() called")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop() called")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy() called")
    }

    private fun open(question: Question) {
        binding.questionTextView.setText(question.textResId)
        enableAnswering(true)
    }

    private fun check(guess: Boolean) {
        val answer = quizViewModel.currentQuestion.answer
        val replyId = if (guess == answer) R.string.correct_toast else R.string.incorrect_toast
        Toast.makeText(this, replyId, Toast.LENGTH_SHORT).show()
        enableAnswering(false)
    }

    private fun enableAnswering(choice: Boolean) {
        binding.trueButton.isEnabled = choice
        binding.falseButton.isEnabled = choice
    }
}