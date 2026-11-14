package com.busol.habittracker.app

import android.app.Application
import com.example.articles.di.articlesModule
import com.example.auth.di.authFeatureModule
import com.example.profile.di.profileModule
import com.google.firebase.FirebaseApp
import com.busol.habittracker.core.data.di.completionHistoryDataModule
import com.busol.habittracker.core.data.di.completionTimeDataModule
import com.busol.habittracker.core.data.di.remoteModule
import com.busol.habittracker.core.data.di.routineDataModule
import com.busol.habittracker.core.data.di.streakDataModule
import com.busol.habittracker.core.data.di.vacationDataModule
import com.busol.habittracker.core.database.di.completionTimeLocalDataModule
import com.busol.habittracker.core.database.di.habitLocalDataModule
import com.busol.habittracker.core.database.di.localDataSourceModule
import com.busol.habittracker.core.database.di.streakLocalDataModule
import com.busol.habittracker.core.domain.databaseprepopulator.DatabasePrepopulator
import com.busol.habittracker.core.domain.databaseprepopulator.databasePrepopulatorModule
import com.busol.habittracker.core.domain.di.completionHistoryDomainModule
import com.busol.habittracker.core.domain.di.completionTimeDomainModule
import com.busol.habittracker.core.domain.di.domainModule
import com.busol.habittracker.core.domain.di.habitDomainModule
import com.busol.habittracker.core.domain.di.streakDomainModule
import com.busol.habittracker.feature.agenda.di.agendaScreenModule
import com.busol.habittracker.routinedetails.di.routineDetailsModule
import kotlin.coroutines.CoroutineContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.qualifier.named

class RoutineTrackerApp : Application() {
    private val ioDispatcher by inject<CoroutineContext>(qualifier = named("ioDispatcher"))
    private val applicationScope = CoroutineScope(SupervisorJob())
    private val databasePrepopulator by inject<DatabasePrepopulator>()

    override fun onCreate() {
        super.onCreate()
        FirebaseApp.initializeApp(this)
        startKoin {
            androidContext(this@RoutineTrackerApp)
            modules(
                localDataSourceModule,
                habitLocalDataModule,
                completionTimeLocalDataModule,
                streakLocalDataModule,

                routineDataModule,
                completionHistoryDataModule,
                completionTimeDataModule,
                vacationDataModule,
                streakDataModule,
                databasePrepopulatorModule,
                remoteModule,

                domainModule,
                habitDomainModule,
                completionHistoryDomainModule,
                streakDomainModule,
                completionTimeDomainModule,
                authFeatureModule,
                profileModule,
                articlesModule,

                agendaScreenModule,
                routineDetailsModule,
            )
        }

        applicationScope.launch(ioDispatcher) {
            databasePrepopulator.prepopulateDatabase(numOfHabits = 30)
        }
    }
}