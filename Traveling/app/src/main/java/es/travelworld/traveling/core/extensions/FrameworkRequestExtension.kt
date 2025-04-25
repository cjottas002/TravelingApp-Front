package es.travelworld.traveling.core.extensions

import es.travelworld.traveling.core.request.FrameworkRequest

fun FrameworkRequest.toQueryMap(): Map<String, String> = mapOf(
    "pageIndex"   to pageIndex.toString(),
    "pageSize"    to pageSize.toString(),
    "orderBy"     to orderBy,
    "orderByAsc"  to orderByAsc.toString()
)