package com.stolbov.emptyprojectstolbov

class DecisionGenerator {
    companion object {
        private val firstColumnList = listOf("Вам нужно",
            "Советую вам",
            "Это будет страшной ошибкой -",
            "Нужно немедленно",
            "Это очень рискованно -",
            "Невозможно",
            "Лучше всего тайком",
            "Чувствую, что вы хотите",
            "Вы сами знаете, что следует",
            "Повелеваю")
        private val secondColumnList = listOf("заняться этим прямо сейчас",
            "ещё раз всё обдумать",
            "предусмотреть путь для отступления",
            "прыгнуть в омут с головой",
            "поторопиться с принятием решения",
            "забыть об этом",
            "сделать, но никому не рассказывать",
            "рассказать об этом другу и послушать его совета",
            "послушать меня и сделать наоборот",
            "перестать спрашивать у бесполезного меня и принять решение самостоятельно. В КОИ-ТО ВЕКИ!")
        fun generateDecision(): String{
            return firstColumnList[(firstColumnList.indices).random()] + " " + secondColumnList[(secondColumnList.indices).random()]
        }
    }
}