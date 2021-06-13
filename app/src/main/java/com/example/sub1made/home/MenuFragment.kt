package com.example.sub1made.home

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sub1made.R
import com.example.sub1made.core.data.Resource
import com.example.sub1made.core.ui.MovieAdapter
import com.example.sub1made.core.ui.ViewModelFactory
import com.example.sub1made.databinding.FragmentMenuBinding
import com.example.sub1made.detail.DetailActivity


class MenuFragment : Fragment() {

    private lateinit var menuViewModel: MenuViewModel

    private var _binding: FragmentMenuBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMenuBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null){
            val movieAdapter = MovieAdapter()
            movieAdapter.onItemClick = { selectData ->
                val intent =Intent(activity, DetailActivity::class.java)
                intent.putExtra(DetailActivity.EXTRA_DATA, selectData)
                startActivity(intent)
            }

            val factory = ViewModelFactory.getInstance(requireActivity())
            menuViewModel = ViewModelProvider(this, factory)[MenuViewModel::class.java]

            menuViewModel.movie.observe(viewLifecycleOwner, { movie ->
                if (movie != null){
                    when (movie){
                        is Resource.Loading -> binding.progressBar.visibility = View.VISIBLE
                        is Resource.Success -> {
                            binding.progressBar.visibility = View.GONE
                            movieAdapter.setData(movie.data)
                        }
                        is Resource.Error -> {
                            binding.progressBar.visibility = View.GONE
                            binding.viewError.root.visibility = View.VISIBLE
                            binding.viewError.tvError.text = movie.message ?: getString(R.string.something_wrong)
                        }
                    }
                }
            })
            with(binding.rvTourism) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = movieAdapter
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}