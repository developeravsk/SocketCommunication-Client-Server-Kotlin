package com.developerabhi.serversidekotlinfinal.model

import com.developerabhi.serversidekotlinfinal.interfaces.InterfaceList

class ServerConnectionModel : InterfaceList.ServerModel{
    //initialize private variables here
    //These variables will only be initialized once when the presenter creates an instance of the model
    private var status1=""
    private var message=""
    private var ipaddress:String=""
    private var port:Int = 0
    override fun statusUpdate(status: String) {
        this.status1=status
    }

    override fun getStatus(): String {
        return status1
    }

    override fun messageUpdate(msg: String) {
        this.message=msg
    }

    override fun getMsg(): String {
       return message
    }

    override fun setIpAddress(ip: String) {
        ipaddress=ip
    }

    override fun setPort(port: Int) {
       this.port=port
    }

    override fun getIpAddress(): String {
        return ipaddress
    }

    override fun getPort(): Int {
        return port
    }

}
