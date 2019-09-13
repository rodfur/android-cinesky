package com.example.cinesky.presentation.details

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.example.cinesky.R
import com.example.cinesky.data.model.Movie
import com.example.cinesky.presentation.base.BaseActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_movie_details.*
import kotlinx.android.synthetic.main.include_toolbar.*

class MovieDetailsActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)

        setupToolbar(toolbarMain, intent.getStringExtra(EXTRA_TITLE), true)

        textOverview.text = intent.getStringExtra(EXTRA_OVERVIEW)
        textDuration.text = intent.getStringExtra(EXTRA_DURATION)
        textReleaseYear.text = intent.getStringExtra(EXTRA_RELEASE_YEAR)

        Picasso.get().load(intent.getStringExtra(EXTRA_COVER_URL)).into(imageMovie)
    }

    companion object {
        private const val EXTRA_TITLE = "EXTRA_TITLE"
        private const val EXTRA_OVERVIEW = "EXTRA_OVERVIEW"
        private const val EXTRA_DURATION = "EXTRA_DURATION"
        private const val EXTRA_RELEASE_YEAR = "EXTRA_RELEASE_YEAR"
        private const val EXTRA_COVER_URL = "EXTRA_COVER_URL"

        fun getStartIntent(context: Context, movie: Movie): Intent {
            return Intent(context, MovieDetailsActivity::class.java).apply {
                putExtra(EXTRA_TITLE, movie.title)
                putExtra(EXTRA_OVERVIEW, movie.overview)
                putExtra(EXTRA_DURATION, movie.duration)
                putExtra(EXTRA_RELEASE_YEAR, movie.release_year)
                putExtra(EXTRA_COVER_URL, movie.cover_url)
            }

        }
    }
}
