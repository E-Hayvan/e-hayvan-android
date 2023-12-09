package com.ehayvan.app.modules.petownerdashboard.`data`.viewmodel

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ehayvan.app.modules.petownerdashboard.`data`.model.PetOwnerDashboardModel
import org.koin.core.KoinComponent

class PetOwnerDashboardVM : ViewModel(), KoinComponent {
  val petOwnerDashboardModel: MutableLiveData<PetOwnerDashboardModel> =
      MutableLiveData(PetOwnerDashboardModel())

  var navArguments: Bundle? = null
}
