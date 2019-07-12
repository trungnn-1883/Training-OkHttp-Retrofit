package com.example.training_okhttp_retrofit.api.callback

import retrofit2.Response

class MockioError : ApiErrorHandler(){

    override fun getExceptionType(response: Response<*>): Exception {
        return when (response.code()) {
            NETWORK_ERROR -> NetworkException()
            FORBIDDEN_ERROR -> ForbiddenException()
            MOCKIO_ERROR -> GitHubException()
            else -> Exception()
        }
    }

    companion object ErrorConfig {
        const val NETWORK_ERROR = 401
        const val FORBIDDEN_ERROR = 403
        const val MOCKIO_ERROR = 404

        class NetworkException : Exception() {
            override val message = "Error due to network"
        }

        class ForbiddenException : Exception() {
            override val message = "This link is forbidden"
        }

        class GitHubException : Exception() {
            override val message = "Error from Mockio"
        }
    }
}