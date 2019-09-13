package com.example.cinesky.presentation.movies

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.GridView
import android.widget.ImageView
import android.widget.TextView
import com.example.cinesky.R
import com.example.cinesky.data.model.Movie
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_movie.view.*
import kotlinx.android.synthetic.main.item_movie.view.textTitle

class MoviesAdapter(
    val context: Context,
    val movies: List<Movie>,
    val onItemClickListener: ((movie: Movie) -> Unit)
) : BaseAdapter() {

    var mPos: Int = -1

    override fun getCount(): Int {

        return movies.size
    }

    override fun getItemId(position: Int): Long {

        return position.toLong()
    }

    override fun getItem(position: Int): Any {

        return movies[position]
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View? {

        var convertView = convertView

        val holder: ItemHolder

        val movie = movies[position]

        if (convertView == null) {

            val layoutInflater = LayoutInflater.from(context)

            convertView = layoutInflater.inflate(R.layout.item_movie, parent, false)

            holder = ItemHolder()

            holder.title = convertView.textTitle
            holder.cover = convertView.imageCover

            convertView.tag = holder
        }
        else
        {
            holder = convertView.tag as ItemHolder
        }

        holder.title!!.text = movie.title

        Picasso.get().load(movie.cover_url).into(holder!!.cover)

        convertView!!.setOnClickListener { onItemClickListener(movie) }

        return convertView
    }

    internal class ItemHolder {

        var title: TextView? = null
        var cover: ImageView? = null
    }
}