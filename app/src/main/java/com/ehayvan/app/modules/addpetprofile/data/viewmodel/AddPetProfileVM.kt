package com.ehayvan.app.modules.addpetprofile.`data`.viewmodel

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ehayvan.app.modules.addpetprofile.`data`.model.AddPetProfileModel
import org.koin.core.KoinComponent

class AddPetProfileVM : ViewModel(), KoinComponent {
  val addPetProfileModel: MutableLiveData<AddPetProfileModel> =
      MutableLiveData(AddPetProfileModel())

  var navArguments: Bundle? = null
}
