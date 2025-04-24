package es.travelworld.traveling.core.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import es.travelworld.traveling.data.local.AppDatabase
import es.travelworld.traveling.data.local.daos.TransportDao
import es.travelworld.traveling.data.local.daos.UserDao
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(appContext, AppDatabase::class.java, "traveling.db")
            .fallbackToDestructiveMigration(true)
            .build()
    }

    @Provides
    fun provideTransportDao(db: AppDatabase): TransportDao {
        return db.transportDao()
    }

    @Provides
    fun provideUserDao(db: AppDatabase): UserDao {
        return db.userDao()
    }

}
