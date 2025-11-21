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
import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavGraphBuilder
import com.example.spacekayakandroidassignment_kanhaiyakumar.MainActivity
import com.example.spacekayakandroidassignment_kanhaiyakumar.datastore.setOnboardingDone
import com.example.spacekayakandroidassignment_kanhaiyakumar.ui.onboarding.pager.WelcomePagerScreen
import com.example.spacekayakandroidassignment_kanhaiyakumar.viewmodel.BottomSheetViewModel
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

sealed class Screen(val route: String) {
    object Welcome1 : Screen("welcome1")
    object Welcome2 : Screen("welcome2")
    object Welcome3 : Screen("welcome3")
    object WelcomePager : Screen("welcome_pager")

    object OTPOnboarding : Screen("otp_onboarding")

}

fun NavGraphBuilder.OnboardingNavGraph(
    navController: NavHostController,
    bottomSheetViewModel: BottomSheetViewModel,
    onShowBottomSheet: () -> Unit
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
        val context = LocalContext.current
        val scope = rememberCoroutineScope()

        WelcomeScreen3(
            onGetStartedClick = {
                scope.launch {
                    setOnboardingDone(context)
                    Log.d("onboarding", "onboarding done")

                    // Navigate to OTP onboarding
                    navController.navigate(Screen.OTPOnboarding.route) {
                        popUpTo(Screen.Welcome1.route) { inclusive = true }
                    }
                }
            },
            currentPage = 2,
            totalPages = 3
        )
    }
    composable(Screen.WelcomePager.route) {
        val context = LocalContext.current
        val scope = rememberCoroutineScope()
        WelcomePagerScreen(
            //onShowBottomSheet = onShowBottomSheet,

            onFinish = {
                scope.launch {
                    setOnboardingDone(context)
                    Log.d("onboarding", "onboarding done")

                    // Navigate to OTP onboarding
                    navController.navigate(Screen.OTPOnboarding.route) {
                        popUpTo(Screen.Welcome1.route) { inclusive = true }
                    }
                }
                // If you want to go to OTP screen inside main nav:
            }
        )
    }

    composable(Screen.OTPOnboarding.route) {
        OTPOnboardingScreen(
            nextPage = {
                onShowBottomSheet()
            }
        )
    }

    composable("phone_number_sheet") {
        BackHandler { navController.popBackStack() }

        OTPNavGraph(
            navController = navController,
            onClose = { navController.popBackStack() },
            onVerificationSuccess = {
                navController.navigate("main_activity") {
                    popUpTo(Screen.Welcome1.route) { inclusive = true }
                }
            }
        )
    }
}

