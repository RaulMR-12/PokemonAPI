package com.example.pokedexapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pokedexapp.databinding.PokeListBinding
import com.squareup.picasso.Picasso

class TypeListAdapter(private val typeClick: (String) -> Unit) : RecyclerView.Adapter<TypeListAdapter.TypeViewHolder>() {
    private var typePokemonList: List<String> = emptyList()

    fun setData(list: List<String>){
        typePokemonList = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TypeViewHolder {
        val binding = PokeListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TypeViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return typePokemonList.size
    }

    override fun onBindViewHolder(holder: TypeViewHolder, position: Int) {
        val binding = holder.binding
        //val type = typePokemonList[position]
        val pokemonName = typePokemonList[position] // Aquí estamos obteniendo el nombre del Pokémon en esta posición

        binding.pokemonText.text = "#${position + 1} - $pokemonName"

        val imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${typePokemonList[position + 1]}.png"

        Picasso.get().load(imageUrl).into(binding.pokemonImage)

        holder.itemView.setOnClickListener { typeClick(pokemonName) }
    }

    class TypeViewHolder(val binding: PokeListBinding) : RecyclerView.ViewHolder(binding.root)
}