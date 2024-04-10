package com.mykotlindemo.ui.fragment.middleFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.mykotlindemo.ViewModelFactory
import com.mykotlindemo.databinding.FragmentMiddleBinding

class MiddleFragment : Fragment() {

    lateinit var mBinding: FragmentMiddleBinding
    private lateinit var viewModel: MiddleFragmentViewModel<Any>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        mBinding = FragmentMiddleBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(
            this,
            ViewModelFactory(requireActivity(), mBinding)
        )[MiddleFragmentViewModel::class.java] as MiddleFragmentViewModel<Any>

        return mBinding.root
    }

}