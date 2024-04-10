package com.mykotlindemo.ui.fragment.homeFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.mykotlindemo.ViewModelFactory
import com.mykotlindemo.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private lateinit var mBinding: FragmentHomeBinding
    private lateinit var viewModel: HomeFragmentViewModel<Any>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        mBinding = FragmentHomeBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(
            this,
            ViewModelFactory(requireActivity(), mBinding)
        )[HomeFragmentViewModel::class.java] as HomeFragmentViewModel<Any>
        return mBinding.root
    }
}