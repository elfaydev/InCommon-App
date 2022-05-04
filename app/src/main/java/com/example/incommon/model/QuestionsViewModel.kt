package com.example.incommon.model

import androidx.lifecycle.ViewModel
import com.example.incommon.data.AllQuestionsList

class QuestionsViewModel: ViewModel() {
    var listOfQuestions: List<QuestionModel>
    private val listSize = 10
    val allQuestions = AllQuestionsList().getAllQuestions()
    var listOfAnswersOne: MutableList<Int> = MutableList(listSize){ -1 }
    var listOfAnswersTwo: MutableList<Int> = MutableList(listSize){ -1 }
    var playerOneName: String = "Player One"
    var playerTwoName: String = "Player Two"

    init {
        listOfQuestions = allQuestions
            .shuffled()
            .take(listSize)
    }

    fun areAllQuestionsAnsweredOne(): Boolean{
        return !(listOfAnswersOne.contains(-1))
    }
    fun areAllQuestionsAnsweredTwo(): Boolean {
        return !(listOfAnswersTwo.contains(-1))
    }

    private fun calculateCommunality(): Double{
        var sum = 0.0
        for(i in 0 until listSize){
            if(listOfAnswersOne[i] == listOfAnswersTwo[i]){
                sum++
            }
        }
        return sum / listSize*100
    }

    fun formatPercentage(): String{
        val number = calculateCommunality()
        return String.format("%.2f", number)
    }

    fun restartGame(){
        changeQuestions()
        resetAnswersLists()
    }

    private fun changeQuestions(){
        listOfQuestions = allQuestions
            .shuffled()
            .take(listSize)
    }

    private fun resetAnswersLists(){
        listOfAnswersOne = MutableList(listSize){ -1 }
        listOfAnswersTwo = MutableList(listSize){ -1 }
    }

}