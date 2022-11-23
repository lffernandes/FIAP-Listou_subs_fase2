package br.com.fiap.listou_subs_fase2

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import br.com.fiap.listou_subs_fase2.Services.RetrofitService
import br.com.fiap.listou_subs_fase2.models.ListaCompra
import kotlinx.android.synthetic.main.activity_new_edit_lista.*
import retrofit2.Response
import android.widget.CompoundButton
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_new_item.*


class NewEditListaActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    private lateinit var retService: RetrofitService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_edit_lista)

        /* retService = ApiFactory.buildService(RetrofitService::class.java)
        uploadLista()*/
        val spinner: Spinner = findViewById(R.id.periodo_spinner)

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter.createFromResource(
            this,
            R.array.periodo_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
        }
        switchRepetir.isChecked = true
        switchRepetir.setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener { buttonView, isChecked ->
            periodo_spinner.isEnabled = switchRepetir.isChecked
        })

        btnSalvarLista.setOnClickListener {
            when {
                etListName.text.toString().trim().isEmpty() -> {
                    etListName.error = "Informe o nome da Lista"
                    etListName.requestFocus()
                }
                etDtCompra.text.toString().trim().isEmpty() -> {
                    etDtCompra.error = "Informe a data da compra"
                    etDtCompra.requestFocus()
                }
                etOrcamento.text.toString().trim().isEmpty() -> {
                    etOrcamento.error = "Informe o valor pretendido da compra"
                    etOrcamento.requestFocus()
                }
                else -> {
                    if (periodo_spinner.selectedItem.toString().trim().isEmpty() && switchRepetir.isChecked) {
                        etDtCompra.error = "Selecione a frequência da compra"
                        etDtCompra.requestFocus()
                    }else{
                        val alertDialog = AlertDialog.Builder(this@NewEditListaActivity)
                        alertDialog.setMessage("Lista adicionada com sucesso!")
                        alertDialog.setPositiveButton("OK") { dialog, which ->
                            val intent = Intent(this@NewEditListaActivity, MinhasListas::class.java)
                            startActivity(intent)
                            finish()
                        }
                        alertDialog.show()
                    }
                }

            }
        }

    }


    private fun uploadLista() {

        var listaData = intent
        var listaDescricao = listaData.getStringExtra("descricao")
        var listaDtCompra = listaData.getStringExtra("dtCompra")
        var listaIsRecorrente = listaData.getBooleanExtra("isRecorrente", false)
        var listaRecorrencia = listaData.getStringExtra("recorrencia")
        var listaOrcamento = listaData.getStringExtra("orcamento")


        val lista = ListaCompra(
            0,
            listaDescricao,
            listaDtCompra,
            listaIsRecorrente,
            listaRecorrencia,
            listaOrcamento,
            null
        )

        /* postResponse.observe(this, Observer {
             val receivedListaItem = it.body()
             val result = " " + "Descricao  : ${receivedListaItem?.descricao}" + "\n" +
                     " " + "Data da Compra: ${receivedListaItem?.dtCompra}" + "\n" +
                     " " + "Recorrente: ${receivedListaItem?.isRecorrente}" + "\n" +
                     " " + "Recorrencia: ${receivedListaItem?.recorrencia}" + "\n" +
                     " " + "Orcamento: ${receivedListaItem?.orcamento}" + "\n\n\n"
         })*/
    }

    //funções para trabalhar com o dropdown(spinner)
    override fun onItemSelected(parent: AdapterView<*>, view: View?, pos: Int, id: Long) {
        val spinner: Spinner = findViewById(R.id.periodo_spinner)
        spinner.onItemSelectedListener = this
        // An item was selected. You can retrieve the selected item using
        // parent.getItemAtPosition(pos)
    }

    override fun onNothingSelected(parent: AdapterView<*>) {
        // Another interface callback
    }
}