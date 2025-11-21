package com.example.spacekayakandroidassignment_kanhaiyakumar.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.spacekayakandroidassignment_kanhaiyakumar.ui.onboarding.otp_onboarding.OTPOnboardingScreen
import com.example.spacekayakandroidassignment_kanhaiyakumar.ui.onboarding.welcome_screen_1.WelcomeScreen1
import com.example.spacekayakandroidassignment_kanhaiyakumar.ui.onboarding.welcome_screen_2.WelcomeScreen2
import com.example.spacekayakandroidassignment_kanhaiyakumar.ui.onboarding.welcome_screen_3.WelcomeScreen3
import android.content.Intent
import androidx.activity.compose.BackHandler
import com.example.spacekayakandroidassignment_kanhaiyakumar.MainActivity
import com.example.spacekayakandroidassignment_kanhaiyakumar.viewmodel.BottomSheetViewModel

sealed class Screen(val route: String) {
    object Welcome1 : Screen("welcome1")
    object Welcome2 : Screen("welcome2")
    object Welcome3 : Screen("welcome3")

    object OTPOnboarding : Screen("otp_onboarding")

}

@Composable
fun OnboardingNavGraph(
    navController: NavHostController,
    bottomSheetViewModel: BottomSheetViewModel,
    onShowBottomSheet: () -> Unit
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Welcome1.route
    ) {
        composable(Screen.Welcome1.route) {
            WelcomeScreen1(
                onNextClick = { navController.navigate(Screen.Welcome2.route) },
                currentPage = 0,
                totalPages = 3
            )
        }

        composable(Screen.Welcome2.route) {
            WelcomeScreen2(
                onNextClick = { navController.navigate(Screen.Welcome3.route) },
                currentPage = 1,
                totalPages = 3
            )
        }

        composable(Screen.Welcome3.route) {
            WelcomeScreen3(
                onGetStartedClick = { navController.navigate(Screen.OTPOnboarding.route) },
                currentPage = 2,
                totalPages = 3
            )
        }
        composable(Screen.OTPOnboarding.route) {
            OTPOnboardingScreen(
                nextPage = {
                    // Show the bottom sheet with phone number input
                    // This would typically be handled by a BottomSheetScaffold or ModalBottomSheetLayout
                    // in your activity or a parent composable
                    onShowBottomSheet()
                },
                //bottomSheetViewModel = bottomSheetViewModel
            )
        }

        // Add a route for the bottom sheet
        composable("phone_number_sheet") {
            // This will be shown as a bottom sheet
            val otpNavController = rememberNavController()

            // Handle back press to close the bottom sheet
            BackHandler {
                if (!otpNavController.popBackStack()) {
                    // If we can't go back in OTP flow, close the bottom sheet
                    navController.popBackStack()
                }
            }

            OTPNavGraph(
                navController = otpNavController,
                onClose = { navController.popBackStack() },
                onVerificationSuccess = {
                    // Navigate to the main app or next screen after successful verification
                    navController.navigate("main_activity") {
                        popUpTo(Screen.Welcome1.route) { inclusive = true }
                    }
                }
            )
        }
    }
}