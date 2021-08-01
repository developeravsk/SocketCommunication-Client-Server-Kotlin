package com.developerabhi.clientsidekotlinfinal.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.developerabhi.clientsidekotlinfinal.R
import com.developerabhi.clientsidekotlinfinal.interfaces.InterfaceList
import com.developerabhi.clientsidekotlinfinal.presenter.MainActivityPresenter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), InterfaceList.ClientView {
    //presenter instance
    private var presenter: MainActivityPresenter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //initializing presenter instance
        presenter = MainActivityPresenter(this)
    }

    override fun initView() {
        //setting title to our toolbar
        toolbar.title = "Client Side"
        //presenter handling gradient color animation in the app background(this did not work)
        presenter?.setAnimation(relativelayout)
        //submit button click handling
        submit.setOnClickListener{
            //passing user input for form validation
            if (presenter?.isValid(valueIPAddress,valuePortNumber) == true) {
                //presenter handles the request logic if form validation is successfull
                presenter?.sendRequest(
                    valueIPAddress.text.toString(),
                    valuePortNumber.text.toString()
                )
            }
        }
        //message send button click handling
        send.setOnClickListener {
            //passing user input for form validation
            if(presenter?.isMessageValid(valueMsg)==true) {
                if (valueConnectionStatus.text == "Connected") {
                    //presenter handles the request logic if form validation is successfull and saves the user message to the model
                    presenter?.setUserMessage(valueMsg.text.toString())
                    presenter?.sendRequest(
                        valueIPAddress.text.toString(),
                        valuePortNumber.text.toString()
                    )
                }
            }
        }
    }

    override fun updateView() {
        //updating connection status via presenter(View has no idea about the structure of the model
        valueConnectionStatus.text = presenter?.getStatus()
    }

    override fun runOnUIThread() {
        runOnUiThread {
            //updating connection status via presenter(View has no idea about the structure of the model
            valueConnectionStatus.text = presenter?.getStatus()
        }
    }
}