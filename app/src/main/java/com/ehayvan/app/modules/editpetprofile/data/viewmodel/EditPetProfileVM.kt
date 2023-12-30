package com.ehayvan.app.modules.editpetprofile.data.viewmodel

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ehayvan.app.modules.editpetprofile.data.model.EditPetProfileModel
import org.koin.core.KoinComponent

class EditPetProfileVM : ViewModel(), KoinComponent {
    val editPetProfileModel: MutableLiveData<EditPetProfileModel> =
        MutableLiveData(EditPetProfileModel())

    var navArguments: Bundle? = null
}