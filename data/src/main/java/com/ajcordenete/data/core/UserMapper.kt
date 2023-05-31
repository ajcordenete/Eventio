package com.ajcordenete.data.core

import com.ajcordenete.domain.models.User
import com.ajcordenete.persistence.features.user.models.UserDB

fun User.asEntity(): UserDB {
    this.apply {
        return UserDB(
            uid = uid,
            fullName = fullName,
            email = email
        )
    }
}

fun UserDB.asDomain(): User {
    this.apply {
        return User(
            uid = uid,
            fullName = fullName.orEmpty(),
            email = email.orEmpty()
        )
    }
}