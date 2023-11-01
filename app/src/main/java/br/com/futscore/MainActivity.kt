package br.com.futscore

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    var gameStarted: Boolean = false
    var scoreTeam01: Int = 0
    var scoreTeam02: Int = 0


    private lateinit var txtGame: TextView
    private lateinit var txtTeam01: TextView
    private lateinit var txtTeam02: TextView
    private lateinit var txtScore01: TextView
    private lateinit var txtScore02: TextView
    private lateinit var txtTimer: TextView
    private lateinit var btnSettings: ImageButton
    private lateinit var btnHistory: ImageButton
    private lateinit var btnUndo: ImageButton
    private lateinit var btnStart: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnStart = findViewById(R.id.btnStart)
        txtScore01 = findViewById(R.id.txtScore01)
        txtScore02 = findViewById(R.id.txtScore02)
        btnSettings = findViewById(R.id.btnSettings)
        btnHistory = findViewById(R.id.btnHistory)


        btnStart.setOnClickListener(View.OnClickListener {
            gameStarted = true
        })

        txtScore01.setOnClickListener(View.OnClickListener {
            if(gameStarted){
                scoreTeam01++
                updateScore()
            }
        })

        txtScore02.setOnClickListener(View.OnClickListener {
            if(gameStarted){
                scoreTeam02++
                updateScore()
            }
        })

        btnSettings.setOnClickListener {
            val intent = Intent(this, ConfigActivity::class.java)
            startActivity(intent)
        }

        btnHistory.setOnClickListener {
            val intent = Intent(this, HistoricActivity::class.java)
            startActivity(intent)
        }
    }
    private fun updateScore() {
        txtScore01.text = String.format("%02d", scoreTeam01)
        txtScore02.text = String.format("%02d", scoreTeam02)
    }
}