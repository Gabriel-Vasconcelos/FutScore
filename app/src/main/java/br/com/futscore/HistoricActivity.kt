package br.com.futscore

import adapters.CustomAdapter
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.futscore.databinding.ActivityHistoricBinding
import data.Scoreboard
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class HistoricActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHistoricBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHistoricBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initRecyclerView()

        binding.buttonBack.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

    private fun initRecyclerView() {
//        val scoreboard = Scoreboard(
//            match_name = "Partida Teste",
//            team_name = "Nome do Time A vs Nome do Time B",
//            game_result = "0 x 0",
//            game_date = "06/11/2023"
//        )
//
//        val data2 = ArrayList<Scoreboard>()
//        data2.add(scoreboard)
//        data2.add(scoreboard)

        val data = readScoreboardDataSharedPreferences()

        binding.rcHistoric.layoutManager = LinearLayoutManager(this)
        binding.rcHistoric.setHasFixedSize(true)
        binding.rcHistoric.adapter = CustomAdapter(data, this)
    }

    fun readScoreboardDataSharedPreferences(): ArrayList<Scoreboard> {
        val data = ArrayList<Scoreboard>()
        val sp: SharedPreferences = getSharedPreferences("historic", Context.MODE_PRIVATE)

        if (sp != null) {
            val historic = sp.getStringSet("historic", HashSet<String>()) ?: HashSet<String>()
            val gson = Gson()

            for (json in historic) {
                val scoreboard = gson.fromJson(json, Scoreboard::class.java)
                data.add(scoreboard)
            }
        }
        return data
    }
}