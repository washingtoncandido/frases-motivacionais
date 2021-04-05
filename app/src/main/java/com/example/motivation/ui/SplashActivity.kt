package com.example.motivation.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

import android.widget.Toast
import com.example.motivation.R
import com.example.motivation.infra.MotivationCostants
import com.example.motivation.infra.SecurityPreferences
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var mSecurityPreferences: SecurityPreferences  //criando um shared

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        mSecurityPreferences = SecurityPreferences(this)//instanciando o shared

        if (supportActionBar != null) {
            supportActionBar!!.hide()
        }

        buttonSalve.setOnClickListener(this)

        verifyName()
    }

    override fun onClick(view: View) {
        var id = view.id
        if (id == R.id.buttonSalve) {
            handleSave()
        }
    }

    private fun handleSave() {
        val name = editName.text.toString()

        if (name != "") {
            mSecurityPreferences.storeString(MotivationCostants.KEY.PERSON_NAME,name)
            val intent =Intent(this, MainActivity::class.java)
            startActivity(intent)
            
        } else {
            Toast.makeText(this, "Informe seu nome !", Toast.LENGTH_SHORT).show()
        }
    }

    private  fun   verifyName(){
       val name = mSecurityPreferences.getString(MotivationCostants.KEY.PERSON_NAME)

        if (name !=""){
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
            finish()
        }

    }
}