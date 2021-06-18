package com.example.sub1made.core.ui

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.sub1made.core.R
import com.example.sub1made.core.databinding.ItemListMovieBinding
import com.example.sub1made.core.domain.model.Movie

class MovieAdapter: RecyclerView.Adapter<MovieAdapter.ListViewHolder>() {

    private var listMovie = ArrayList<Movie>()
    var onItemClick: ((Movie) -> Unit)? = null

    fun setData(newListData: List<Movie>?){
        if (newListData == null) return
        listMovie.clear()
        listMovie.addAll(newListData)
        notifyDataSetChanged()
    }
    inner class ListViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val binding = ItemListMovieBinding.bind(itemView)
        private val imgUrl = itemView.context.resources.getString(R.string.baseUrlImage)
        fun bind(data: Movie){
            val image = buildString {
                append(imgUrl)
                append(data.backdropPath)
            }
            Log.d("GLIDING IMAGE", image)
            with(binding){
                Glide.with(itemView.context)
                    .load(image)
                    .apply(
                        RequestOptions.placeholderOf(R.drawable.ic_loading)
                            .error(R.drawable.ic_error)
                    )
                    .into(ivItemImage)
                tvItemTitle.text = data.title
                tvItemSubtitle.text = data.releaseDate
            }
        }

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(listMovie[absoluteAdapterPosition])
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder =
        ListViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_list_movie, parent, false))


    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val data = listMovie[position]
        holder.bind(data)
    }

    override fun getItemCount(): Int = listMovie.size
}