package com.example.drive.mocks

import com.example.drive.model.GeoLocation
import com.example.drive.model.RoadProblem

class RoadProblemMock {
    companion object {
        fun getRoadProblems(): List<RoadProblem> =
            listOf(
                RoadProblem(
                    title = "Плохая грунтовка",
                    description = "Дороги очень ужасные, разберитесь пожалуйста",
                    geoLocation = GeoLocation(
                        height = 53.206290,
                        width = 50.195185
                    ),
                    city = CityMock.city()[0]
                ),

                RoadProblem(
                    title = "Узкие дворовые территории",
                    description = "Много дорожных дыр",
                    geoLocation = GeoLocation(
                        height = 53.208002,
                        width = 50.165473
                    ),
                    city = CityMock.city()[0]
                ),

                RoadProblem(
                    title = "Плоой асфальт",
                    description = "Засыпали дорогу черт-знает чем",
                    geoLocation = GeoLocation(
                        height = 53.201221,
                        width = 50.176117
                    ),
                    city = CityMock.city()[1]
                ),

                RoadProblem(
                    title = "Нету дорожных указателей",
                    description = "Водители начали заблуждаться в пересчениях дороги",
                    geoLocation = GeoLocation(
                        height = 53.196937,
                        width = 50.187273
                    ),
                    city = CityMock.city()[2]
                )
            )
    }
}