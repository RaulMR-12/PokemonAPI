package com.example.pokedexapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pokedexapp.databinding.ActivityCapturedListBinding

class FavoritesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCapturedListBinding
    private lateinit var favoritesAdapter: FavoritesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCapturedListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initUI()
    }

    private fun initUI() {
        val favoritesList: List<Pokemon> = getFavoritesList()

        favoritesAdapter = FavoritesAdapter(favoritesList)

        binding.pokelistRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@FavoritesActivity)
            adapter = favoritesAdapter
        }
    }

    private fun getFavoritesList(): List<Pokemon> {
        return listOf()
    }
}

