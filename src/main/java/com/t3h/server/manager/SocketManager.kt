package com.t3h.server.manager

import com.corundumstudio.socketio.AckRequest
import com.corundumstudio.socketio.Configuration
import com.corundumstudio.socketio.SocketIOClient
import com.corundumstudio.socketio.SocketIOServer
import com.corundumstudio.socketio.listener.DataListener
import com.fasterxml.jackson.databind.ObjectMapper
import com.google.gson.Gson
import com.t3h.server.model.MessageContent
import com.t3h.server.model.database.Message
import com.t3h.server.repository.MessageRepository
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import javax.annotation.PostConstruct

@Component
open class SocketManager {
    private val mapId = HashMap<Int, SocketIOClient>()
    private lateinit var socketIOServer: SocketIOServer

    @Autowired
    lateinit var messageRepository:MessageRepository
    @PostConstruct
    fun intis() {
        print("-------------------------------------------------")
        val config = Configuration()
        config.port = 9000
        socketIOServer = SocketIOServer(config)

        //lang nghe cac ket noi cua client den

        socketIOServer.addConnectListener {
            println("addConnectListener......")


        }

        socketIOServer.addDisconnectListener {
            println("addDisconnectListener......")
            for (key in mapId.keys) {
                if (mapId.get(key) == it){
                    mapId.remove(key)
                    break
                }
            }
        }

        socketIOServer.addEventListener("info",
                String::class.java, object : DataListener<String> {
            override fun onData(socket: SocketIOClient, data: String,
                                p2: AckRequest?) {
                println("addEventListener info...... "+data)
                mapId.put(
                        data.toInt(),
                        socket
                )
            }
        })


        socketIOServer.addEventListener("message",
                String::class.java, object : DataListener<String> {
            override fun onData(socket: SocketIOClient, data: String,
                                p2: AckRequest?) {
                try {
                    val ob = Gson().fromJson(
                            data, MessageContent::class.java
                    )
                    //luu vao database
                    //.....
                    val soRe = mapId.get(ob.receiverId)
                    if (soRe != null ){
                        soRe.sendEvent("message", data)
                        saveMessage(ob,"delivery")
                    }else {
                        saveMessage(ob,"pendding")
                    }

                    println("addEventListener message...... "+data)
                }catch ( e:Exception){
                    e.printStackTrace()
                }


            }
        })

        socketIOServer.start()
    }

    private fun saveMessage(messageContent:MessageContent, status:String){
        Observable.create<String>({
            //saveDB
            val mes = Message()
            mes.senderId=messageContent.senderId
            mes.receiverId=messageContent.receiverId
            mes.status = status
            mes.content = messageContent.value
            messageRepository.save(mes)
            it.onNext("")
        })
                .subscribeOn(Schedulers.newThread())
                .subscribe({})
    }


}