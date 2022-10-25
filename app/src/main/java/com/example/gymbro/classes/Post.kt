package com.example.gymbro.classes

import android.graphics.drawable.Drawable

data class Post(
    var profilePhoto: String?,
    var username: String?,
    var location: String?,
    var postPhoto: Int,
    var numberOfLikes: String?,
    var description: String?,
    var numberOfComments: String?,
    var photoBackground: Int
) {

}