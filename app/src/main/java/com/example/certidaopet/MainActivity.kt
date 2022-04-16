package com.example.certidaopet

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.view.View
import androidx.activity.viewModels

import com.example.certidaopet.databinding.ActivityMainBinding
import com.example.certidaopet.utils.EditTextUtil
import com.github.rtoshiro.util.format.SimpleMaskFormatter
import com.github.rtoshiro.util.format.text.MaskTextWatcher

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainActivityViewModel by viewModels()
    private var isEditing = false
    private var petModel: PetModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setTheme(R.style.Theme_CertidaoPet)
        setContentView(binding.root)
        super.onCreate(savedInstanceState)
        if (petModel == null) petModel = PetModel.defaultPetModel
        initLayout()
        observeActivityCurrentStatus()
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.updatePetModel(getInputtedPet())
    }

    override fun onResume() {
        super.onResume()
        observePetChanges()
    }

    //INIT LAYOUT
    private fun initLayout() {
        initEditText()
        initButtons()
    }

    //EDIT TEXTS
    private fun initEditText() {
        setHints()
        setMasks()
        setInputTypes()
    }

    private fun setHints() {
        binding.edtTxtName.textLayout.hint = getString(R.string.name)
        binding.edtTxtRa.textLayout.hint = getString(R.string.ra)
        binding.edtTxtBirthDate.textLayout.hint = getString(R.string.birthDate)
        binding.edtTxtgender.textLayout.hint = getString(R.string.gender)
        binding.edtTxtCastrated.textLayout.hint = getString(R.string.castrated)
        binding.edtTxtRace.textLayout.hint = getString(R.string.race)
        binding.edtTxtColor.textLayout.hint = getString(R.string.color)
        binding.edtTxtMotherName.textLayout.hint = getString(R.string.mother_name)
        binding.edtTxtFatherName.textLayout.hint = getString(R.string.father_name)
        binding.edtTxtBornIn.textLayout.hint = getString(R.string.born_in)
        binding.edtTxtExpeditionDate.textLayout.hint = getString(R.string.expedition_date)
    }

    private fun setInputTypes() {
        with(binding) {
            edtTxtExpeditionDate.textField.inputType = InputType.TYPE_CLASS_NUMBER
            edtTxtBirthDate.textField.inputType = InputType.TYPE_CLASS_NUMBER
        }
    }

    private fun setMasks() {
        val dateFormat = SimpleMaskFormatter("NN/NN/NNNN")
        with(binding) {
            edtTxtExpeditionDate.textField.addTextChangedListener(
                MaskTextWatcher(edtTxtExpeditionDate.textField, dateFormat)
            )
            edtTxtBirthDate.textField.addTextChangedListener(
                MaskTextWatcher(edtTxtBirthDate.textField, dateFormat)
            )
        }
    }

    private fun toggleEdtTxtEdition(isEditing: Boolean) {
        if (isEditing) enableEditing()
        else disableEditing()
    }

    private fun disableEditing() {
        with(binding) {
            EditTextUtil.disableEditText(edtTxtName.textField)
            EditTextUtil.disableEditText(edtTxtRa.textField)
            EditTextUtil.disableEditText(edtTxtBirthDate.textField)
            EditTextUtil.disableEditText(edtTxtgender.textField)
            EditTextUtil.disableEditText(edtTxtCastrated.textField)
            EditTextUtil.disableEditText(edtTxtRace.textField)
            EditTextUtil.disableEditText(edtTxtColor.textField)
            EditTextUtil.disableEditText(edtTxtMotherName.textField)
            EditTextUtil.disableEditText(edtTxtFatherName.textField)
            EditTextUtil.disableEditText(edtTxtBornIn.textField)
            EditTextUtil.disableEditText(edtTxtExpeditionDate.textField)
        }
    }

    private fun enableEditing() {
        with(binding) {
            EditTextUtil.enableEditText(edtTxtName.textField)
            EditTextUtil.enableEditText(edtTxtRa.textField)
            EditTextUtil.enableEditText(edtTxtBirthDate.textField)
            EditTextUtil.enableEditText(edtTxtgender.textField)
            EditTextUtil.enableEditText(edtTxtCastrated.textField)
            EditTextUtil.enableEditText(edtTxtRace.textField)
            EditTextUtil.enableEditText(edtTxtColor.textField)
            EditTextUtil.enableEditText(edtTxtMotherName.textField)
            EditTextUtil.enableEditText(edtTxtFatherName.textField)
            EditTextUtil.enableEditText(edtTxtBornIn.textField)
        }
    }

    private fun setPetIntoEdtTxt(petModel: PetModel?) {
        if (petModel != null) {
            with(binding) {
                edtTxtName.textField.setText(petModel.name)
                edtTxtRa.textField.setText(petModel.ra)
                edtTxtBirthDate.textField.setText(petModel.birthDate)
                edtTxtgender.textField.setText(petModel.gender)
                edtTxtCastrated.textField.setText(petModel.castrated)
                edtTxtRace.textField.setText(petModel.race)
                edtTxtColor.textField.setText(petModel.color)
                edtTxtMotherName.textField.setText(petModel.motherName)
                edtTxtFatherName.textField.setText(petModel.fatherName)
                edtTxtBornIn.textField.setText(petModel.bornIn)
                edtTxtExpeditionDate.textField.setText(petModel.expeditionDate)
            }
        }
    }

    private fun getInputtedPet(): PetModel {
        return PetModel(
            binding.edtTxtName.textField.text.toString(),
            binding.edtTxtBirthDate.textField.text.toString(),
            binding.edtTxtRa.textField.text.toString(),
            binding.edtTxtgender.textField.text.toString(),
            binding.edtTxtCastrated.textField.text.toString(),
            binding.edtTxtRace.textField.text.toString(),
            binding.edtTxtColor.textField.text.toString(),
            binding.edtTxtMotherName.textField.text.toString(),
            binding.edtTxtFatherName.textField.text.toString(),
            binding.edtTxtBornIn.textField.text.toString(),
            binding.edtTxtExpeditionDate.textField.text.toString(),
        )
    }

    //BUTTONS
    private fun initButtons() {
        setOnActionClickListener()
        setOnCancelClickListener()
    }

    private fun toggleActionLayout(isEditing: Boolean) {
        if (isEditing) setSaveButton()
        else setEditButton()
    }

    private fun setSaveButton() {
        binding.btnAction.text = getString(R.string.save)
        binding.btnAction.setCompoundDrawablesWithIntrinsicBounds(
            R.drawable.ic_baseline_save_24, 0, 0, 0
        )
        binding.imgViewPetPic.alpha = 0.5F
    }

    private fun setEditButton() {
        binding.btnAction.text = getString(R.string.edit)
        binding.btnAction.setCompoundDrawablesWithIntrinsicBounds(
            R.drawable.ic_baseline_edit_24, 0, 0, 0
        )
        binding.imgViewPetPic.alpha = 1F
    }

    private fun setOnActionClickListener() {
        binding.btnAction.setOnClickListener {
            if (isEditing) {
                EditTextUtil.hideKeyboard(this)
                viewModel.updatePetModel(getInputtedPet())
                viewModel.savePet()
                viewModel.closeLayoutForEditing()
            } else {
                viewModel.openLayoutForEditing()
            }
        }
    }

    private fun setOnCancelClickListener() {
        binding.btnCancel.setOnClickListener {
            EditTextUtil.hideKeyboard(this)
            setPetIntoEdtTxt(petModel)
            viewModel.closeLayoutForEditing()
        }
    }

    private fun toggleLayoutButtons(isEditing: Boolean) {
        if (isEditing) {
            binding.btnCancel.visibility = View.VISIBLE
            binding.imgBtnPetPic.visibility = View.VISIBLE
        } else {
            binding.btnCancel.visibility = View.GONE
            binding.imgBtnPetPic.visibility = View.GONE
        }
    }

    //OBSERVING LAYOUT CHANGES
    private fun observeActivityCurrentStatus() {
        viewModel.isEditing().observe(this, { isEditing ->
            this.isEditing = isEditing
            toggleLayoutButtons(isEditing)
            toggleActionLayout(isEditing)
            toggleEdtTxtEdition(isEditing)
        })
    }

    private fun observePetChanges() {
        viewModel.getPetInfo().observe(this, { petModel ->
            this.petModel = petModel
            setPetIntoEdtTxt(petModel)
        })
    }

}