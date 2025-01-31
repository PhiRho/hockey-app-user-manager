package net.pippah.usermanager

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.AnnotationConfigApplicationContext
import usermanager.AppConfig

@SpringBootApplication
class UserApplication

fun main(args: Array<String>) {
    val context = AnnotationConfigApplicationContext()
    context.register(AppConfig::class.java)

    context.refresh()
    runApplication<UserApplication>(*args)
}