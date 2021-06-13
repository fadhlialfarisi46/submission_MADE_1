package com.example.sub1made.favorite

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sub1made.core.ui.MovieAdapter
import com.example.sub1made.core.ui.ViewModelFactory
import com.example.sub1made.databinding.FragmentFavBinding
import com.example.sub1made.detail.DetailActivity

class FavFragment : Fragment() {
    private lateinit var favoriteMovieViewModel: FavoriteMovieViewModel

    private var _binding : FragmentFavBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null){
            val movieAdapter = MovieAdapter()
            movieAdapter.onItemClick = { selectedMovie ->
                val intent = Intent(activity, DetailActivity::class.java)
                intent.putExtra(DetailActivity.EXTRA_DATA, selectedMovie)
                startActivity(intent)
            }

            val factory = ViewModelFactory.getInstance(requireActivity())
            favoriteMovieViewModel = ViewModelProvider(this, factory)[FavoriteMovieViewModel::class.java]

            favoriteMovieViewModel.fovMovie.observe(viewLifecycleOwner, { dataMovie ->
                movieAdapter.setData(dataMovie)
                binding.viewError.root.visibility = if (dataMovie.isNotEmpty()) View.GONE else View.VISIBLE
            })

            with(binding.rvTourism){
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