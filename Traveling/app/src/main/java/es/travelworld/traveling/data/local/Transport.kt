package es.travelworld.traveling.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "transports")
data class TransportEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val imageRes: Int,
    val price: String
)