package com.ajcordenete.data.feature.user

import com.ajcordenete.data.core.asDomain
import com.ajcordenete.data.core.asEntity
import com.ajcordenete.domain.get
import com.ajcordenete.domain.models.User
import com.ajcordenete.persistence.features.user.UserLocalSource
import java.lang.Exception
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userLocalSource: UserLocalSource
): UserRepository {

}