package com.busol.habittracker.core.data.di

import androidx.room.Room
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.busol.habittracker.core.data.db.HabitDatabase
import com.busol.habittracker.core.data.db.dao.InfoDao
import com.busol.habittracker.core.data.repository.FirebaseRepository
import com.busol.habittracker.core.data.repository.FirebaseRepositoryImpl
import com.busol.habittracker.core.data.repository.InfoRepository
import com.busol.habittracker.core.data.repository.InfoRepositoryImpl
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val remoteModule = module {
    single {
        Firebase.auth
    }

    single {
        Firebase.firestore
    }

    factory<FirebaseRepository> {
        FirebaseRepositoryImpl(get(), get())
    }

    single {
        Room.databaseBuilder(
            androidContext(),
            HabitDatabase::class.java,
            "habitDB",
        ).fallbackToDestructiveMigration()
            .apply {
                //Enable DB logging if needed
//                if (BuildConfig.DEBUG) {
//                    setQueryCallback({ sqlQuery, bindArgs ->
//                        Log.d("Database", "Query: $sqlQuery, Args: $bindArgs")
//                    }, Executors.newSingleThreadExecutor())
//                }
            }
            .build()
    }

    single<InfoDao> {
        get<HabitDatabase>().infoDao()
    }

    factory<InfoRepository> {
        InfoRepositoryImpl(get(), get())
    }
}