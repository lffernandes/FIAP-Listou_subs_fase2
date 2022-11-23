package br.com.fiap.listou_subs_fase2.repository

import android.content.Intent
import android.util.Log
import android.widget.Toast
import br.com.fiap.listou_subs_fase2.HomeActivity
import br.com.fiap.listou_subs_fase2.Services.ApiFactory
import br.com.fiap.listou_subs_fase2.Services.RetrofitService
import br.com.fiap.listou_subs_fase2.models.UserListou
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserRepository {

    val request = ApiFactory.buildService(RetrofitService::class.java)

    fun login(username: String, password: String): Boolean? {

        val call = request?.login(UserListou(username, password))
        var resp : Response<Boolean>? =call?.execute()
        if (resp != null) {
            if (resp.isSuccessful()) {
                return resp.body()
            }
        }
        Log.e("UserRepository", "onFailure: falha ao efetuar o login")
        return  false

       /* call?.enqueue(object : Callback<Boolean> {

            override fun onResponse(
                call: Call<Boolean?>,
                response: Response<Boolean?>
            ) {
                retorno = response.body() == true
            }

            override fun onFailure(call: Call<Boolean?>, t: Throwable) {
                Log.e("UserRepository", "onFailure: falha ao efetuar o login", t)
            }

        })*/
    }
}