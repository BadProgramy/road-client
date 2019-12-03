package com.example.drive.requests.api

import com.example.drive.RoadProblemActivity
import com.example.drive.mocks.RoadProblemMock
import com.example.drive.model.City
import com.example.drive.model.GeoLocation
import com.example.drive.model.RoadProblem
import com.example.drive.response.RoadProblemResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface RoadProblemApi {

    @GET("/api/internal/city/{cityName}/road-problems/")
    fun getRoadProblemByCity(@Path("cityName") cityName: String): Call<List<RoadProblemResponse>>
}

class RoadProblemApiImpl : Callback<List<RoadProblemResponse>> {
    override fun onFailure(call: Call<List<RoadProblemResponse>>, t: Throwable) {
        println("[RoadProblemApiImpl] Ошибка ${t.message}, поэтому моки")
        RoadProblemActivity.cards = RoadProblemMock.getRoadProblems().toMutableList()
    }

    override fun onResponse(
        call: Call<List<RoadProblemResponse>>,
        response: Response<List<RoadProblemResponse>>
    ) {
        if (response.isSuccessful) {
            RoadProblemActivity.cards = response.body()!!.map {
                RoadProblem(
                    title = it.title,
                    description = it.description,
                    geoLocation = GeoLocation(
                        height = it.height,
                        width = it.width
                    ),
                    city = City(name = it.cityResponse.name)
                )
            }.toMutableList()
            return
        }
        println("Не хороший ответ от сервера со статусом ${response.code()}, поэтому моки")
        RoadProblemActivity.cards = RoadProblemMock.getRoadProblems().toMutableList()
    }
}