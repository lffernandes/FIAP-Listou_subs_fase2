package br.com.fiap.listou_subs_fase2

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_new_item.*

class NewItemActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_item)

        btnSalvarItem.setOnClickListener {
            when {
                etItemName.text.toString().trim().isEmpty() -> {
                    etItemName.error = "Informe o nome do Item"
                    etItemName.requestFocus()
                }
                etQtd.text.toString().trim().isEmpty() -> {
                    etQtd.error = "Informe a quantidade"
                    etQtd.requestFocus()
                }
                else -> {
                    val alertDialog = AlertDialog.Builder(this@NewItemActivity)
                    alertDialog.setMessage("Item adicionado  com sucesso!")
                    alertDialog.setPositiveButton("OK") { dialog, which ->
                        val intent = Intent(this@NewItemActivity, ListaActivity::class.java)
                        startActivity(intent)
                        finish()
                    }
                    alertDialog.show()
                }
            }
        }
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        TODO("Not yet implemented")
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        TODO("Not yet implemented")
    }
}