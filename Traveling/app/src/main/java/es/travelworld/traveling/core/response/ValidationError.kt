package es.travelworld.traveling.core.response


data class ValidationError(
    val fields: List<String>,
    val message: String
)