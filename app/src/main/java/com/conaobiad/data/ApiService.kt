package com.conaobiad.data

import android.content.Context
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.conaobiad.data.model.Obiad
import org.json.JSONArray
import org.json.JSONObject

class ApiHandler(private val context: Context) {

    private val requestQueue: RequestQueue by lazy {
        Volley.newRequestQueue(context)
    }

    fun fetchObiady(onSuccess: (List<Obiad>) -> Unit, onError: (Exception) -> Unit) {
        val url = "https://co-na-obiad-api.onrender.com/get_obiad"

        val request = JsonArrayRequest(
            Request.Method.GET,
            url,
            null,
            { response ->
                try {
                    val obiadList = parseResponse(response)
                    onSuccess(obiadList)
                } catch (e: Exception) {
                    onError(e)
                }
            },
            { error ->
                onError(Exception(error.message))
            }
        )

        requestQueue.add(request)
    }

    private fun parseResponse(response: JSONArray): List<Obiad> {
        val obiadList = mutableListOf<Obiad>()
        for (i in 0 until response.length()) {
            val item: JSONObject = response.getJSONObject(i)
            val obiad = Obiad(
                alergeny = item.getString("alergeny"),
                dataDnia = item.getString("data_dnia"),
                dataTygodnia = item.getString("data_tygodnia"),
                dzien = item.getString("dzien"),
                zupa = item.getString("zupa"),
                drugie_danie = item.getString("drugie_danie"),
                skladniki = item.getString("skladniki")
            )
            obiadList.add(obiad)
        }
        return obiadList
    }
}
