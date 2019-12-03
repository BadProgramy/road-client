package com.example.drive.mocks

import com.example.drive.model.City

class CityMock {
    companion object {
        fun city(): List<City> =
            listOf(
                City(name = "Самара"),
                City(name = "Москва"),
                City(name = "Тольятти")
            )
    }
}