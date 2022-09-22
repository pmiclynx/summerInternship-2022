package com.summer.internship.tvtracker.data

interface EntityMapper<F,T> {
    fun mapToEntity(from :F) :T
}