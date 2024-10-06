package com.leonardolarranaga.kotlintrivia.data

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class QuestionDatabase(context: Context) {
    private var allQuestions: List<Question>

    init {
        try {
            val json = context.assets.open("KotlinQuestions.json").bufferedReader().use { it.readText() }
            val type = object : TypeToken<List<Question>>() {}.type
            allQuestions = Gson().fromJson(json, type)
        } catch (e: Exception) {
            Log.e("QuestionDatabase", "Error reading JSON file", e)
            allQuestions = emptyList()
        }

    }

    fun getRandomQuestions(n: Int): List<Question> {
        return allQuestions.shuffled().take(n)
    }
}