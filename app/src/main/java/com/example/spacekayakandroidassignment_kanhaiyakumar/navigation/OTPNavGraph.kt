package com.example.spacekayakandroidassignment_kanhaiyakumar.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.spacekayakandroidassignment_kanhaiyakumar.ui.otp.OtpVerificationBottomSheet
import com.example.spacekayakandroidassignment_kanhaiyakumar.ui.otp.OtpVerifiedScreen
import com.example.spacekayakandroidassignment_kanhaiyakumar.ui.otp.PhoneNumberBottomSheet
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

sealed class OTPRoutes(val route: String) {
    object PhoneNumber : OTPRoutes("phone_number")
    object OtpVerification : OTPRoutes("otp_verification/{phoneNumber}") {
        fun createRoute(phoneNumber: String) = "otp_verification/$phoneNumber"

    }
    object OtpVerified : OTPRoutes("otp_verified")


}

@Composable
fun OTPNavGraph(
    navController: NavHostController,
    onClose: () -> Unit,
    onVerificationSuccess: () -> Unit
) {
    NavHost(
        navController = navController,
        startDestination = OTPRoutes.PhoneNumber.route
    ) {
        composable(OTPRoutes.PhoneNumber.route) {
            PhoneNumberBottomSheet(
                onClose = onClose,
                onContinue = { phoneNumber ->
                    navController.navigate(OTPRoutes.OtpVerification.createRoute(phoneNumber))
                }
            )
        }

        composable(OTPRoutes.OtpVerification.route) { backStackEntry ->
            val phoneNumber = backStackEntry.arguments?.getString("phoneNumber") ?: ""
            val coroutineScope = rememberCoroutineScope()
            OtpVerificationBottomSheet(
                phoneNumber = phoneNumber,
                onBack = { navController.popBackStack() },
                onOtpChange = { /* Handle OTP changes */ },
                onResend = { /* Handle resend OTP */ },
                onVerify = { enteredOtp ->
                    coroutineScope.launch {
                        // Simulate network verification delay
                        delay(2000) // 2 seconds delay
                        navController.navigate(OTPRoutes.OtpVerified.route)
                    }
                    true // Return true to indicate verification started successfully
                }
            )
        }
        composable(OTPRoutes.OtpVerified.route) {
            OtpVerifiedScreen(
                onBack = {

                    // Step 1 — Close the bottom sheet
                    onClose()

                    // Step 2 — Clear all OTP screens from backstack
                    navController.popBackStack(
                        route = OTPRoutes.PhoneNumber.route,
                        inclusive = true
                    )
                }
            )
        }

    }
}