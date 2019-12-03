package com.example.drive.response

import java.time.ZonedDateTime

data class AuthResponse(
    val status: Int?
)

data class CityResponse(
    val name: String
)

data class RoadProblemResponse(
    val id: Long,
    val title: String,
    val description: String,
    val height: Double,
    val width: Double,
    val createdAt: ZonedDateTime,
    val cityResponse: CityResponse
)