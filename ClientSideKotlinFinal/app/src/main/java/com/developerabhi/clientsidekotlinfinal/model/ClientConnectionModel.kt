package com.developerabhi.clientsidekotlinfinal.model

import com.developerabhi.clientsidekotlinfinal.interfaces.InterfaceList

class ClientConnectionModel: InterfaceList.ClientModel {
    //initialize private variables here
    //These variables will only be initialized once when the presenter creates an instance of the model
    private var status1="Disconnected"
    private var usermessage="Hello message"
    override fun statusUpdate(status: String) {
        this.status1=status
    }

    override fun getStatus(): String {
        return status1
    }

    override fun setUserMessage(msg: String) {
        usermessage=msg
    }

    override fun getUserMessage(): String {
    return usermessage
    }
}