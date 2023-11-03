package br.com.futscore

import CountUpTimer
import Game
import ScoreManager
import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import java.util.Date

class MainActivity : AppCompatActivity() {
    var gameStarted: Boolean = false
    private var countUpTimer: CountUpTimer? = null
    var score = ScoreManager()
    var scoreTeam01: Int = score.getScore(1)
    var scoreTeam02: Int = score.getScore(2)


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

    @SuppressLint("MutatingSharedPrefs")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnStart = findViewById(R.id.btnStart)
        txtGame = findViewById(R.id.txtGame)
        txtTeam01 = findViewById(R.id.txtTeam01)
        txtTeam02 = findViewById(R.id.txtTeam02)
        txtScore01 = findViewById(R.id.txtScore01)
        txtScore02 = findViewById(R.id.txtScore02)
        txtTimer = findViewById(R.id.txtTimer)
        btnSettings = findViewById(R.id.btnSettings)
        btnHistory = findViewById(R.id.btnHistory)
        btnUndo = findViewById(R.id.btnUndo)


        btnStart.setOnClickListener(View.OnClickListener {
            gameStarted = true
            btnStart.isClickable = false
            countUpTimer = CountUpTimer { elapsedTime ->
                if (elapsedTime >= 5) {
                    saveGame();
                    countUpTimer?.stop()
                    txtTimer.text = "Tempo esgotado"
                    gameStarted = false
                    btnStart.isClickable = true
                } else {
                    txtTimer.text = formatTime(elapsedTime)
                }
            }
            countUpTimer?.start()
        })

        txtScore01.setOnClickListener(View.OnClickListener {
            if(gameStarted){
                score.updateScore(1, scoreTeam01 + 1)
                scoreTeam01 = score.getScore(1)
                updateScore()

                score.updateScore(2, scoreTeam02)
                scoreTeam02 = score.getScore(2)
                updateScore()
            }
        })

        txtScore02.setOnClickListener(View.OnClickListener {
            if(gameStarted){
                score.updateScore(1, scoreTeam01)
                scoreTeam01 = score.getScore(1)
                updateScore()

                score.updateScore(2, scoreTeam02 + 1)
                scoreTeam02 = score.getScore(2)
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

        btnUndo.setOnClickListener(View.OnClickListener {
            score.undo(1)
            scoreTeam01 = score.getScore(1)
            updateScore()

            score.undo(2)
            scoreTeam02 = score.getScore(2)
            updateScore()
        })

    }

    @SuppressLint("MutatingSharedPrefs")
    private fun saveGame(){
        val game = Game(
            txtGame.text.toString(),
            txtTeam01.text.toString(),
            txtTeam02.text.toString(),
            txtScore01.text.toString().toInt(),
            txtScore02.text.toString().toInt(),
            txtTimer.text.toString(),
            Date()
        )
        val sharedPreferences = getSharedPreferences("historic", MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        val historic = sharedPreferences.getStringSet("historic", HashSet<String>()) ?: HashSet<String>()

        historic.add(game.toString())

        editor.putStringSet("historic", historic)
        editor.apply()
    }

    private fun updateScore() {
        txtScore01.text = String.format("%02d", scoreTeam01)
        txtScore02.text = String.format("%02d", scoreTeam02)
    }

    private fun formatTime(seconds: Long): String {
        val minutes = seconds / 60
        val remainingSeconds = seconds % 60
        return String.format("%02d:%02d", minutes, remainingSeconds)
    }
}