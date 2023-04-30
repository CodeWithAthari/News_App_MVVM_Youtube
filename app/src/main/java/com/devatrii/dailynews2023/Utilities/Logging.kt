package com.devatrii.dailynews2023.Utilities

import android.util.Log

fun logError(TAG:String,message:String){
    if(message.isEmpty()){
        Log.e(TAG,"Message is Empty")
        return
    }
    Log.e(TAG,message)
}


fun logDebug(TAG:String,message:String){
    if(message.isEmpty()){
        Log.d(TAG,"Message is Empty")
        return
    }
    Log.d(TAG,message)
}


fun logInfo(TAG:String,message:String){
    if(message.isEmpty()){
        Log.i(TAG,"Message is Empty")
        return
    }
    Log.i(TAG,message)
}