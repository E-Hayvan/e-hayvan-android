package com.ehayvan.app.modules.editpetrecordstwoempty.`data`.viewmodel

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ehayvan.app.modules.editpetrecordstwoempty.`data`.model.EditPetRecordsTwoEmptyModel
import org.koin.core.KoinComponent

class EditPetRecordsTwoEmptyVM : ViewModel(), KoinComponent {
  val editPetRecordsTwoEmptyModel: MutableLiveData<EditPetRecordsTwoEmptyModel> =
      MutableLiveData(EditPetRecordsTwoEmptyModel())

  var navArguments: Bundle? = null
}
