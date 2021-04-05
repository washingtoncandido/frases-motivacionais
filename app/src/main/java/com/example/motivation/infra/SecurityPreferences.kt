package com.example.motivation.infra

import android.content.Context
import android.text.Editable
import java.security.AccessControlContext

class SecurityPreferences( context: Context)  {

    private val msharedPreferences =
        context.getSharedPreferences("motivation", Context.MODE_PRIVATE)

    fun storeString(key: String, value: String) {
        msharedPreferences.edit().putString(key, value).apply()
    }

    fun getString(key: String):String {
        return msharedPreferences.getString(key,"")?:"" //?""=Criando um if que retorna nulo
    }


}