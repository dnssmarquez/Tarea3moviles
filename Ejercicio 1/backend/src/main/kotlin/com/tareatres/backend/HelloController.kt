package com.tareatres.backend.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class HelloController {
    @GetMapping("/mensaje")
    fun mensaje(): Map<String, String> {
        return mapOf("mensaje" to "¡Hola Mundo desde el backend en Kotlin!")
    }
}
