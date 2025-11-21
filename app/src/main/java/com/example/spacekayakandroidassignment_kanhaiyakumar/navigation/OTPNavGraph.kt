package com.example.spacekayakandroidassignment_kanhaiyakumar.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.spacekayakandroidassignment_kanhaiyakumar.ui.otp.OtpVerificationBottomSheet
import com.example.spacekayakandroidassignment_kanhaiyakumar.ui.otp.OtpVerifiedScreen
import com.example.spacekayakandroidassignment_kanhaiyakumar.ui.otp.PhoneNumberBottomSheet

sealed class OTPRoutes(val route: String) {
    object PhoneNumber : OTPRoutes("phone_number")
    object OtpVerification : OTPRoutes("otp_verification/{phoneNumber}") {
        fun createRoute(phoneNumber: String) = "otp_verification/$phoneNumber"

    }object OtpVerified : OTPRoutes("otp_verified")

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
            OtpVerificationBottomSheet(
                phoneNumber = phoneNumber,
                onBack = { navController.popBackStack() },
                onOtpChange = { /* Handle OTP changes */ },
                onResend = { /* Handle resend OTP */ },
                onVerify = {
                    // Handle successful verification
                    navController.navigate(OTPRoutes.OtpVerified.route)
                }
            )
        }
        composable(OTPRoutes.OtpVerified.route) {
            OtpVerifiedScreen(
            )
        }
    }
}