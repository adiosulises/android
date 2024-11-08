package com.unison.appproducto.navigation

sealed class AppScreens(val route: String) {
    object FirstScreen: AppScreens("first_screen")
    object SecondScreen: AppScreens("second_screen")
    object ThirdScreen: AppScreens("third_screen")
    object FormScreen: AppScreens("form_screen")
    object AddProductScreen: AppScreens("product_screen")
    object EditProductScreen: AppScreens("edit_screen")
}