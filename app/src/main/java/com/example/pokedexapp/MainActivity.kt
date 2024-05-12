package com.example.pokedexapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.pokedexapp.databinding.ActivityMainBinding
import com.example.pokedexapp.databinding.ActivityPokeInfoBinding

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: PokemonInfoViewModel
    private lateinit var binding: ActivityPokeInfoBinding
    private lateinit var mainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        binding = ActivityPokeInfoBinding.inflate(layoutInflater)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)

        viewModel = ViewModelProvider(this)[PokemonInfoViewModel::class.java]

        initUI()
    }

    private fun initUI(){
        val id = intent.extras?.getInt("id") ?: -1

        viewModel.getPokemonInfo(id)

        viewModel.pokemonInfo.observe(this, Observer { pokemon ->
            val typeNames = pokemon.types.map { it.type.name }
            binding.nameTextView.text = pokemon.name
            binding.heightText.text = "Altura: ${pokemon.height / 10.0}m"
            binding.weightText.text = "Peso: ${pokemon.weight / 10.0}"

            binding.typeText.text = "Tipo: ${typeNames.joinToString()}"

            binding.typeText.setOnClickListener{
                val pokemonList = viewModel.pokemonListByType.value ?: emptyList()
                val intent = Intent(this, PokemonTypeListActivity::class.java).apply {
                    putExtra("type", typeNames.firstOrNull())
                    putExtra("pokemonList", ArrayList(pokemonList))
                }

                startActivity(intent)
            }

            val frontDefault = pokemon.sprites.other?.officialArtwork?.frontDefault
            if (!frontDefault.isNullOrEmpty()){
                Glide.with(this).load(frontDefault).into(binding.imageView)
            }
            else{
                Glide.with(this).load(pokemon.sprites.frontDefault).into(binding.imageView)
            }
        })

        viewModel.getPokemonDescription(id)
        viewModel.pokemonDescription.observe(this) { pokemon ->

            val spanishEntries = pokemon.flavorTextEntries.filter { it.language.name == "es" }

            val spanishText = spanishEntries.firstOrNull()?.flavorText

            binding.descriptionText.text = spanishText ?: ""

            viewModel.pokemonInfo.value?.spanishFlavorTextEntries =
                spanishEntries.map { it.flavorText }
        }

        mainBinding.favoritesButton.setOnClickListener{
            showFavorites()
        }
    }

    private fun showFavorites(){
        val intent = Intent(this, FavoritesActivity::class.java)
        startActivity(intent)
    }
}