package com.summer.internship.tvtracker.data

import com.summer.internship.tvtracker.data.room.MovieItemPopularLocal
import com.summer.internship.tvtracker.data.room.MovieItemTopRatedLocal
import com.summer.internship.tvtracker.domain.MovieUI

object RemoteMovieMapper : EntityMapper<MovieRemote, MovieUI> {
    override fun mapToEntity(from: MovieRemote): MovieUI {
        return MovieUI(
            from.name,
            from.backdrop_path,
            from.id
        )
    }

    fun mapToPopularRoom(from: MovieUI): MovieItemPopularLocal {
        return MovieItemPopularLocal(
            from.id,
            from.title,
            from.url
        )
    }

    fun mapToTopRatedRoom(from: MovieUI): MovieItemTopRatedLocal {
        return MovieItemTopRatedLocal(
            from.id,
            from.title,
            from.url
        )
    }

    fun mapFromPopularRoom(from: MovieItemPopularLocal): MovieUI {
        return MovieUI(
            from.title,
            from.url,
            from.id
        )
    }

    fun mapFromTopRatedRoom(from: MovieItemTopRatedLocal): MovieUI {
        return MovieUI(
            from.title,
            from.url,
            from.id
        )
    }
}