package com.ehayvan.app.modules.vetsearchscreen.`data`.viewmodel

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ehayvan.app.modules.vetsearchscreen.`data`.model.VetSearchScreenModel
import org.koin.core.KoinComponent

class VetSearchScreenVM : ViewModel(), KoinComponent {
  val vetSearchScreenModel: MutableLiveData<VetSearchScreenModel> =
      MutableLiveData(VetSearchScreenModel())

  var navArguments: Bundle? = null
}
