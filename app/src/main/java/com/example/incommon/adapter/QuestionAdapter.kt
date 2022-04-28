package com.example.incommon.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.incommon.R
import com.example.incommon.model.QuestionModel

class QuestionAdapter(private val listOfQuestions: List<QuestionModel>, private val listOfAnswers: MutableList<Int>, private val context: Context): RecyclerView.Adapter<QuestionAdapter.QuestionViewHolder>() {



    class QuestionViewHolder(val view: View): RecyclerView.ViewHolder(view) {
        val radioGroup = view.findViewById<RadioGroup>(R.id.radioGroup)
        val textQuestion = view.findViewById<TextView>(R.id.question_text)
        val yesButton = view.findViewById<RadioButton>(R.id.yes)
        val noButton = view.findViewById<RadioButton>(R.id.no)

    }

    private lateinit var callBack: (listOfAnswers: MutableList<Int>)  -> Unit
    fun getAnswers(callback: (item: MutableList<Int>) -> Unit){
        callBack = callback
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionViewHolder {
        val layout = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_view,parent, false)
        return QuestionViewHolder(layout)
    }

    override fun onBindViewHolder(holder: QuestionViewHolder, position: Int) {
        val item = listOfQuestions[position]
        holder.textQuestion.text = context.resources.getString(item.questionId)


        holder.radioGroup.setOnCheckedChangeListener(null)
        holder.radioGroup.clearCheck()
        when(listOfAnswers[holder.bindingAdapterPosition]){
            0 -> holder.noButton.isChecked = true
            1 -> holder.yesButton.isChecked = true
        }

        holder.radioGroup.setOnCheckedChangeListener{_, checkedId ->
            when(checkedId){
                R.id.yes -> listOfAnswers[holder.bindingAdapterPosition] = 1
                R.id.no  -> listOfAnswers[holder.bindingAdapterPosition] = 0
            }
            callBack(listOfAnswers)
        }



    }

    override fun getItemCount(): Int {
        return listOfAnswers.size
    }
}