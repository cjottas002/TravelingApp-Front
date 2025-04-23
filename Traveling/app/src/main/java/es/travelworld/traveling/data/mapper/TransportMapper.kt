package es.travelworld.traveling.data.mapper

import es.travelworld.traveling.data.local.TransportEntity
import es.travelworld.traveling.data.remote.Transport

fun TransportEntity.toTransport() : Transport {
    return Transport(
        name = this.name,
        imageRes = this.imageRes,
        price = this.price
    )
}