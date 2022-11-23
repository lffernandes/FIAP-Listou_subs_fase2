package br.com.fiap.listou_subs_fase2.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.fiap.listou_subs_fase2.Adapter.ListaAdapter.MyViewHolder
import br.com.fiap.listou_subs_fase2.R
import br.com.fiap.listou_subs_fase2.models.ItemCompra

class ListaAdapter(private val myitens: ArrayList<ItemCompra>) :
    RecyclerView.Adapter<MyViewHolder>() {

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var id: TextView = itemView.findViewById(R.id.id)
        var nomeItem: TextView = itemView.findViewById(R.id.nomeItem)
        var qtdItem: TextView = itemView.findViewById(R.id.qtdItem)
       /* var precoItem: TextView = itemView.findViewById(R.id.precoItem)*/

        fun bind(myitens: ArrayList<ItemCompra>?, position: Int) {
            id.text = myitens!!.get(position).id.toString()
            nomeItem.text = myitens!!.get(position).nome
            qtdItem.text = myitens!!.get(position).quantidade.toString()
           /* precoItem.text = myitens!!.get(position).precoPretendidoUnitario*/
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent!!.context)
            .inflate(R.layout.item_compra, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        return holder.bind(myitens, position)
    }


    override fun getItemCount(): Int {
        return myitens.size
    }
}