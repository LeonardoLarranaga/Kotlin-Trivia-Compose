package com.leonardolarranaga.kotlintrivia.data

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.File
import java.io.FileOutputStream

class RoundScoreDatabase(val context: Context) {
    val databaseName = "scores.json"
    val roundScores: MutableList<RoundScore>

    init {
        roundScores = readRoundScores()
    }

    fun saveRoundScore(roundScore: RoundScore) {
        try {
            roundScores.add(roundScore)
            val file = File(context.filesDir, databaseName)
            val json = Gson().toJson(roundScores)
            val stream = FileOutputStream(file)
            stream.write(json.toByteArray())
            stream.close()
        } catch (e: Exception) {
            Log.d("RoundScoreDataBase", "Error saving round score: ${e.message}")
        }
    }

    fun readRoundScores(): MutableList<RoundScore> {
        try {
            val file = File(context.filesDir, databaseName)
            val json = file.readText()
            val type = object : TypeToken<List<RoundScore>>() {}.type
            return Gson().fromJson(json, type)
        } catch (e: Exception) {
            Log.d("RoundScoreDataBase", "Error reading round scores: ${e.message}")
            return mutableListOf()
        }
    }
}