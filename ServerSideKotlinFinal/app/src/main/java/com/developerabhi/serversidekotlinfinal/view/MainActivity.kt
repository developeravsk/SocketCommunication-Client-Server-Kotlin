package com.developerabhi.serversidekotlinfinal.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.developerabhi.serversidekotlinfinal.R
import com.developerabhi.serversidekotlinfinal.interfaces.InterfaceList
import com.developerabhi.serversidekotlinfinal.presenter.MainActivityPresenter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), InterfaceList.ServerView {
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
        toolbar.title = "Server Side"
        //start button click handling
        start.setOnClickListener{
            //presenter handles the server start logic
            presenter?.startServer()
        }
    }


    override fun updateView() {
        //updating ip address, port number, connection status and message via presenter(View has no idea about the structure of the model
        valueIPAddress.text = presenter?.getIPAddress()
        valuePortNumber.text = presenter?.getPort().toString()
        tvMessage.text = presenter?.getStatus()
        tvStatus.text = presenter?.getMessage()
    }

    override fun runOnUIThread() {
        runOnUiThread {
            //updating connection status and message via presenter(View has no idea about the structure of the model
            tvMessage.text = presenter?.getStatus()
            tvStatus.text = presenter?.getMessage()
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        //presenter handles any connection closing logic
        presenter?.destroy()
    }
}