package com.t3h.server

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.PropertySource

@SpringBootApplication
@PropertySource("classpath:application.properties")
open class Main {
   companion object{
       @JvmStatic
       fun main(s:Array<String>){
           SpringApplication.run(Main::class.java, *s)
       }
   }
}