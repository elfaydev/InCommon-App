package com.example.incommon

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.incommon.adapter.QuestionAdapter
import com.example.incommon.databinding.FragmentPlayerTwoBinding
import com.example.incommon.model.QuestionsViewModel
import com.google.android.material.dialog.MaterialAlertDialogBuilder


class PlayerTwoFragment : Fragment() {

    private val viewModel: QuestionsViewModel by activityViewModels()
    private lateinit var recyclerView: RecyclerView
    private var _binding: FragmentPlayerTwoBinding? = null
    val binding get() = _binding!!
    private lateinit var adapter: QuestionAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val fragmentBinding = FragmentPlayerTwoBinding.inflate(inflater,container, false)
        _binding = fragmentBinding
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        adapter = QuestionAdapter(viewModel.listOfQuestions, viewModel.listOfAnswersTwo, requireContext())
        recyclerView.adapter = adapter
        binding.playerTwoName.text = viewModel.playerTwoName
        adapter.getAnswers { updatedListOfAnswers ->
            viewModel.listOfAnswersTwo = updatedListOfAnswers
        }

        binding.showResultButton.setOnClickListener {
            val duration = Toast.LENGTH_SHORT
            val communality = viewModel.formatPercentage()
            if(viewModel.areAllQuestionsAnsweredTwo()){
                showFinalScoreDialog(communality)
            }else{
                val toast = Toast.makeText(requireContext(), "Answer all the questions", duration)
                toast.show()
            }
        }

    }

    private fun showFinalScoreDialog(score: String) {
        MaterialAlertDialogBuilder(requireContext())
            .setTitle("RESULT")
            .setMessage("Your Communality is: $score%")
            .setCancelable(false)
            .setPositiveButton("Play Again"){_,_ ->
                restartGame()
            }
            .setNegativeButton("Exit"){_,_ ->
                exitGame()
            }
            .show()
    }

    private fun exitGame() {
        activity?.finish()
    }

    private fun restartGame() {
       viewModel.restartGame()
        findNavController().navigate(R.id.action_playerTwoFragment_to_playerOneFragment)
    }


}