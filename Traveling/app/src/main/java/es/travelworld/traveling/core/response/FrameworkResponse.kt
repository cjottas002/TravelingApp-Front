package es.travelworld.traveling.core.response

import es.travelworld.traveling.core.response.interfaces.IFrameworkResponse

data class FrameworkResponse<TResponseDto : ResponseDto>(
    override val data: TResponseDto? = null,
    override val count: Int = 0,
    val errors: List<ValidationError> = emptyList(),
    val success: Boolean = errors.isEmpty()
) : IFrameworkResponse<TResponseDto>