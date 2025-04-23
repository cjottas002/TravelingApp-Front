package es.travelworld.traveling.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface TransportDao {
    @Query("SELECT * FROM transports")
    fun getAllTransports(): Flow<List<TransportEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(transports: List<TransportEntity>)
}