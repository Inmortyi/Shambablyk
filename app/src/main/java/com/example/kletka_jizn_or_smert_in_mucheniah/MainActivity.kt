package com.example.kletka_jizn_or_smert_in_mucheniah

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    // Используем enum для представления состояния клеток
    enum class CellState { ALIVE, DEAD, LIFE }

    private val cells = mutableListOf<CellState>()
    private lateinit var cellAdapter: CellAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val addButton = findViewById<Button>(R.id.addButton)
        val cellListView = findViewById<ListView>(R.id.cellListView)

        cellAdapter = CellAdapter(this, cells)
        cellListView.adapter = cellAdapter

        addButton.setOnClickListener {
            addCell()
            cellAdapter.notifyDataSetChanged()
        }
    }

    private fun addCell() {
        val newCell = if (Random.nextBoolean()) CellState.ALIVE else CellState.DEAD
        cells.add(newCell)

        if (cells.size >= 3) {
            val lastThree = cells.takeLast(3)
            when {
                lastThree.all { it == CellState.ALIVE } -> {
                    // Если три живые клетки подряд, добавляем "жизнь"
                    cells.add(CellState.LIFE)
                    Toast.makeText(this, "Зародилась жизнь!", Toast.LENGTH_SHORT).show()
                }
                lastThree.all { it == CellState.DEAD } -> {
                    // Если три мертвые клетки подряд, удаляем последнюю "жизнь"
                    val lastLifeIndex = cells.lastIndexOf(CellState.LIFE)
                    if (lastLifeIndex != -1) {
                        cells.removeAt(lastLifeIndex)
                        Toast.makeText(this, "Жизнь умирает...", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }
}