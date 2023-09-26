package com.itechcom.expensestracker.utils

enum class LoginType {
    BASIC,
    GOOGLE,
    FACEBOOK;
    fun getType() = this.name
}