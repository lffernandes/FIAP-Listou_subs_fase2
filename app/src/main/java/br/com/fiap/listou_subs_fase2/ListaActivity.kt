package br.com.fiap.listou_subs_fase2

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.fiap.listou_subs_fase2.Adapter.ListaAdapter
import br.com.fiap.listou_subs_fase2.models.ItemCompra
import com.google.android.material.switchmaterial.SwitchMaterial
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_lista.*
import kotlinx.android.synthetic.main.activity_minhas_listas.*
import kotlinx.android.synthetic.main.activity_minhas_listas.fab

class ListaActivity() : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView( R.layout.activity_lista)

       /* val mlista: ItemCompra = intent.getSerializableExtra("itens") as ItemCompra*/
        val json = intent.getStringExtra("itens");
        val gson = Gson()
        val itemType = object : TypeToken<ArrayList<ItemCompra>>() {}.type
        val myItens = gson.fromJson<ArrayList<ItemCompra>>(json, itemType)


        var id = findViewById<TextView>(R.id.id)
        id.text = intent.getStringExtra("id")

        var descricao = findViewById<TextView>(R.id.descricao)
        descricao.text = intent.getStringExtra("descricao")

        var dtCompra = findViewById<TextView>(R.id.dtCompra)
        dtCompra.text = intent.getStringExtra("dtCompra")

        var recorrente = findViewById<SwitchMaterial>(R.id.switchRecorrente)
        recorrente.isClickable = false
        recorrente.isChecked = intent.getBooleanExtra("recorrente",false)

        var recorrencia = findViewById<TextView>(R.id.recorrencia)
        recorrencia.text = intent.getStringExtra("recorrencia")

        var orcamento = findViewById<TextView>(R.id.orcamento)
        orcamento.text = intent.getStringExtra("orcamento")

        var recyclerView: RecyclerView = findViewById(R.id.rv_item_list)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.addItemDecoration(DividerItemDecoration(this, 0))
        recyclerView.adapter = ListaAdapter(myItens)

        itembtn.setOnClickListener {
            val intent = Intent(this, NewItemActivity::class.java)
            startActivity(intent);
        }
    }
}