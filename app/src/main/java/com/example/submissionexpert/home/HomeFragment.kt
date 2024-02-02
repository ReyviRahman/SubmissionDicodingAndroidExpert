package com.example.submissionexpert.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.submissionexpert.R
import com.example.submissionexpert.core.data.Resource
import com.example.submissionexpert.core.ui.GameAdapter
import com.example.submissionexpert.core.ui.ViewModelFactory
import com.example.submissionexpert.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private lateinit var homeViewModel: HomeViewModel

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {
            val gameAdapter = GameAdapter()

            val factory = ViewModelFactory.getInstance(requireActivity())
            homeViewModel = ViewModelProvider(this, factory)[HomeViewModel::class.java]

            homeViewModel.game.observe(viewLifecycleOwner) { game ->
                if (game != null) {
                    when(game) {
                        is Resource.Loading -> {
                            binding.progressBar.visibility = View.VISIBLE
                        }
                        is Resource.Success -> {
                            binding.progressBar.visibility = View.GONE
                            gameAdapter.submitList(game.data)
                        }
                        is Resource.Error -> {
                            binding.progressBar.visibility = View.GONE
                            binding.viewError.root.visibility = View.VISIBLE
                            binding.viewError.tvError.text =
                                game.message ?: "Opps.. SOmething went wrong"
                        }
                    }
                }
            }

            with(binding.rvGame) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = gameAdapter
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}