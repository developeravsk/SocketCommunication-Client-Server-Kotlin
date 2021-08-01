package com.developerabhi.serversidekotlinfinal.interfaces

class InterfaceList {
    //interfaces for view
    interface ServerView{
        fun initView()
        fun updateView()
        fun runOnUIThread()

    }
    //interfaces for presenter
    interface ServerPresenter{
        fun startServer()
        fun getIPAddress():String
        fun getPort():Int
        fun getStatus():String
        fun getMessage():String

    }
    //interfaces for model
    interface ServerModel{
        fun statusUpdate(status: String)
        fun getStatus():String
        fun messageUpdate(msg: String)
        fun getMsg():String
        fun setIpAddress(ip:String)
        fun setPort(port: Int)
        fun getIpAddress():String
        fun getPort():Int
    }
}