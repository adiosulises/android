package com.unison.appproducto.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.unison.appproducto.R
import com.unison.appproducto.navigation.AppScreens
import com.unison.appproducto.ui.theme.ColorBackground
import com.unison.appproducto.ui.theme.ColorPrimary
import com.unison.appproducto.ui.theme.ColorSecondary
import com.unison.appproducto.ui.theme.ColorText

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun FirstScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Box(modifier = Modifier.fillMaxWidth()) {
                        Text(
                            text = "HOME VIEW",
                            modifier = Modifier.align(Alignment.Center),
                        )
                    }
                },
                modifier = Modifier,
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = ColorPrimary,
                    titleContentColor = ColorText,
                    navigationIconContentColor = ColorBackground,
                )
            )
        },
        bottomBar = {
            BottomAppBar (
                containerColor = ColorPrimary
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    text = "Ulises Vidal Cazares Gallegos"
                )
            }
        }
    ) {
        BodyContent(navController)
    }
}

@Composable
fun BodyContent(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription ="Logo",
            modifier = Modifier
                .size(180.dp)
        )
        Text("Adiós Humanos")
        val buttonModifier = Modifier
            .fillMaxWidth(0.5f)
        Button(
            onClick = { navController.navigate(route = AppScreens.SecondScreen.route) },
            modifier = buttonModifier,
            colors = ButtonDefaults.buttonColors(
                containerColor = ColorSecondary,
                contentColor = ColorBackground
            )
        ) {
            Text("Productos")
        }
        Button(
            onClick = { navController.navigate(route = AppScreens.ThirdScreen.route) },
            modifier = buttonModifier,
            colors = ButtonDefaults.buttonColors(
                containerColor = ColorSecondary,
                contentColor = ColorBackground
            )
        ) {
            Text("Presentación")
        }
    }
}