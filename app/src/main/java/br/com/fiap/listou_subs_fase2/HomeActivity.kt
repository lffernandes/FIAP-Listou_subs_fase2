package br.com.fiap.listou_subs_fase2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val saudacao = intent.getStringExtra("username")

        val ola = findViewById<TextView>(R.id.ola)
        ola.text = "Olá, ${saudacao?.uppercase()}."

        card_lista.setOnClickListener {
            val intent = Intent(this, MinhasListas::class.java)
            startActivity(intent);
        }

        card_sair.setOnClickListener {
            finish();
            System.exit(0);
        }
    }
}