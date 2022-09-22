package com.summer.internship.tvtracker.data

import android.util.Log
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import com.summer.internship.tvtracker.domain.FavoriteMovieUI
import com.summer.internship.tvtracker.domain.FavoritesDataSource
import io.reactivex.rxjava3.core.Single
import java.time.Instant
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter

class FavoriteFirebaseDataSource(private val favorites:CollectionReference) : FavoritesDataSource {

    private val userID = Firebase.auth.currentUser?.uid

    override fun addFavorite(
        detailsResponse: TvDetailsUi,
        id: Long?
    ): Single<String> {
        return Single.create { emitter ->
            userID?.let {
                val favoritesRef = favorites.document(it).collection("favoriteMovies")
                id?.let {
                    favoritesRef
                        .document(id.toString())
                        .get()
                        .addOnSuccessListener { result ->
                            if (!result.exists()) {
                                favoritesRef.document(id.toString()).set(
                                    MovieFavoriteRemote(
                                        detailsResponse.backdropPath,
                                        detailsResponse.overView,
                                        detailsResponse.posterPath,
                                        detailsResponse.voteAverage,
                                        detailsResponse.name,
                                        DateTimeFormatter
                                            .ofPattern("yyyy-MM-dd")
                                            .withZone(ZoneOffset.UTC)
                                            .format(Instant.now())
                                    )
                                )
                                emitter.onSuccess(id.toString())

                            } else {
                                emitter.onError(Throwable("movie already exists"))
                            }
                        }
                        .addOnFailureListener { exp ->
                            emitter.onError(exp)
                        }
                }
            }
        }

    }


    override fun getFavorites(): Single<List<FavoriteMovieUI>> {
        return Single.create { emitter ->
            userID?.let {
                val favoritesRef = favorites.document(it).collection("favoriteMovies")
                favoritesRef
                    .get()
                    .addOnSuccessListener { result ->
                        val favoriteMovieList: ArrayList<FavoriteMovieUI> =
                            arrayListOf<FavoriteMovieUI>()
                        for (document in result) {
                            Log.d("ao", document.data.toString())
                            val response = document.toObject<FavoriteResponse>()
                            favoriteMovieList.add(
                                FavoriteMovieUI(
                                    response.name!!,
                                    response.backdropPath!!,
                                    response.date!!,
                                    document.id.toLong()
                                )
                            )
                        }
                        Log.d("aa", favoriteMovieList.toString())
                        emitter.onSuccess(favoriteMovieList)
                    }
                    .addOnFailureListener { exp ->
                        Log.d("aaaaaaa", exp.toString())
                        emitter.onError(exp)
                    }
            }
        }
    }

    override fun deleteFavorite(id: Long) {
        userID?.let {
            val favoritesRef = favorites.document(it).collection("favoriteMovies")
            favoritesRef.document(id.toString()).delete()
                .addOnSuccessListener {
                }
                .addOnFailureListener { e ->
                    Log.d("airb", "Error deleting document", e)
                }
        }
    }
}