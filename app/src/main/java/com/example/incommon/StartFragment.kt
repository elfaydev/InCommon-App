package com.example.incommon

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.incommon.databinding.FragmentStartBinding
import com.example.incommon.model.QuestionsViewModel


class StartFragment : Fragment() {

    val viewModel: QuestionsViewModel by activityViewModels()
    private var _binding: FragmentStartBinding? = null
    val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val fragmentBinding = FragmentStartBinding.inflate(inflater, container, false)
        _binding = fragmentBinding
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            button.setOnClickListener {

                if (textFieldOne.text.toString().isBlank() || textFieldTwo.text.toString()
                        .isBlank()
                ) {
                    if (textFieldOne.text.toString().isBlank()) {
                        setErrorTextFieldOne(true)
                    } else {
                        setErrorTextFieldOne(false)
                    }

                    if (textFieldTwo.text.toString().isBlank()) {
                        setErrorTextFieldTwo(true)
                    } else {
                        setErrorTextFieldTwo(false)
                    }
                } else {
                    setErrorTextFieldOne(false)
                    setErrorTextFieldTwo(false)
                    viewModel.playerOneName = playerOne.editText?.text.toString()
                    viewModel.playerTwoName = playerTwo.editText?.text.toString()
                    findNavController().navigate(R.id.action_startFragment_to_playerOneFragment)
                }

            }
        }
    }

    private fun setErrorTextFieldOne(error: Boolean) {
        if (error) {
            binding.playerOne.isErrorEnabled = true
            binding.playerOne.error = "Write a name"
        } else {
            binding.playerOne.isErrorEnabled = false
        }
    }

    private fun setErrorTextFieldTwo(error: Boolean) {
        if (error) {
            binding.playerTwo.isErrorEnabled = true
            binding.playerTwo.error = "Write a name"
        } else {
            binding.playerTwo.isErrorEnabled = false
        }
    }

}