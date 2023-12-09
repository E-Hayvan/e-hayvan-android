package com.ehayvan.app.modules.editpetrecordstwo.`data`.viewmodel

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ehayvan.app.modules.editpetrecordstwo.`data`.model.EditPetRecordsTwoModel
import org.koin.core.KoinComponent

class EditPetRecordsTwoVM : ViewModel(), KoinComponent {
  val editPetRecordsTwoModel: MutableLiveData<EditPetRecordsTwoModel> =
      MutableLiveData(EditPetRecordsTwoModel())

  var navArguments: Bundle? = null
}
