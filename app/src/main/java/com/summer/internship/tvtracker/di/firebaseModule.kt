package com.summer.internship.tvtracker.di

import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import org.koin.dsl.module

val firebaseModule = module {
    single {
        val db = Firebase.firestore
        db.collection("favorites")
    }
}