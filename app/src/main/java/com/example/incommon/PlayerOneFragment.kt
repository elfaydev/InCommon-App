package com.example.incommon

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.incommon.adapter.QuestionAdapter
import com.example.incommon.databinding.FragmentPlayerOneBinding
import com.example.incommon.model.QuestionsViewModel


class PlayerOneFragment : Fragment() {

    val viewModel: QuestionsViewModel by activityViewModels()

    private lateinit var recyclerView: RecyclerView
    private var _binding: FragmentPlayerOneBinding? = null
    val binding get() = _binding!!
    private lateinit var adapter: QuestionAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val fragmentBinding = FragmentPlayerOneBinding.inflate(inflater, container, false)
        _binding = fragmentBinding
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        adapter = QuestionAdapter(viewModel.listOfQuestions, viewModel.listOfAnswersOne, requireContext())
        recyclerView.adapter = adapter
        binding.playerOneName.text = viewModel.playerOneName
        adapter.getAnswers { updatedListOfAnswers ->
            viewModel.listOfAnswersOne = updatedListOfAnswers
        }

        binding.nextPage.setOnClickListener {
            if(viewModel.areAllQuestionsAnsweredOne()){
                findNavController().navigate(R.id.action_playerOneFragment_to_playerTwoFragment)
            }else{
                val duration = Toast.LENGTH_SHORT
                val toast = Toast.makeText(requireContext(), "Answer all the questions", duration)
                toast.show()
            }
        }


    }


}