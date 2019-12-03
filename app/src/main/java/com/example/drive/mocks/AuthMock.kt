package com.example.drive.mocks

import com.example.drive.response.AuthResponse

class AuthMock {
    companion object {
        fun auth() : AuthResponse =
            AuthResponse(status = 200)
    }
}