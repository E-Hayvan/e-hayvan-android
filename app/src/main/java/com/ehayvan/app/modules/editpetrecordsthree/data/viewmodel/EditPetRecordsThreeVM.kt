package com.ehayvan.app.modules.editpetrecordsthree.`data`.viewmodel

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ehayvan.app.modules.editpetrecordsthree.`data`.model.EditPetRecordsThreeModel
import org.koin.core.KoinComponent

class EditPetRecordsThreeVM : ViewModel(), KoinComponent {
  val editPetRecordsThreeModel: MutableLiveData<EditPetRecordsThreeModel> =
      MutableLiveData(EditPetRecordsThreeModel())

  var navArguments: Bundle? = null
}
