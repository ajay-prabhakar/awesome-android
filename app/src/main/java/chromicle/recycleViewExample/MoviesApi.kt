package chromicle.recycleViewExample

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

const val BASE_URL = "https://api.simplifiedcoding.in/course-apis/recyclerview/"


/**
 *@author Chromicle
 */


interface MoviesApi {

    @GET("movies")
    fun getMovies() : Call<List<Movie>>

    companion object {
        operator fun invoke() : MoviesApi {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(MoviesApi::class.java)
        }
    }
}