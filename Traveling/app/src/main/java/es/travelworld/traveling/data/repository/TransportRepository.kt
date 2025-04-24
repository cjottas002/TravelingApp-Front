package es.travelworld.traveling.data.repository

import es.travelworld.traveling.data.local.daos.TransportDao
import es.travelworld.traveling.data.local.entities.TransportEntity
import es.travelworld.traveling.domain.repository.ITransportRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TransportRepository @Inject constructor(private val transportDao: TransportDao) : ITransportRepository {
    override fun getTransports(): Flow<List<TransportEntity>> {
        return transportDao.getAllTransports()
    }
}