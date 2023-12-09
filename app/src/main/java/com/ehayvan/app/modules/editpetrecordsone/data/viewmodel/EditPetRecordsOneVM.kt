package com.ehayvan.app.modules.editpetrecordsone.`data`.viewmodel

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ehayvan.app.modules.editpetrecordsone.`data`.model.EditPetRecordsOneModel
import org.koin.core.KoinComponent

class EditPetRecordsOneVM : ViewModel(), KoinComponent {
  val editPetRecordsOneModel: MutableLiveData<EditPetRecordsOneModel> =
      MutableLiveData(EditPetRecordsOneModel())

  var navArguments: Bundle? = null
}
