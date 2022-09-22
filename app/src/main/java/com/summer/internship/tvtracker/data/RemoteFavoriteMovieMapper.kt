package com.summer.internship.tvtracker.data

import com.summer.internship.tvtracker.data.room.FavoriteLocal
import com.summer.internship.tvtracker.domain.FavoriteMovieUI
import java.time.Instant
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter

object RemoteFavoriteMovieMapper : EntityMapper<FavoriteLocal, FavoriteMovieUI> {
    override fun mapToEntity(from: FavoriteLocal): FavoriteMovieUI {
        return FavoriteMovieUI(
            from.name,
            from.backdropPath,
            from.date,
            from.id
        )
    }

    fun mapToLocalFromDetailsResponse(id: Long, detailsResponse: TvDetailsUi): FavoriteLocal {
        return FavoriteLocal(
            id,
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
    }
}