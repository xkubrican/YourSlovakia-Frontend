package com.example.yourslovakiafrontend.api_handler

import fiit.mtaa.yourslovakia.models.AuthenticationRequest
import okhttp3.*
import java.io.IOException
import com.google.gson.Gson
import fiit.mtaa.yourslovakia.models.AuthenticationResponse
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody

val client = OkHttpClient()
private val baseUrl = "yourslovakia.streicher.tech/"
private var jwtToken = ""
private var refreshToken = ""
fun register(authenticationRequest: AuthenticationRequest) {
    val jsonBody = Gson().toJson(authenticationRequest)

    val requestBody = jsonBody.toRequestBody("application/json".toMediaTypeOrNull())

    val request = Request.Builder()
        .url(baseUrl + "create_user")
        .post(requestBody)
        .build()

    client.newCall(request).enqueue(object : Callback {
        override fun onFailure(call: Call, e: IOException) {
            e.printStackTrace()
        }

        override fun onResponse(call: Call, response: Response) {
            if (response.isSuccessful) {
                val responseData = response.body?.string()
                if (responseData == "true") {
                    println("Response is true")
                }
            } else {
                println("Unsuccessful response")
            }
        }
    })
}