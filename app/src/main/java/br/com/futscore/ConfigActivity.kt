package br.com.futscore

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.content.SharedPreferences
import com.google.android.material.textfield.TextInputEditText

class ConfigActivity : AppCompatActivity() {

    private lateinit var buttonBack: Button
    private lateinit var gameName: TextInputEditText
    private lateinit var teamOneName: TextInputEditText
    private lateinit var teamTwoName: TextInputEditText
    private lateinit var interval: TextInputEditText
    private lateinit var playButton: Button
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i("ciclo_vida", "onCreate")
        setContentView(R.layout.activity_config)

        buttonBack = findViewById(R.id.buttonBack)
        gameName = findViewById(R.id.gameName)
        teamOneName = findViewById(R.id.teamOneName)
        teamTwoName = findViewById(R.id.teamTwoName)
        interval = findViewById(R.id.interval)
        playButton = findViewById(R.id.playButton)

        // Inicialize o SharedPreferences
        sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE)

        // Restaure o texto salvo, se existir
        val textoSalvo = sharedPreferences.getString("textoSalvo", "")
        val saveNameGame = sharedPreferences.getString("saveNameGame", "")
        val saveTeamOneName = sharedPreferences.getString("saveTeamOneName", "")
        val saveTeamTwoName = sharedPreferences.getString("saveTeamTwoName", "")
        val saveInterval = sharedPreferences.getInt("saveInterval", 0)

        gameName.setText(saveNameGame)
        teamOneName.setText(saveTeamOneName)
        teamTwoName.setText(saveTeamTwoName)
        interval.setText(saveInterval.toString())

        playButton.setOnClickListener {
            val textGameName = gameName.text.toString()
            val textTeamOneName = teamOneName.text.toString()
            val textTeamTwoName = teamTwoName.text.toString()
            val textInterval = interval.text.toString().toInt()

            // Salvar o texto no SharedPreferences
            val editor = sharedPreferences.edit()
            editor.putString("saveNameGame", textGameName)
            editor.putString("saveTeamOneName", textTeamOneName)
            editor.putString("saveTeamTwoName", textTeamTwoName)
            editor.putInt("saveInterval", textInterval)
            editor.commit()

            val intent = Intent(
                this,
                MainActivity::class.java
            )

            //Passar parâmetros para nova tela
            intent.putExtra("game", textGameName)
            intent.putExtra("teamOne", textTeamOneName)
            intent.putExtra("teamTwo", textTeamTwoName)
            intent.putExtra("interval", textInterval)

            //Iniciar uma nova tela
            startActivity(intent)

        }

        buttonBack.setOnClickListener {
            val textGameName = gameName.text.toString()
            val textTeamOneName = teamOneName.text.toString()
            val textTeamTwoName = teamTwoName.text.toString()
            val textInterval = interval.text.toString().toInt()

            // Salvar o texto no SharedPreferences
            val editor = sharedPreferences.edit()
            editor.putString("saveNameGame", textGameName)
            editor.putString("saveTeamOneName", textTeamOneName)
            editor.putString("saveTeamTwoName", textTeamTwoName)
            editor.putInt("saveInterval", textInterval)
            editor.commit()

            val intent = Intent(
                this,
                MainActivity::class.java
            )
            //Passar parâmetros para nova tela
            intent.putExtra("game", textGameName)
            intent.putExtra("teamOne", textTeamOneName)
            intent.putExtra("teamTwo", textTeamTwoName)
            intent.putExtra("interval", textInterval)

            //Iniciar uma nova tela
            startActivity(intent)
        }

    }
}