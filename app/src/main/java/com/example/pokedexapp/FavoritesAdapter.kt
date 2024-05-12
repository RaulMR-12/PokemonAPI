package com.example.pokedexapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.pokedexapp.databinding.PokeListBinding

class FavoritesAdapter(private val pokemonList: List<Pokemon>) :
    RecyclerView.Adapter<FavoritesAdapter.FavoritesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoritesViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = PokeListBinding.inflate(inflater, parent, false)
        return FavoritesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FavoritesViewHolder, position: Int) {
        val pokemon = pokemonList[position]
        holder.bind(pokemon)
    }

    override fun getItemCount(): Int = pokemonList.size

    inner class FavoritesViewHolder(private val binding: PokeListBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(pokemon: Pokemon) {
            binding.pokemonText.text = "${pokemon.id} - ${pokemon.name}"
            Glide.with(binding.root)
                .load(pokemon.sprites.frontDefault)
                .into(binding.pokemonImage)

            binding.root.setOnClickListener {
            }
        }
    }
}

