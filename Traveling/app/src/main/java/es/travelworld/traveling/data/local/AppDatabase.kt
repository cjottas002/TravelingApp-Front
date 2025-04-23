package es.travelworld.traveling.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [TransportEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun transportDao(): TransportDao
}