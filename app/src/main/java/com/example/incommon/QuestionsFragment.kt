package com.example.incommon

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.incommon.databinding.FragmentQuestionsBinding
import com.example.incommon.databinding.FragmentStartBinding
import com.example.incommon.module.QuestionsViewModel


class QuestionsFragment : Fragment() {

    val viewModel: QuestionsViewModel by activityViewModels()

    private var binding: FragmentQuestionsBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val fragmentBinding = FragmentQuestionsBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        return fragmentBinding.root
    }


}