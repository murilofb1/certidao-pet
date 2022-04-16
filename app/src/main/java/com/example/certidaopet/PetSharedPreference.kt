package com.example.certidaopet

import android.app.Application
import android.content.Context

class PetSharedPreference(application: Application) {
    private val petPreferences =
        application.getSharedPreferences(PET_SHARED_REFERENCE, Context.MODE_PRIVATE)
    private val editor = petPreferences.edit()

    companion object {
        const val PET_SHARED_REFERENCE = "pet"
    }

    fun savePet(petModel: PetModel) {
        editor.putString(PET_SHARED_REFERENCE,petModel.toJson())
        editor.commit()
    }

    fun getPet(): PetModel? {
        val petJson = petPreferences.getString(PET_SHARED_REFERENCE, "")
        val returnedPetModel = PetModel.fromJson(petJson)
        return returnedPetModel ?: PetModel()
    }
}