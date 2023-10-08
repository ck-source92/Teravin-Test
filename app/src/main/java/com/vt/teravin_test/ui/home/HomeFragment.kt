package com.vt.teravin_test.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.vt.teravin_test.core.source.Resource
import com.vt.teravin_test.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val homeViewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observerView()
    }

    private fun observerView() {
        homeViewModel.movies.observe(viewLifecycleOwner) {
            if (it.data.isNullOrEmpty()) {
                when (it) {
                    is Resource.Loading -> {
                        println("loading")
                        println(it.data)
                    }

                    is Resource.Success -> {
                        println("success")
                        println(it.data)
                    }

                    is Resource.Error -> {
                        println("error")
                        println(it.message)
                    }

                    else -> {}
                }
            } else {
                println("else data ${it.data}")
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}