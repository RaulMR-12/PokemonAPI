package com.example.pokedexapp

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import org.w3c.dom.TypeInfo

data class Pokemon (
    @Expose @SerializedName("id") val id: Int,
    @Expose @SerializedName("name") val name: String,
    @Expose @SerializedName("height") val height: Int,
    @Expose @SerializedName("weight") val weight: Int,
    @Expose @SerializedName("types") val types: List<Type>,
    @Expose @SerializedName("is_default") val isDefault: Boolean,
    @Expose @SerializedName("order") val order: Int,
    @Expose @SerializedName("forms") val forms: List<Form>,
    @Expose @SerializedName("abilities") val abilities: List<Ability>,
    @Expose @SerializedName("species") val species: Species,
    @Expose @SerializedName("sprites") val sprites: Sprites,
    @Expose @SerializedName("stats") val stats: List<Stat>,
    @Expose @SerializedName("flavor_text_entries") val flavorTextEntries: List<FlavorTextEntry>,

    var spanishFlavorTextEntries: List<String> = emptyList()
)

data class FlavorTextEntry (
    @Expose @SerializedName("flavor_text") val flavorText: String,
    @Expose @SerializedName("language") val language: Language
)

data class Language(
    @Expose @SerializedName("name") val name: String,
    @Expose @SerializedName("url") val url: String
)

data class Sprites(
    @Expose @SerializedName("back_default") val backDefault: String?,
    @Expose @SerializedName("back_female") val backFemale: String?,
    @Expose @SerializedName("back_shiny") val backShiny: String?,
    @Expose @SerializedName("back_shiny_female") val backShinyFemale: String?,
    @Expose @SerializedName("front_default") val frontDefault: String?,
    @Expose @SerializedName("front_female") val frontFemale: String?,
    @Expose @SerializedName("front_shiny") val frontShiny: String?,
    @Expose @SerializedName("front_shiny_female") val frontShinyFemale: String?,
    @Expose @SerializedName("other") val other: OtherSprites?
)

data class OtherSprites(
    @Expose @SerializedName("official-artwork") val officialArtwork: OfficialArtWorkSprites?
)

data class OfficialArtWorkSprites(
    @Expose @SerializedName("front_default") val frontDefault: String?,
    @Expose @SerializedName("front_shiny") val frontShiny: String?
)

data class Type(
    @Expose @SerializedName("type") val type: TypeInformation,
    @Expose @SerializedName("slot") val slot: Int
)

data class TypeInformation(
    @Expose @SerializedName("name") val name: String,
    @Expose @SerializedName("url") val url: String
)

data class Form(
    @Expose @SerializedName("name") val name: String,
    @Expose @SerializedName("url") val url: String
)

data class Ability(
    @Expose @SerializedName("ability") val ability: AbilityInfo,
    @Expose @SerializedName("is_hidden") val isHidden: Boolean,
    @Expose @SerializedName("slot") val slot: Int
)

data class AbilityInfo(
    @Expose @SerializedName("name") val name: String,
    @Expose @SerializedName("url") val url: String
)

data class Species(
    @Expose @SerializedName("name") val name: String,
    @Expose @SerializedName("url") val url: String
)

data class Stat(
    @Expose @SerializedName("base_stat") val baseStat: Int,
    @Expose @SerializedName("effort") val effort: Int,
    @Expose @SerializedName("stat") val stat: StatInfo
)

data class StatInfo(
    @Expose @SerializedName("name") val name: String,
    @Expose @SerializedName("url") val url: String
)

data class PokeResult (
    @Expose @SerializedName("name") val name: String,
    @Expose @SerializedName("url") val url: String
)

data class TypeResult(
    @Expose @SerializedName("name") val name: String,
    @Expose @SerializedName("pokemon") val pokemonList: List<Pokemon>
)
