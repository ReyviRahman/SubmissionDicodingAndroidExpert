package com.example.submissionexpert.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class GameResponse(
	@field:SerializedName("results")
	val results: List<ResultsItem>
)

data class ResultsItem(
	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("background_image")
	val backgroundImage: String,

	@field:SerializedName("released")
	val released: String

)

