package com.example.praktikum_pam_minggu4_yowen_daniel_124140120

class Greeting {
    private val platform = getPlatform()

    fun greet(): String {
        return sayHello(platform.name)
    }
}