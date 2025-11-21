package com.example.spacekayakandroidassignment_kanhaiyakumar

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.spacekayakandroidassignment_kanhaiyakumar.navigation.OTPNavGraph
import com.example.spacekayakandroidassignment_kanhaiyakumar.navigation.OnboardingNavGraph
import com.example.spacekayakandroidassignment_kanhaiyakumar.ui.onboarding.otp_onboarding.OTPOnboardingScreen
import com.example.spacekayakandroidassignment_kanhaiyakumar.ui.theme.SpacekayakAndroidAssignmentKanhaiyaKumarTheme
import com.example.spacekayakandroidassignment_kanhaiyakumar.viewmodel.BottomSheetViewModel
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {


            SpacekayakAndroidAssignmentKanhaiyaKumarTheme {
                Surface(
                    modifier = Modifier.fillMaxSize()
                        .systemBarsPadding(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainApp()

                }
            }
        }
    }
}

// Update MainActivity.kt
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainApp() {
    val navController = rememberNavController()
    val coroutineScope = rememberCoroutineScope()
    val bottomSheetState = rememberModalBottomSheetState(skipPartiallyExpanded = false)
    val bottomSheetViewModel: BottomSheetViewModel = viewModel()

    // Handle back press
    BackHandler(enabled = bottomSheetState.isVisible) {
        coroutineScope.launch {
            bottomSheetState.hide()
            bottomSheetViewModel.hideBottomSheet()
        }
    }

    // Main content
    val onCloseBottomSheet: () -> Unit = {
        coroutineScope.launch {
            bottomSheetState.hide()
            bottomSheetViewModel.hideBottomSheet()
        }
    }

    // Main content with NavHost
    NavHost(
        navController = navController,
        startDestination = "onboarding"
    ) {
        composable("onboarding") {
            // Show OnboardingNavGraph
            OnboardingNavGraph(
                navController = navController,
                bottomSheetViewModel = bottomSheetViewModel,
                onShowBottomSheet = {
                    coroutineScope.launch {
                        bottomSheetState.show()
                        bottomSheetViewModel.showBottomSheet()
                    }
                }
            )
        }
        // Add other destinations as needed
    }

    // Bottom Sheet
    if (bottomSheetViewModel.isBottomSheetVisible) {
        ModalBottomSheet(
            onDismissRequest = onCloseBottomSheet,
            sheetState = bottomSheetState
        ) {
            // OTP Flow in Bottom Sheet
            OTPNavGraph(
                navController = rememberNavController(),
                onClose = onCloseBottomSheet,
                onVerificationSuccess = {
                    coroutineScope.launch {
                        bottomSheetState.hide()
                        bottomSheetViewModel.hideBottomSheet()
                        // Navigate to home or next screen
                        navController.navigate("home") {
                            popUpTo(0)
                        }
                    }
                }
            )
        }
    }
}

