package com.bonacode.securehome.ui.feature.main.activity

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.Settings
import androidx.activity.viewModels
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.input.input
import com.bonacode.securehome.R
import com.bonacode.securehome.architecture.mvvm.ViewModelActivity
import com.bonacode.securehome.databinding.ActivityMainBinding
import com.bonacode.securehome.domain.feature.action.model.ActionModel
import com.bonacode.securehome.domain.feature.action.model.ActionSentEvent
import com.bonacode.securehome.domain.feature.action.model.SmsSendResult
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ViewModelActivity<MainViewModel, ActivityMainBinding>(
    R.layout.activity_main,
    R.id.navHostMainActivity
) {
    override val viewModel: MainViewModel by viewModels()

    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun initViews() {
        setSupportActionBar(safeGetBinding().contentView.findViewById(R.id.toolbar))

        appBarConfiguration = AppBarConfiguration.Builder(
            R.id.homeFragment,
            R.id.controlPanelMainFragment,
            R.id.settingsFragment,
            R.id.actionHistoryFragment
        ).setOpenableLayout(safeGetBinding().drawerLayout).build()

        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration)
        NavigationUI.setupWithNavController(safeGetBinding().navigationView, navController)
    }

    override fun onSupportNavigateUp(): Boolean =
        (NavigationUI.navigateUp(navController, appBarConfiguration) ||
                super.onSupportNavigateUp())

    override fun subscribe() {
        viewModel.showActionSentEvent.observe(this) {
            it?.getContentIfNotHandled()?.let { event -> showActionSentSnackbar(event) }
        }
        viewModel.favouriteActionsLimitReached.observe(this) {
            it?.getContentIfNotHandled()?.let { showFavouriteActionsLimitReachedSnackbar() }
        }
    }

    override fun unsubscribe() {
        viewModel.showActionSentEvent.removeObservers(this)
        viewModel.favouriteActionsLimitReached.removeObservers(this)
    }

    private fun showActionSentSnackbar(event: ActionSentEvent) {
        when (event.result) {
            SmsSendResult.SUCCESS -> showActionSentSuccessSnackbar(
                event.showAddToFavouriteButton,
                event.action
            )
            SmsSendResult.FAILURE -> showActionSentFailureSnackbar()
        }
    }

    private fun showActionSentSuccessSnackbar(
        showAddToFavouriteButton: Boolean,
        action: ActionModel
    ) {
        var snackbar =
            Snackbar.make(
                safeGetBinding().rootView,
                R.string.action_sent_success,
                Snackbar.LENGTH_LONG
            )

        if (showAddToFavouriteButton) {
            snackbar = snackbar.setAction(R.string.add_to_favourite) {
                showEnterFavouriteActionNameDialog(action)
            }
        }

        snackbar.show()
    }

    private fun showActionSentFailureSnackbar() {
        var snackbar = Snackbar.make(
            safeGetBinding().rootView,
            R.string.action_sent_failure,
            Snackbar.LENGTH_LONG
        )

        snackbar = snackbar.setAction(R.string.action_sent_failure_navigate_to_permissions) {
            val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
            val uri: Uri = Uri.fromParts("package", packageName, null)
            intent.data = uri
            startActivity(intent)
        }

        snackbar.show()
    }

    private fun showEnterFavouriteActionNameDialog(action: ActionModel) {
        MaterialDialog(this).show {
            title(text = getString(R.string.dialog_select_fav_name_set_favourite_action_name))
            input(
                hint = getString(R.string.dialog_select_fav_name_enter_fav_action_name),
                allowEmpty = false
            ) { _, text -> viewModel.saveFavouriteAction(action, text.toString()) }
            positiveButton(R.string.ok)
        }
    }

    private fun showFavouriteActionsLimitReachedSnackbar() {
        Snackbar.make(
            safeGetBinding().rootView,
            R.string.favourite_actions_limit_reached,
            Snackbar.LENGTH_LONG
        ).show()
    }

    companion object {
        fun start(context: Context) {
            context.startActivity(Intent(context, MainActivity::class.java))
        }
    }
}
