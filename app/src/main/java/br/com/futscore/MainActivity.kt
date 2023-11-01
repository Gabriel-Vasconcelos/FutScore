package br.com.futscore

import CountUpTimer
import ScoreManager
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnStart = findViewById(R.id.btnStart)
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
                txtTimer.text = formatTime(elapsedTime)
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