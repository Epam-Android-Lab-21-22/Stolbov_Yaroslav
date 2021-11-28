package com.stolbov.emptyprojectstolbov

import kotlin.math.pow

class IBMCalculator {
    companion object{
        private val resultText = "Индекс массы тела:  "
        private val arrayWithAnnotation = arrayOf("Выраженный дефицит массы тела",
            "Недостаточная масса тела",
            "Норма",
            "Избыточная масса тела(предшествует ожирению)",
            "Ожирение 1-й степени",
            "Ожирение 2-й степени",
            "Ожирение 3-й степени")

        fun getIBM(height: Int, weight: Int): String{
            val ibm: Double = weight.toString().toDouble() / (height.toString().toDouble().pow(2) / 10000)

            val resultString = StringBuilder().apply {
                append(resultText)
                append(String.format("%.2f", ibm))
                append("\n")
            }

            return if (ibm <= 16)
                resultString.append(arrayWithAnnotation[0]).toString()
            else if (ibm > 16 && ibm <= 18.5)
                resultString.append(arrayWithAnnotation[1]).toString()
            else if (ibm > 18.5 && ibm <= 25)
                resultString.append(arrayWithAnnotation[2]).toString()
            else if (ibm > 25 && ibm <= 30)
                resultString.append(arrayWithAnnotation[3]).toString()
            else if (ibm > 30 && ibm <= 35)
                resultString.append(arrayWithAnnotation[4]).toString()
            else if (ibm > 35 && ibm <= 40)
                resultString.append(arrayWithAnnotation[5]).toString()
            else
                resultString.append(arrayWithAnnotation[6]).toString()
        }
    }
}