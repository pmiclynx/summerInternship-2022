package com.summer.internship.tvtracker.data

object TvDetailsMapper : EntityMapper<TvDetailsResponse, TvDetailsUi> {
    override fun mapToEntity(from: TvDetailsResponse): TvDetailsUi {
        return TvDetailsUi(
            from.backdropPath,
            from.overView,
            from.posterPath,
            from.voteAverage,
            from.name
        )
    }
}