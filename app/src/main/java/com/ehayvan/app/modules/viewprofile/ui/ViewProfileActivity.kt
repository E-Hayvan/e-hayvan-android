package com.ehayvan.app.modules.viewprofile.ui

import android.view.View
import androidx.activity.viewModels
import com.ehayvan.app.R
import com.ehayvan.app.appcomponents.base.BaseActivity
import com.ehayvan.app.databinding.ActivityViewProfileBinding
import com.ehayvan.app.modules.viewprofile.`data`.model.Content1RowModel
import com.ehayvan.app.modules.viewprofile.`data`.viewmodel.ViewProfileVM
import kotlin.Int
import kotlin.String
import kotlin.Unit

class ViewProfileActivity : BaseActivity<ActivityViewProfileBinding>(R.layout.activity_view_profile)
    {
  private val viewModel: ViewProfileVM by viewModels<ViewProfileVM>()

  override fun onInitialized(): Unit {
    viewModel.navArguments = intent.extras?.getBundle("bundle")
    val contentAdapter = ContentAdapter(viewModel.contentList.value?:mutableListOf())
    binding.recyclerContent.adapter = contentAdapter
    contentAdapter.setOnItemClickListener(
    object : ContentAdapter.OnItemClickListener {
      override fun onItemClick(view:View, position:Int, item : Content1RowModel) {
        onClickRecyclerContent(view, position, item)
      }
    }
    )
    viewModel.contentList.observe(this) {
      contentAdapter.updateData(it)
    }
    binding.viewProfileVM = viewModel
  }

  override fun setUpClicks(): Unit {
    binding.imageArrowleft.setOnClickListener {
      finish()
    }
  }

  fun onClickRecyclerContent(
    view: View,
    position: Int,
    item: Content1RowModel
  ): Unit {
    when(view.id) {
    }
  }

  companion object {
    const val TAG: String = "VIEW_PROFILE_ACTIVITY"

  }
}
