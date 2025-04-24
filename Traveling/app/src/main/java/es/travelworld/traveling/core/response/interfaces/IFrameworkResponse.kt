package es.travelworld.traveling.core.response.interfaces

interface IFrameworkResponse<T> {
    val data: T?
    val count: Int
}