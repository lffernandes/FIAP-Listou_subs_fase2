package br.com.fiap.listou_subs_fase2

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.fiap.listou_subs_fase2.Adapter.ListasAdapter
import br.com.fiap.listou_subs_fase2.Services.ApiFactory
import br.com.fiap.listou_subs_fase2.Services.RetrofitService
import br.com.fiap.listou_subs_fase2.models.ItemCompra
import br.com.fiap.listou_subs_fase2.models.ListaCompra
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_minhas_listas.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MinhasListas() : AppCompatActivity(), ListasAdapter.ListaClickListener {

    var listasData: ArrayList<ListaCompra> = ArrayList()

    override fun onItemClick(item: ListaCompra, position: Int) {
        val alertDialog = AlertDialog.Builder(this@MinhasListas)

        val json = Gson().toJson(item.itens)

        val intent = Intent(this@MinhasListas, ListaActivity::class.java)
            intent.putExtra("id",  item.id)
            intent.putExtra("descricao",item.descricao)
            intent.putExtra("dtCompra", item.dtCompra)
            intent.putExtra("recorrente", item.recorrente)
            intent.putExtra("recorrencia",  item.recorrencia)
            intent.putExtra("orcamento", item.orcamento)
            intent.putExtra("itens",json)
            startActivity(intent)
    }


    private var myService: RetrofitService? = null
    private var myAdapter: ListasAdapter? = null
    private var myListas: MutableList<ListaCompra> = ArrayList()
    private var myItens: MutableList<ItemCompra> = ArrayList()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_minhas_listas)

        var recyclerView: RecyclerView = findViewById(R.id.rv_compra_list)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.addItemDecoration(DividerItemDecoration(this, 0))
        recyclerView.adapter = ListasAdapter(listMinhasListas(), this@MinhasListas)

        val actionBar = supportActionBar
        actionBar!!.title = "Home"
        actionBar.setDisplayHomeAsUpEnabled(true)

        fab.setOnClickListener {
            val intent = Intent(this, NewEditListaActivity::class.java)
            startActivity(intent);
        }
    }


    suspend fun getListas(user: String): LiveData<ArrayList<ListaCompra>> {

        lateinit var liveData: MutableLiveData<ArrayList<ListaCompra>>
        var call = ApiFactory.buildService(RetrofitService::class.java)
        call?.getListas(user)?.enqueue(object : Callback<ArrayList<ListaCompra>> {
            override fun onResponse(
                call: Call<ArrayList<ListaCompra>>,
                response: Response<ArrayList<ListaCompra>>
            ) {
                liveData.value = response.body()
            }

            override fun onFailure(call: Call<ArrayList<ListaCompra>>, t: Throwable) {
            }
        })
        return liveData

    }

    fun listMinhasListas(): ArrayList<ListaCompra> {

        myListas.add(ListaCompra(1, "Lista 1", "23/10/2021", true, "semmanal", "R$200",meusItens()))
        myListas.add(ListaCompra(2, "Lista 2", "30/10/2021", true, "semmanal", "R$400", meusItens()))
        myListas.add(ListaCompra(3, "Lista 3", "6/11/2021", false, "eventual", "R$600", meusItens()))
        myListas.add(ListaCompra(4, "Lista 4", "13/11/2021", true, "semmanal", "R$800", meusItens()))

        return myListas as ArrayList<ListaCompra>
    }

    fun meusItens(): ArrayList<ItemCompra> {
        myItens.add(ItemCompra(1, "Leite", 5, "5".toFloat()))
        myItens.add(ItemCompra(2, "Sabão em pó", 1, "7".toFloat()))
        myItens.add(ItemCompra(3, "Cerveja", 12, "4".toFloat()))
        myItens.add(ItemCompra(4, "Frango 1kg", 1, "20".toFloat()))
        return myItens as ArrayList<ItemCompra>
    }
}