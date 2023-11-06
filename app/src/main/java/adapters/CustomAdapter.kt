package adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import data.Scoreboard
import br.com.futscore.R
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class CustomAdapter(private val myList: List<Scoreboard>, private val context: Context) : RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    // Criação de novos ViewHolders
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // Infla o card_historic_item
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_historic_item, parent, false)

        return ViewHolder(view)
    }

    // Faz o bind de uma ViewHolder a um Objeto da Lista
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val scoreboard = myList[position];

        val gameScoreResult: String = "${scoreboard.scoreTeamOne} x ${scoreboard.scoreTeamTwo}"
        val teamsName: String = "${scoreboard.teamOne} vs ${scoreboard.teamTwo}"
        val gameDate: String = formatDate(scoreboard.gameDate)

        holder.textMatchName.text = scoreboard.matchName
        holder.textGameDate.text = gameDate
        holder.textGameResult.text = gameScoreResult
        holder.textTeamName.text = teamsName

        holder.itemView.setOnClickListener {
            val message: String =
                "${scoreboard.matchName}\n ${gameScoreResult}\n ${teamsName}\n $gameDate"

            val sendIntent: Intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, message)
                type = "text/plain"
            }
            val shareIntent = Intent.createChooser(sendIntent, null)
            context.startActivity(shareIntent)
        }

    }

    // Retorna o número de itens na lista
    override fun getItemCount(): Int = myList.size


    // Ligando o Recycler view a um View Holder
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val textMatchName: TextView = itemView.findViewById(R.id.textMatchName)
        val textTeamName: TextView = itemView.findViewById(R.id.textTeamName)
        val textGameResult: TextView = itemView.findViewById(R.id.textGameResult)
        val textGameDate: TextView = itemView.findViewById(R.id.textGameDate)
    }

    // Função para formatar a data do jogo
    private fun formatDate(data: Date): String {
        val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        return sdf.format(data)
    }
}