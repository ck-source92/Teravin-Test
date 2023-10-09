package com.vt.teravin_test.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.vt.teravin_test.NetworkError
import com.vt.teravin_test.core.domain.model.Movie
import com.vt.teravin_test.core.source.Resource
import com.vt.teravin_test.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val homeViewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
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
            if (it != null) {
                when (it) {
                    is Resource.Loading -> {
                        binding.progressBar.isVisible = true
                    }

                    is Resource.Success -> {
                        binding.progressBar.isVisible = false
                        setupRecyclerView(it.data)
                    }

                    is Resource.Error -> {
                        binding.progressBar.isVisible = false
                        Toast.makeText(
                            requireActivity(),
                            "No Internet Connection\n" + it.message,
                            Toast.LENGTH_SHORT
                        ).show()
                        NetworkError(requireActivity()).showNoConnectionDialog()
                    }
                }
            }
        }
        homeViewModel.offlineMovie.observe(viewLifecycleOwner) {
            setupRecyclerView(it)
        }
    }

    private fun setupRecyclerView(movie: List<Movie>?) {
        val movieAdapter = HomeAdapter()
        movieAdapter.submitList(movie)
        with(binding.rvMovie) {
            layoutManager = GridLayoutManager(context, 2)
            setHasFixedSize(true)
            adapter = movieAdapter
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}