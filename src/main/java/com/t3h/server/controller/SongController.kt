package com.t3h.server.controller

import com.t3h.server.manager.SongManager
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
open class SongController{
    @Autowired
    private lateinit var songManager:SongManager

    @GetMapping("/song/searchSong")
    fun searchSong(
            @RequestParam("songName", required = false) songName:String?,
            @RequestParam("page", required = false, defaultValue = "1")
            pageNumber:Int
    ) : Any{
        return songManager.searchSong(songName, pageNumber)
    }
}