package es.travelworld.traveling.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import es.travelworld.traveling.data.local.daos.TransportDao
import es.travelworld.traveling.data.local.daos.UserDao
import es.travelworld.traveling.data.local.entities.TransportEntity
import es.travelworld.traveling.data.local.entities.UserEntity

@Database(
    entities = [
        TransportEntity::class,
        UserEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun transportDao(): TransportDao
    abstract fun userDao(): UserDao
}