package adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import data.Scoreboard
import br.com.futscore.R

class CustomAdapter(private val mList: List<Scoreboard>) : RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    // Criação de novos ViewHolders
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // Infla o card_historic_item
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_historic_item, parent, false)

        return ViewHolder(view)
    }

    // Ligando o Recycler view a um View Holder
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {}

    // Faz o bind de uma ViewHolder a um Objeto da Lista
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {}

    // Retorna o número de itens na lista
    override fun getItemCount(): Int {
        return mList.size
    }
}