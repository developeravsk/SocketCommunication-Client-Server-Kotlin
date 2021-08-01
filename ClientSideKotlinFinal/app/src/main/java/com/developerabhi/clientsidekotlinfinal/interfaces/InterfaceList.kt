package com.developerabhi.clientsidekotlinfinal.interfaces

interface InterfaceList {

    //interfaces for view
    interface ClientView{
        fun initView()
        fun updateView()
        fun runOnUIThread()
    }

    //interfaces for presenter
    interface ClientPresenter{
        fun sendRequest(ip: String, port: String)
        fun getStatus():String
    }

    //interfaces for model
    // we are only ealing with connection status and usermessage here
    interface ClientModel{
        fun statusUpdate(status: String)
        fun getStatus():String
        fun setUserMessage(msg: String)
        fun getUserMessage():String
    }

}