package com.example.pokedexapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pokedexapp.databinding.ActivityMainBinding

class PokemonList : AppCompatActivity() {

    private lateinit var viewModel: PokeListViewModel
    private lateinit var binding: ActivityMainBinding
    private lateinit var pokeListAdapter: PokeListAdapter

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[PokeListViewModel::class.java]

        initUI()
    }

    private fun initUI() {
        binding.pokelistRecyclerView.layoutManager = LinearLayoutManager(this)

        pokeListAdapter = PokeListAdapter {
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("id", it)
            startActivity(intent)
        }

        binding.pokelistRecyclerView.adapter = pokeListAdapter

        viewModel.getPokemonList()
        viewModel.pokemonList.observe(this, Observer { list ->
            pokeListAdapter.setData(list)
        })
    }

    private fun goBack(){
        onBackPressed()
    }
}