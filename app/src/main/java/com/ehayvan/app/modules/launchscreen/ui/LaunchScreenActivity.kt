package com.ehayvan.app.modules.launchscreen.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Handler
import android.os.Looper
import androidx.activity.viewModels
import com.ehayvan.app.R
import com.ehayvan.app.appcomponents.base.BaseActivity
import com.ehayvan.app.databinding.ActivityLaunchScreenBinding
import com.ehayvan.app.modules.editpetprofile.ui.EditPetProfileActivity
import com.ehayvan.app.modules.editpetrecordsthree.ui.EditPetRecordsThreeActivity
import com.ehayvan.app.modules.editpetrecordstwo.ui.EditPetRecordsTwoActivity
import com.ehayvan.app.modules.editpetrecordstwoempty.ui.EditPetRecordsTwoEmptyActivity
import com.ehayvan.app.modules.editprofile.ui.EditProfileActivity
import com.ehayvan.app.modules.launchscreen.`data`.viewmodel.LaunchScreenVM
import com.ehayvan.app.modules.petownerdashboard.ui.PetOwnerDashboardActivity
import com.ehayvan.app.modules.register.ui.RegisterActivity
import com.ehayvan.app.modules.veterinariandashboard.ui.VeterinarianDashboardActivity
import kotlin.String
import kotlin.Unit

@SuppressLint("CustomSplashScreen")
class LaunchScreenActivity :
    BaseActivity<ActivityLaunchScreenBinding>(R.layout.activity_launch_screen) {
  private val viewModel: LaunchScreenVM by viewModels<LaunchScreenVM>()

  override fun onInitialized(): Unit {
    viewModel.navArguments = intent.extras?.getBundle("bundle")
    binding.launchScreenVM = viewModel

    val handler = Handler(Looper.getMainLooper())

    // Use the postDelayed method to introduce a delay
    handler.postDelayed({
      // Create an Intent to start RegisterActivity
      val intent = Intent(this, RegisterActivity::class.java)

      // Optionally, you can pass data to the RegisterActivity using extras
      // intent.putExtra("key", value)

      // Start RegisterActivity
      startActivity(intent)

      // Finish the LaunchScreenActivity to prevent the user from navigating back to it
      finish()
    }, 2000)
  }

  override fun setUpClicks(): Unit {
  }

  companion object {
    const val TAG: String = "LAUNCH_SCREEN_ACTIVITY"

  }
}
