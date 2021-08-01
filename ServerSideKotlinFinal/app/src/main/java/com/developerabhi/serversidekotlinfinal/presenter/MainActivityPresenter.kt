package com.developerabhi.serversidekotlinfinal.presenter


import com.developerabhi.serversidekotlinfinal.interfaces.InterfaceList
import com.developerabhi.serversidekotlinfinal.model.ServerConnectionModel
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.NetworkInterface
import java.net.ServerSocket
import java.net.SocketException

class MainActivityPresenter(//instance for view
    private var view: InterfaceList.ServerView
) : InterfaceList.ServerPresenter {

    //instance for model
    private var model: InterfaceList.ServerModel = ServerConnectionModel()

    //instance for Serversocket
    private var serverSocket: ServerSocket? = null

    //instance our view
    init {
        view.initView()
    }

    //extract assigned ip address to the device by the router
    private fun getIpAddressFromLocal(): String {
        var ip = ""
        try {

            val enumNetworkInterfaces = NetworkInterface
                .getNetworkInterfaces()
            while (enumNetworkInterfaces.hasMoreElements()) {
                val networkInterface = enumNetworkInterfaces
                    .nextElement()
                val enumInetAddress = networkInterface
                    .inetAddresses
                while (enumInetAddress.hasMoreElements()) {
                    val inetAddress = enumInetAddress.nextElement()
                    if (inetAddress.isSiteLocalAddress) {
                        ip = inetAddress.hostAddress.toString()
                    }
                }
            }
        } catch (e: SocketException) {
            //NetworkInterface exception handling
            e.printStackTrace()
            ip = "Cannot extract ip: $e"
        }
        return ip

    }


    override fun startServer() {
        //fixed port defined for connnection
        val socketServerPort = 6004
        //setting ip address and port to the model variables
        model.setIpAddress(getIpAddressFromLocal())
        model.setPort(socketServerPort)
        //updaing the view
        view.updateView()
        //initializing Serverconnection to SerSocket
        serverSocket = ServerSocket(socketServerPort)
        model.statusUpdate("I'm waiting here: " + serverSocket!!.localPort)
        //update the view
        view.updateView()
        Thread {
            while (true) {
                //accept socket communication from clients
                val socket = serverSocket!!.accept()
                //extracting user message via InputStreamReader
                val message = BufferedReader(InputStreamReader(socket.inputStream)).readLine()
                //setting status update and message update to the model
                model.statusUpdate("Connection from ${socket.inetAddress}:${socket.port}")
                model.messageUpdate(message)
                //makes changes to the UI, since we are inside a separate thread the control need to be
                //reverted back to the main thread or UI thread
                view.runOnUIThread()
            }
        }.start()

    }

    override fun getIPAddress(): String {
        //extracting ip address from model
        return model.getIpAddress()
    }

    override fun getPort(): Int {
        //extracting port from the model
        return model.getPort()
    }

    override fun getStatus(): String {
        //setting status update to the model
        return model.getStatus()
    }

    override fun getMessage(): String {
        //setting message update to the model
        return model.getMsg()
    }

    fun destroy() {
        //OnDestroy function invocation. Closing the serversocket connection
        serverSocket?.close()
    }

}