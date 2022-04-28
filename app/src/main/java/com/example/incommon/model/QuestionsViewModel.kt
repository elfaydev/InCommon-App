package com.example.incommon.model

import androidx.lifecycle.ViewModel
import com.example.incommon.data.AllQuestionsList

class QuestionsViewModel: ViewModel() {
    val listOfQuestions: List<QuestionModel>
    private val listSize = 30
    var listOfAnswers: MutableList<Int> = MutableList(listSize){ -1 }

    init {
        val allQuestions = AllQuestionsList().getAllQuestions()
        listOfQuestions = allQuestions
            .shuffled()
            .take(listSize)
    }


}