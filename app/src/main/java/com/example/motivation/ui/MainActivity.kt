package com.example.motivation.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.motivation.R
import com.example.motivation.infra.MotivationCostants
import com.example.motivation.infra.SecurityPreferences
import com.example.motivation.repository.Mock
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_splash.*
import kotlinx.android.synthetic.main.activity_splash.textName

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var mSecurityPreferences: SecurityPreferences  //criando um shared
    private var mPhraseFilter :Int =MotivationCostants.PHRASEFILTER.ALL//INICIANDO A TELA ACITIVE COM A FRASE DO ICONE ALL

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //RETIRANDO TOOLBAR NA ACTIVITMAIN
        if (supportActionBar != null) {
            supportActionBar!!.hide()
        }

        mSecurityPreferences = SecurityPreferences(this)//instanciando o shared (criando cosntante)

        textName.text = mSecurityPreferences.getString(MotivationCostants.KEY.PERSON_NAME)//PEGANDO O NOME DO INPUT E COLOCANDO NO TEXTVIEW

        imagAll.setColorFilter(resources.getColor(R.color.colorAccent))//ESCOLHENDO O FILTRO PARA A FRASE
        handleNewPhrase()
        //CHAMANDO OS BOTÕES NA ACTIVITY
        buttonNewPhrase.setOnClickListener(this)
        imagAll.setOnClickListener(this)
        imagHappy.setOnClickListener(this)
        imagSun.setOnClickListener(this)

    }

    override fun onClick(view: View) {
        val id = view.id
        val listFilter = listOf(R.id.imagAll, R.id.imagHappy, R.id.imagSun)
            //VERIFICANDO O BUTÃO CLICADO E CHAMANDO A FUN NOVA FRASE
        if (id == R.id.buttonNewPhrase) {
            handleNewPhrase()
        } else if (id in listFilter) {//FAZENDO O NOVO FILTRO
            handleNewFilter(id)
        }

    }

    private fun handleNewFilter(id: Int) {

            //INICIANDO AS CORES DO ICONE COMO BRANCO
        imagAll.setColorFilter(resources.getColor(R.color.white))//
        imagHappy.setColorFilter(resources.getColor(R.color.white))
        imagSun.setColorFilter(resources.getColor(R.color.white))

        when (id) {
            R.id.imagAll -> {
                imagAll.setColorFilter(resources.getColor(R.color.colorAccent))
                mPhraseFilter =MotivationCostants.PHRASEFILTER.ALL//ESCOLHENDO O FILTRO PARA A FRASE
            }
            R.id.imagHappy -> {
                imagHappy.setColorFilter(resources.getColor(R.color.colorAccent))
                mPhraseFilter =MotivationCostants.PHRASEFILTER.HAPPY//ESCOLHENDO O FILTRO  PARA A FRASE
            }
            R.id.imagSun -> {
                imagSun.setColorFilter(resources.getColor(R.color.colorAccent))
                mPhraseFilter =MotivationCostants.PHRASEFILTER.SUN//ESCOLHENDO O FILTRO PARA A FRASE
            }

        }

    }

    private fun  handleNewPhrase(){
        textPhrase.text = Mock().getPhrase(mPhraseFilter)
    }
}