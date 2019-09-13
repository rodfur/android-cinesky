package com.example.cinesky.presentation.movies

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.example.cinesky.R
import com.example.cinesky.data.ApiService
import com.example.cinesky.data.model.Movie
import com.example.cinesky.data.response.MovieDetailsResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MoviesViewModel : ViewModel() {

    val moviesLiveData: MutableLiveData<List<Movie>> = MutableLiveData()
    val viewFlipperLiveData: MutableLiveData<Pair<Int, Int?>> = MutableLiveData()

    fun getBooks() {
        ApiService.service.getMovies().enqueue(object : Callback<List<MovieDetailsResponse>> {
            override fun onResponse(
                call: Call<List<MovieDetailsResponse>>,
                response: Response<List<MovieDetailsResponse>>
            ) {
                when {
                    response.isSuccessful -> {
                        val movies: MutableList<Movie> = mutableListOf()

                        response.body()?.let { movieDetailsResponse ->
                            for (result in movieDetailsResponse) {
                                val movie = result.getMovieModel()
                                movies.add(movie)
                            }
                        }

                        moviesLiveData.value = movies
                        viewFlipperLiveData.value = Pair(VIEW_FLIPPER_MOVIES, null)
                    }
                    response.code() == 401 -> viewFlipperLiveData.value = Pair(VIEW_FLIPPER_ERROR, R.string.movies_error_401)
                    else -> viewFlipperLiveData.value = Pair(VIEW_FLIPPER_ERROR, R.string.movies_error_400_generic)
                }
            }

            override fun onFailure(call: Call<List<MovieDetailsResponse>>, t: Throwable) {
                viewFlipperLiveData.value = Pair(VIEW_FLIPPER_ERROR, R.string.movies_error_500_generic)
            }
        })
    }

    companion object {
        private const val VIEW_FLIPPER_MOVIES = 1
        private const val VIEW_FLIPPER_ERROR = 2
    }
}