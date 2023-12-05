package com.project.kotlinappapi.models

data class Book(
    var id: String,
    var name: String,
    var description: String,
    var date_publish: String,
    var user: User
)
