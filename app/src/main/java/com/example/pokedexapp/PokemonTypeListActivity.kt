package com.example.pokedexapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pokedexapp.databinding.ActivityPokemonTypeListBinding
import java.util.ArrayList

class PokemonTypeListActivity : AppCompatActivity() {

    private lateinit var viewModel: TypeListViewModel
    private lateinit var binding: ActivityPokemonTypeListBinding
    private lateinit var adapter: TypeListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPokemonTypeListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(TypeListViewModel::class.java)

        binding.pokelistRecyclerView.layoutManager = LinearLayoutManager(this)
        adapter = TypeListAdapter { type -> onTypeClicked(type) }
        binding.pokelistRecyclerView.adapter = adapter

        val type = intent.getStringExtra("type") ?: "normal"
        val pokemonList = intent.getSerializableExtra("pokemonList") as? ArrayList<Pokemon> ?: arrayListOf()
        val pokemonNames = pokemonList.map { it.name }
        adapter.setData(pokemonNames)

        viewModel.getPokemonListByType(type)

        viewModel.pokemonList.observe(this, Observer { typeList ->
            adapter.setData(typeList)
        })
    }

    private fun onTypeClicked(typeName: String){
        viewModel.getPokemonListByType(typeName)
    }

    fun goBack(view: View){
        onBackPressed()
    }


}