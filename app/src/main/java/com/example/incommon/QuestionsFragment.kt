package com.example.incommon

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.incommon.adapter.QuestionAdapter
import com.example.incommon.databinding.FragmentQuestionsBinding
import com.example.incommon.model.QuestionsViewModel


class QuestionsFragment : Fragment() {

    val viewModel: QuestionsViewModel by activityViewModels()

    private lateinit var recyclerView: RecyclerView
    private var _binding: FragmentQuestionsBinding? = null
    val binding get() = _binding!!
    private lateinit var adapter: QuestionAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val fragmentBinding = FragmentQuestionsBinding.inflate(inflater, container, false)
        _binding = fragmentBinding
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        adapter = QuestionAdapter(viewModel.listOfQuestions, viewModel.listOfAnswers, requireContext())
        recyclerView.adapter = adapter
        adapter.getAnswers { updatedListOfAnswers ->
            viewModel.listOfAnswers = updatedListOfAnswers
        }


    }


}