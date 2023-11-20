package com.kirik.repository.data.model


import com.google.gson.annotations.SerializedName

data class RepositoryResponse(
    @SerializedName("incomplete_results")
    val incompleteResults: Boolean?,
    @SerializedName("items")
    val items: List<Item>,
    @SerializedName("total_count")
    val totalCount: Int?
) {
    data class Item(

        @SerializedName("full_name")
        val fullName: String?,

        @SerializedName("id")
        val id: Int,

        @SerializedName("name")
        val name: String?,
        val description: String?,


        @SerializedName("watchers_count")
        val watchersCount: Int?,
        @SerializedName("stargazers_count")
        val stargazersCount: Int?,
        val owner: Owner,
        @SerializedName("language")
        val language: String?,
        @SerializedName("created_at")
        val createdAt: String?,
        @SerializedName("open_issues_count")

        val issues: Int?
    ) {
        data class Owner(
            @SerializedName("avatar_url")
            val avatarUrl: String?
        )

    }
}