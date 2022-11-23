package br.com.fiap.listou_subs_fase2.Services

import br.com.fiap.listou_subs_fase2.models.ListaCompra
import br.com.fiap.listou_subs_fase2.models.ListaResponse
import br.com.fiap.listou_subs_fase2.models.UserListou
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.*

public interface RetrofitService {

    @GET("/listas/{user}}")
     suspend fun getListas(@Path ("user") user: String) : Call<ArrayList<ListaCompra>>

    @GET("lista/{listaId}")
    suspend fun getListaById(
        @Path("listaId") ListaId : Int
    ): Response<ListaResponse>

    @POST("user/lista")
    suspend fun uploadLista (@Body lista:ListaCompra) : Response<ListaCompra>

    @POST("login")
    fun login (@Body userListou: UserListou) : Call<Boolean>

}
