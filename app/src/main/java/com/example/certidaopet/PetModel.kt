package com.example.certidaopet

import com.example.certidaopet.utils.DateUtil
import com.google.gson.Gson

data class PetModel(
    var name: String = "",
    var birthDate: String = "",
    var ra: String = "",
    var gender: String = "",
    var castrated: String = "",
    var race: String = "",
    var color: String = "",
    var motherName: String = "",
    var fatherName: String = "",
    var bornIn: String = "",
    var expeditionDate: String = ""
) {

    init {
        if (expeditionDate.isEmpty()) expeditionDate = DateUtil.getTodayDate()
    }

    companion object {
        val defaultPetModel = PetModel(
            "Marrie Jane",
            "20021999",
            gender = "Feminino",
            castrated = "Yep",
            race = "Persa",
            color = "Branco",
            motherName = "Jamile Gabrielle dos Santos",
            fatherName = "Jamile também (eu acho 🤣)",
            bornIn = "Cataguases - MG",
            expeditionDate = "16/11/2021"
        )

        fun fromJson(string: String?): PetModel? {
            val gson = Gson()
            return gson.fromJson(string, PetModel::class.java)
        }
    }

    override fun equals(other: Any?): Boolean {
        return if (other is PetModel) compareWith(other)
        else false
    }

    private fun compareWith(compareModel: PetModel): Boolean {
        return compareModel.name == this.name &&
                compareModel.birthDate == this.birthDate &&
                compareModel.ra == this.ra &&
                compareModel.gender == this.gender &&
                compareModel.castrated == this.castrated &&
                compareModel.race == this.race &&
                compareModel.color == this.color &&
                compareModel.motherName == this.motherName &&
                compareModel.fatherName == this.fatherName &&
                compareModel.bornIn == this.bornIn //&&
        compareModel.expeditionDate == this.expeditionDate
    }

    fun toJson(): String? {
        val gson = Gson()
        return gson.toJson(this)
    }

}
