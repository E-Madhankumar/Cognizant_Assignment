package com.assignment.network

import androidx.annotation.NonNull
import java.net.ConnectException
import java.net.SocketTimeoutException
enum class Status {
    SUCCESS,
    ERROR,
    LOADING
}
class Response<out T> constructor(val status: Status, val result: T?, val message: String?) {
    companion object {

        fun <T> success(@NonNull result: T): Response<T> {
            return Response(
                Status.SUCCESS,
                result,
                null
            )
        }

        fun <T> error(result: T?,error:String): Response<T> {
            return Response(
                Status.ERROR,
                null,
                error
            )
        }

        fun <T> loading(): Response<T> {
            return Response(
                Status.LOADING,
                null,
                null
            )
        }
        fun <T> handleException(e: Exception,result: T?=null): Response<T> {
            return Response(
                Status.ERROR,
                null,
                getErrorMessage(e)
            )
        }

        private fun getErrorMessage(e: Exception): String {
            return when (e) {
                is SocketTimeoutException -> "The server is taking longer than expected to respond"
                is ConnectException -> "Make sure you are connected to WI-FI or mobile network and try again"
//                is HttpException ->{
//                    when(e.code()){
//                        401 -> "Your session has expired"
//                        404 -> "Your request data is not found and couldn't complete your request"
//                        409 -> "Conflict"
//                        else -> "The server encountered a temporary error and couldn't complete your request"
//                    }
//                }
                else -> "The server encountered a temporary error and couldn't complete your request"

            }
        }
    }

}