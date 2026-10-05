package com.example.praktikum_pam_minggu4_yowen_daniel_124140120

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform