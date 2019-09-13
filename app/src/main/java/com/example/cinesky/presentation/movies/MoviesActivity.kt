package com.example.cinesky.presentation.movies

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.widget.GridView
import com.example.cinesky.R
import com.example.cinesky.presentation.base.BaseActivity
import com.example.cinesky.presentation.details.MovieDetailsActivity
import kotlinx.android.synthetic.main.activity_movies.*
import kotlinx.android.synthetic.main.include_toolbar.*

class MoviesActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movies)

        setupToolbar(toolbarMain, getString(R.string.movies_title))

        val viewModel: MoviesViewModel = ViewModelProviders.of(this).get(MoviesViewModel::class.java)

        viewModel.moviesLiveData.observe(this, Observer {
            it?.let { movies ->

                with(gridMovies){

                    adapter = MoviesAdapter(this@MoviesActivity, movies ) { movie ->
                        val intent = MovieDetailsActivity.getStartIntent(this@MoviesActivity, movie)
                        this@MoviesActivity.startActivity(intent)
                    }

                    numColumns = 2
                    horizontalSpacing = 5
                    verticalSpacing = 5
                    stretchMode = GridView.STRETCH_COLUMN_WIDTH
                }
            }
        })

        viewModel.viewFlipperLiveData.observe(this, Observer {
            it?.let { viewFlipper ->
                viewFlipperMovies.displayedChild = viewFlipper.first

                viewFlipper.second?.let { errorMessageResId ->
                    textViewError.text = getString(errorMessageResId)
                }
            }
        })

        viewModel.getBooks()
    }
}
