package com.pixelart.githubapiusers.data.dto


import com.squareup.moshi.Json

data class SingleUser(
    val login: String,
    val id: Int,
    @field:Json(name = "node_id")
    val nodeId: String,
    @field:Json(name = "avatar_url")
    val avatarUrl: String,
    @field:Json(name = "gravatar_id")
    val gravatarId: String,
    val url: String,
    @field:Json(name = "html_url")
    val htmlUrl: String,
    @field:Json(name = "followers_url")
    val followersUrl: String,
    @field:Json(name = "following_url")
    val followingUrl: String,
    @field:Json(name = "gists_url")
    val gistsUrl: String,
    @field:Json(name = "starred_url")
    val starredUrl: String,
    @field:Json(name = "subscriptions_url")
    val subscriptionsUrl: String,
    @field:Json(name = "organizations_url")
    val organizationsUrl: String,
    @field:Json(name = "repos_url")
    val reposUrl: String,
    @field:Json(name = "events_url")
    val eventsUrl: String,
    @field:Json(name = "received_events_url")
    val receivedEventsUrl: String,
    val type: String,
    @field:Json(name = "site_admin")
    val siteAdmin: Boolean,
    val name: String,
    val company: Any?,
    val blog: String,
    val location: String,
    val email: Any?,
    val hireable: Any?,
    val bio: Any?,
    @field:Json(name = "public_repos")
    val publicRepos: Int,
    @field:Json(name = "public_gists")
    val publicGists: Int,
    val followers: Int,
    val following: Int,
    @field:Json(name = "created_at")
    val createdAt: String,
    @field:Json(name = "updated_at")
    val updatedAt: String
)