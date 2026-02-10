package com.example.project1_triviagame.network

import android.util.Log
import androidx.core.text.HtmlCompat
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONArray
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

object TriviaApi {

    private const val TAG = "TriviaApi"
    private const val BASE =
        "https://opentdb.com/api.php?amount=10&type=multiple"

    suspend fun fetchQuestions(
        categoryId: Int,
        difficulty: String,
        amount: Int = 10
    ): List<TriviaQuestion> = withContext(Dispatchers.IO) {

        // multi-use single URL
        val urlString = "$BASE&category=$categoryId&difficulty=$difficulty"
        val conn = (URL(urlString).openConnection() as HttpURLConnection).apply {
            requestMethod = "GET"
            connectTimeout = 10_000
            readTimeout = 10_000
        }

        try {
            val code = conn.responseCode
            if (code != 200) {
                Log.e(TAG, "HTTP error $code")
                return@withContext emptyList()
            }

            val body = BufferedReader(InputStreamReader(conn.inputStream)).use { it.readText() }
            val json = JSONObject(body)

            val responseCode = json.optInt("response_code", -1)
            if (responseCode != 0) {
                Log.e(TAG, "OpenTDB response_code=$responseCode")
                return@withContext emptyList()
            }

            val results = json.getJSONArray("results")
            return@withContext parseResults(results)

        } finally {
            conn.disconnect()
        }
    }

    private fun parseResults(results: JSONArray): List<TriviaQuestion> {
        val out = mutableListOf<TriviaQuestion>()

        for (i in 0 until results.length()) {
            val item = results.getJSONObject(i)

            val category = decode(item.getString("category"))
            val difficulty = decode(item.getString("difficulty"))
            val question = decode(item.getString("question"))
            val correct = decode(item.getString("correct_answer"))

            val incorrect = item.getJSONArray("incorrect_answers")
            val answers = mutableListOf<String>()
            for (j in 0 until incorrect.length()) {
                answers += decode(incorrect.getString(j))
            }
            answers += correct
            answers.shuffle()

            out += TriviaQuestion(
                question = question,
                correctAnswer = correct,
                answers = answers,
                category = category,
                difficulty = difficulty
            )
        }

        return out
    }

    private fun decode(raw: String): String {
        return HtmlCompat.fromHtml(raw, HtmlCompat.FROM_HTML_MODE_LEGACY).toString()
    }
}
