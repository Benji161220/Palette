package com.example.palette

import android.app.Activity
import android.app.ActivityOptions
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView

class CardsAdapter(var items: ArrayList<Tarjeta>) : RecyclerView.Adapter<CardsAdapter.TarjViewHolder>() {

    class TarjViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var imagen: ImageView

        init {
            imagen = itemView.findViewById(R.id.image1)
        }

        fun bindTarjeta(t: Tarjeta) {
            imagen.setImageResource(t.imagen)
        }

    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): TarjViewHolder {
        val itemView = LayoutInflater.from(viewGroup.context).inflate(R.layout.item_cards, viewGroup, false)
        return TarjViewHolder(itemView)
    }

    override fun onBindViewHolder(viewHolder: TarjViewHolder, pos: Int) {
        val item = items[pos]
        viewHolder.bindTarjeta(item)

        viewHolder.itemView.setOnClickListener { 
            val intent = Intent(viewHolder.itemView.context, ImagePalette::class.java)
            intent.putExtra("image_resource", item.imagen)
            val options = ActivityOptions.makeSceneTransitionAnimation(viewHolder.itemView.context as Activity, viewHolder.imagen, "image_transition")
            viewHolder.itemView.context.startActivity(intent, options.toBundle())
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }
}