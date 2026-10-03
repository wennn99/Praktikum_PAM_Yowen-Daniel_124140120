package com.example.praktikum3pam

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform