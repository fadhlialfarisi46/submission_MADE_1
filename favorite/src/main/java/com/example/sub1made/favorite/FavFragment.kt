package com.example.sub1made.favorite

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sub1made.core.ui.MovieAdapter
import com.example.sub1made.detail.DetailActivity
import com.example.sub1made.favorite.databinding.FragmentFavBinding
import com.example.sub1made.favoritesModule
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class FavFragment : Fragment() {
    private val favoriteMovieViewModel: FavoriteMovieViewModel by viewModel()

    private var _binding : FragmentFavBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavBinding.inflate(inflater, container, false)
        loadKoinModules(favoritesModule)
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

            favoriteMovieViewModel.fovMovie.observe(viewLifecycleOwner, { dataMovie ->
                movieAdapter.setData(dataMovie)
                binding.viewEmpty.root.visibility = if (dataMovie.isNotEmpty()) View.GONE else View.VISIBLE
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