package com.example.kletka_jizn_or_smert_in_mucheniah

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView

class CellAdapter(private val context: Context, private val cells: List<MainActivity.CellState>) : BaseAdapter() {

    override fun getCount(): Int {
        return cells.size
    }

    override fun getItem(position: Int): MainActivity.CellState {
        return cells[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view: View = convertView ?: LayoutInflater.from(context).inflate(R.layout.cell_item, parent, false)

        val cellIcon = view.findViewById<ImageView>(R.id.cellIcon)
        val cellTitle = view.findViewById<TextView>(R.id.cellTitle)
        val cellSubtitle = view.findViewById<TextView>(R.id.cellSubtitle)

        val cell = getItem(position)

        when (cell) {
            MainActivity.CellState.ALIVE -> {
                cellIcon.setImageResource(R.drawable.ic_live_cell)
                cellTitle.text = "Живая клетка"
                cellSubtitle.text = "и шевелится!"
            }
            MainActivity.CellState.DEAD -> {
                cellIcon.setImageResource(R.drawable.ic_dead_cell)
                cellTitle.text = "Мёртвая клетка"
                cellSubtitle.text = "или прикидывается"
            }
            MainActivity.CellState.LIFE -> {
                cellIcon.setImageResource(R.drawable.ic_life_cell)
                cellTitle.text = "Жизнь"
                cellSubtitle.text = "Ку-ку!"
            }
        }

        return view
    }
}