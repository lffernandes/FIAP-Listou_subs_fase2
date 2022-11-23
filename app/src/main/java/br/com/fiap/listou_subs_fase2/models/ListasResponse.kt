package br.com.fiap.listou_subs_fase2.models

import com.google.gson.annotations.SerializedName


class ListasResponse{
    @SerializedName("results")
    var listas: List<ListaCompra>? = null
}
