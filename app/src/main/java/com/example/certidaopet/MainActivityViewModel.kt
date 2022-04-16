package com.example.certidaopet

import android.app.Application
import android.util.Log
import androidx.lifecycle.*

class MainActivityViewModel(application: Application) : AndroidViewModel(application) {
    private val sharedPref = PetSharedPreference(application)
    private val editStatus: MutableLiveData<Boolean> = MutableLiveData<Boolean>(false)

    fun isEditing(): LiveData<Boolean> = editStatus
    fun openLayoutForEditing() { editStatus.value = true }
    fun closeLayoutForEditing() { editStatus.value = false }

    private val pet: MutableLiveData<PetModel?> by lazy {
        MutableLiveData(sharedPref.getPet())
    }

    fun getPetInfo(): LiveData<PetModel?> = pet
    fun updatePetModel(petModel: PetModel){pet.value = petModel}
    fun savePet(){ sharedPref.savePet(pet.value!!) }

}

