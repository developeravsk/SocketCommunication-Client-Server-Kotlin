package com.developerabhi.clientsidekotlinfinal.presenter

import android.graphics.drawable.AnimationDrawable
import android.text.TextUtils
import android.widget.EditText
import android.widget.RelativeLayout
import com.developerabhi.clientsidekotlinfinal.interfaces.InterfaceList
import com.developerabhi.clientsidekotlinfinal.model.ClientConnectionModel
import java.net.Socket


class MainActivityPresenter(//instance for view
    private var view: InterfaceList.ClientView
) : InterfaceList.ClientPresenter {

    //instance for model
    private var model: InterfaceList.ClientModel = ClientConnectionModel()

    //initialize our view
    init {
        view.initView()
    }

    //function that starts our socket connection to the user defined ip address and port number
    override fun sendRequest(ip: String, port: String) {
        //variable holding our connection status
        var status: String
        //variable for Socket
        var socket: Socket
        //running network operations separately rather than on a main thread
        Thread {
            try {
                //intializing socket connection and setting connection status
                socket = Socket(ip, port.toInt())
                status = "Connected"
                //retreiving user message and passing user message to model
                socket.outputStream.write(model.getUserMessage().toByteArray())
                socket.outputStream.flush()
                socket.close()
            } catch (e: Exception) {
                //Exception handling socket connection and message retrieval
                status = e.toString()
            }
            //updating the status variable in the model
            model.statusUpdate(status)
            //updating the view instantly after updates
            view.runOnUIThread()
        }.start()


    }


    override fun getStatus(): String {
        //extracting connection status from model
        return model.getStatus()
    }

    fun isValid(valueConnectionStatus: EditText?, valuePortNumber: EditText?): Boolean {
        //validating form: ip and port input from user
        var valid = true
        if (TextUtils.isEmpty(valueConnectionStatus?.text)) {
            valid = false
            valueConnectionStatus!!.error = "Cannot be empty"
        }
        if (TextUtils.isEmpty(valuePortNumber!!.text)) {
            valid = false
            valuePortNumber.error = "Cannot be blank"
        }
        return valid
    }

    fun isMessageValid(valueMsg: EditText?): Boolean {
        //validating message input frm user
        var valid = true
        if (TextUtils.isEmpty(valueMsg?.text)) {
            valid = false
            valueMsg!!.error = "Cannot be empty"
        }
        return valid
    }

    fun setUserMessage(msgtext: String) {
        //setting user message to model
        model.setUserMessage(msgtext)
    }

    fun setAnimation(relativelayout: RelativeLayout?) {
        //gradient color animation on app bacground
        Thread {
            val animationDrawable = relativelayout?.background as AnimationDrawable
            animationDrawable.setEnterFadeDuration(2000)
            animationDrawable.setExitFadeDuration(4000)
            animationDrawable.start()
        }.start()
    }
}