package com.itechcom.domain.mapper

import com.itechcom.data.model.DataFirebaseCallModel
import com.itechcom.domain.model.auth.FirebaseCallModel

fun DataFirebaseCallModel.mapToFirebaseCallModel() : FirebaseCallModel {
    return FirebaseCallModel(isSuccess, errorMessage)
}