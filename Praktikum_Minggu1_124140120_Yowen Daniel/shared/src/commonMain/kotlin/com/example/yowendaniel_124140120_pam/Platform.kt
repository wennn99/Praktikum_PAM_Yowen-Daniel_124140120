package com.example.yowendaniel_124140120_pam

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform