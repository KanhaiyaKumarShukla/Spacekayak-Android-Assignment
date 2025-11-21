package com.example.spacekayakandroidassignment_kanhaiyakumar.model


data class Country(
    val name: String,
    val code: String,
    val dialCode: String,
    val flag: String
)

val countries = listOf(
    Country("United States", "US", "+1", "🇺🇸"),
    Country("United Kingdom", "GB", "+44", "🇬🇧"),
    Country("Canada", "CA", "+1", "🇨🇦"),
    Country("Australia", "AU", "+61", "🇦🇺"),
    Country("Germany", "DE", "+49", "🇩🇪"),
    Country("France", "FR", "+33", "🇫🇷"),
    Country("India", "IN", "+91", "🇮🇳"),
    Country("Japan", "JP", "+81", "🇯🇵"),
    Country("China", "CN", "+86", "🇨🇳"),
    Country("Brazil", "BR", "+55", "🇧🇷"),
    Country("Russia", "RU", "+7", "🇷🇺"),
    Country("South Africa", "ZA", "+27", "🇿🇦")
)