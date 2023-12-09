package com.ehayvan.app.modules.veterinariandashboard.ui

import android.view.View
import androidx.activity.viewModels
import com.ehayvan.app.R
import com.ehayvan.app.appcomponents.base.BaseActivity
import com.ehayvan.app.databinding.ActivityVeterinarianDashboardBinding
import com.ehayvan.app.modules.veterinariandashboard.`data`.model.ListtitleRowModel
import com.ehayvan.app.modules.veterinariandashboard.`data`.viewmodel.VeterinarianDashboardVM
import kotlin.Int
import kotlin.String
import kotlin.Unit

class VeterinarianDashboardActivity :
    BaseActivity<ActivityVeterinarianDashboardBinding>(R.layout.activity_veterinarian_dashboard) {
  private val viewModel: VeterinarianDashboardVM by viewModels<VeterinarianDashboardVM>()

  override fun onInitialized(): Unit {
    viewModel.navArguments = intent.extras?.getBundle("bundle")
    val listtitleAdapter = ListtitleAdapter(viewModel.listtitleList.value?:mutableListOf())
    binding.recyclerListtitle.adapter = listtitleAdapter
    listtitleAdapter.setOnItemClickListener(
    object : ListtitleAdapter.OnItemClickListener {
      override fun onItemClick(view:View, position:Int, item : ListtitleRowModel) {
        onClickRecyclerListtitle(view, position, item)
      }
    }
    )
    viewModel.listtitleList.observe(this) {
      listtitleAdapter.updateData(it)
    }
    binding.veterinarianDashboardVM = viewModel
  }

  override fun setUpClicks(): Unit {
  }

  fun onClickRecyclerListtitle(
    view: View,
    position: Int,
    item: ListtitleRowModel
  ): Unit {
    when(view.id) {
    }
  }

  companion object {
    const val TAG: String = "VETERINARIAN_DASHBOARD_ACTIVITY"

  }
}
