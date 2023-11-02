package br.com.futscore

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private lateinit var buttonFechar: Button
    private lateinit var textResultado: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonFechar = findViewById(R.id.button_fechar)
        textResultado = findViewById(R.id.textResultado)

        val bundle = intent.extras//todos os parâmetros

        if ( bundle != null ){
            val gameNameReceived = intent.getStringExtra("game")
            val teamOneReceived = intent.getStringExtra("teamOne")
            val teamTwoReceived = intent.getStringExtra("teamTwo")
            val intervalReceived = intent.getIntExtra("interval", 0)

            val resultado = "Dados: $gameNameReceived, $teamOneReceived, $teamTwoReceived, $intervalReceived"

            textResultado.text = resultado
        }

        buttonFechar.setOnClickListener {
            val intent = Intent(
                this,
                ConfigActivity::class.java
            )

            startActivity(intent)
        }

    }
}