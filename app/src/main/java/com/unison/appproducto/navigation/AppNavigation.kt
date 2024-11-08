package com.unison.appproducto.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.unison.appproducto.YourApp
import com.unison.appproducto.screens.AddProductScreen
import com.unison.appproducto.screens.EditProductScreen
import com.unison.appproducto.screens.FirstScreen
import com.unison.appproducto.screens.Form
import com.unison.appproducto.screens.SecondScreen
import com.unison.appproducto.screens.ThirdScreen
import com.unison.appproducto.viewmodels.ProductViewModel
import com.unison.appproducto.viewmodels.ProductViewModelFactory


@Composable
fun AppNavigation(
    productViewModel: ProductViewModel = viewModel(
        factory = ProductViewModelFactory(YourApp.instance.repository)
    )
) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = AppScreens.FirstScreen.route) {
        composable(route = AppScreens.FirstScreen.route) {
            FirstScreen(navController)
        }
        composable(route = AppScreens.SecondScreen.route) {
            val products = productViewModel.allProducts.collectAsState(initial = emptyList()).value
            SecondScreen(navController, products = products, viewModel = productViewModel)
        }
        composable(route = AppScreens.ThirdScreen.route) {
            ThirdScreen(navController)
        }
        composable(route = AppScreens.FormScreen.route) {
            Form(navController)
        }
        composable(route = AppScreens.AddProductScreen.route) {
            AddProductScreen(navController, productViewModel)
        }
        composable("edit_product/{productId}") { backStackEntry ->
            val productId = backStackEntry.arguments?.getString("productId")?.toInt() ?: return@composable
            val product = productViewModel.getProductById(productId).collectAsState(initial = null).value
            product?.let {
                EditProductScreen(navController = navController, viewModel = productViewModel, product = it)
            }
        }
    }
}
