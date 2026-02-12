package com.example.project1_triviagame

import com.example.project1_triviagame.network.TriviaApi
import com.example.project1_triviagame.network.TriviaQuestion
import org.json.JSONArray
import org.json.JSONObject
import org.junit.Assert
import org.junit.Test
import java.lang.reflect.Method

class TriviaApiTest {

    @Test
    fun parseResults_createsCorrectTriviaQuestion() {

        // create fake OpenTDB JSON result
        val fakeQuestion = JSONObject().apply {
            put("category", "Science")
            put("difficulty", "easy")
            put("question", "What is 2+2?")
            put("correct_answer", "4")
            put("incorrect_answers", JSONArray(listOf("1", "2", "3")))
        }

        val fakeArray = JSONArray().apply {
            put(fakeQuestion)
        }

        // access private function parseResults using reflection
        val method: Method = TriviaApi::class.java
            .getDeclaredMethod("parseResults", JSONArray::class.java)

        method.isAccessible = true

        val result = method.invoke(TriviaApi, fakeArray) as List<*>

        Assert.assertEquals(1, result.size)

        val question = result[0] as TriviaQuestion

        Assert.assertEquals("Science", question.category)
        Assert.assertEquals("easy", question.difficulty)
        Assert.assertEquals("What is 2+2?", question.question)
        Assert.assertEquals("4", question.correctAnswer)

        // should have 4 answers (3 incorrect + 1 correct)
        Assert.assertEquals(4, question.answers.size)

        // correct answer should be included
        Assert.assertTrue(question.answers.contains("4"))
    }

    @Test
    fun parseResults_multipleQuestions_parsesAll() {

        val fakeArray = JSONArray()

        repeat(3) {
            val obj = JSONObject().apply {
                put("category", "General")
                put("difficulty", "medium")
                put("question", "Question $it")
                put("correct_answer", "Correct")
                put("incorrect_answers", JSONArray(listOf("A", "B", "C")))
            }
            fakeArray.put(obj)
        }

        val method = TriviaApi::class.java
            .getDeclaredMethod("parseResults", JSONArray::class.java)

        method.isAccessible = true

        val result = method.invoke(TriviaApi, fakeArray) as List<*>

        // should parse all 3
        Assert.assertEquals(3, result.size)
    }
}