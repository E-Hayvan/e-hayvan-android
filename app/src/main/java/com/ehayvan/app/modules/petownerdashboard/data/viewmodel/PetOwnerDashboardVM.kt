package com.ehayvan.app.modules.petownerdashboard.`data`.viewmodel

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ehayvan.app.modules.petownerdashboard.data.model.ListPetRowModel
import com.ehayvan.app.modules.petownerdashboard.`data`.model.PetOwnerDashboardModel
import com.ehayvan.app.modules.veterinariandashboard.data.model.ListtitleRowModel
import org.koin.core.KoinComponent

class PetOwnerDashboardVM : ViewModel(), KoinComponent {
  val petOwnerDashboardModel: MutableLiveData<PetOwnerDashboardModel> =
      MutableLiveData(PetOwnerDashboardModel())

  var navArguments: Bundle? = null

    val petList: MutableLiveData<MutableList<ListPetRowModel>> =
        MutableLiveData(mutableListOf())
}
