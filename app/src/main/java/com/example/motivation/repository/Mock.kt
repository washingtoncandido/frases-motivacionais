package com.example.motivation.repository

import com.example.motivation.infra.MotivationCostants
import java.util.*

data class Phrase(val descriptor: String, val category: Int)
class Mock {

    private val ALL = MotivationCostants.PHRASEFILTER.ALL
    private val HAPPY = MotivationCostants.PHRASEFILTER.HAPPY
    private val SUN = MotivationCostants.PHRASEFILTER.SUN

    private val mListPhrase: List<Phrase> = listOf(
        Phrase("frase Happy 1", HAPPY),
        Phrase("frase happy2", HAPPY),
        Phrase("frase  Happy3", HAPPY),
        Phrase("A melhor maneira de prever o futuro é invetá-lo", SUN),
        Phrase("frase sun 2", SUN),
        Phrase("frase sun 3", SUN),
        Phrase("frase sun 4", SUN)
    )

    fun getPhrase(categoryId: Int): String {

        val filterred = mListPhrase.filter { it.category == categoryId || categoryId == ALL }//FILTRANDO AS FRASES PELA CATEGORIA (CASO NÃO ESCOLHA UMA CATEGORIA RETORNA ALL)
        //criando uma valor aletatorio de acordo com a quantidade de frases no listOF
        val rand = Random().nextInt(filterred.size)//evitando erro de fecha o app caso acesse mais frase da categoria
        return filterred[rand].descriptor//passando uma frase aleatoria da listOF
    }
}