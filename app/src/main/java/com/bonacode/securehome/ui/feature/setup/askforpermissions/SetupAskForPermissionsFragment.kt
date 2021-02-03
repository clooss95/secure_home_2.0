package com.bonacode.securehome.ui.feature.setup.askforpermissions

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import com.afollestad.materialdialogs.MaterialDialog
import com.bonacode.securehome.R
import com.bonacode.securehome.architecture.mvvm.ViewModelFragment
import com.bonacode.securehome.databinding.FragmentSetupAskForPermissionsBinding
import com.bonacode.securehome.ui.feature.main.activity.MainActivity
import com.karumi.dexter.Dexter
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.single.BasePermissionListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SetupAskForPermissionsFragment :
    ViewModelFragment<SetupAskForPermissionsViewModel, FragmentSetupAskForPermissionsBinding>() {
    override fun onCreateViewDataBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentSetupAskForPermissionsBinding =
        FragmentSetupAskForPermissionsBinding.inflate(inflater, container, false)

    override val viewModel: SetupAskForPermissionsViewModel by viewModels()

    override fun onResume() {
        super.onResume()
        if (ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.SEND_SMS
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            viewModel.saveAndNavigateForward()
        }
    }

    override fun subscribe() {
        viewModel.askForPermissionsEvent.observe(this) {
            it?.getContentIfNotHandled()?.let {
                askForPermissions()
            }
        }
        viewModel.navigateToMainActivityEvent.observe(this) {
            MainActivity.start(requireContext())
            requireActivity().finish()
        }
        viewModel.permissionsDeniedEvent.observe(this) {
            MaterialDialog(requireContext()).show {
                title(text = getString(R.string.dialog_permissions_denied_title))
                positiveButton(R.string.dialog_permissions_denied_navigate_to_settings) {
                    val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                    val uri: Uri = Uri.fromParts("package", requireContext().packageName, null)
                    intent.data = uri
                    startActivity(intent)
                }
                negativeButton(R.string.cancel)
            }
        }
    }

    override fun unsubscribe() {
        viewModel.askForPermissionsEvent.removeObservers(this)
        viewModel.navigateToMainActivityEvent.removeObservers(this)
        viewModel.permissionsDeniedEvent.removeObservers(this)
    }

    private fun askForPermissions() {
        Dexter
            .withContext(requireContext())
            .withPermission(Manifest.permission.SEND_SMS)
            .withListener(object : BasePermissionListener() {
                override fun onPermissionGranted(p0: PermissionGrantedResponse?) {
                    viewModel.saveAndNavigateForward()
                }

                override fun onPermissionDenied(p0: PermissionDeniedResponse?) {
                    viewModel.permissionsDenied()
                }
            }).check()
    }
}
