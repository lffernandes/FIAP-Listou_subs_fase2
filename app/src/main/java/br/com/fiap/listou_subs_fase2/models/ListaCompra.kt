package br.com.fiap.listou_subs_fase2.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable


class ListaCompra : Serializable{
    constructor(
        id: Int?,
        descricao: String?,
        dtCompra: String?,
        recorrente: Boolean?,
        recorrencia: String?,
        orcamento: String?,
        itens: List<ItemCompra>?
    ) {
        this.id = id
        this.descricao = descricao
        this.dtCompra = dtCompra
        this.recorrente = recorrente
        this.recorrencia = recorrencia
        this.orcamento = orcamento
        this.itens = itens
    }

    @SerializedName("id")
    @Expose
    var id: Int? = null

    @SerializedName("descricao")
    @Expose
    var descricao: String? = null

    @SerializedName("dtCompra")
    @Expose
    var dtCompra: String? = null

    @SerializedName("recorrente")
    @Expose
    var recorrente: Boolean? = null

    @SerializedName("recorrencia")
    @Expose
    var recorrencia: String? = null

    @SerializedName("orcamento")
    @Expose
    var orcamento: String? = null

    @SerializedName("itens")
    @Expose
    var itens: List<ItemCompra>? = null

}