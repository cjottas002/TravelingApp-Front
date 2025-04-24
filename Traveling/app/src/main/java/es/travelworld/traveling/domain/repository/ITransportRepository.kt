package es.travelworld.traveling.domain.repository

import es.travelworld.traveling.data.local.entities.TransportEntity
import kotlinx.coroutines.flow.Flow

interface ITransportRepository {
    fun getTransports(): Flow<List<TransportEntity>>
}