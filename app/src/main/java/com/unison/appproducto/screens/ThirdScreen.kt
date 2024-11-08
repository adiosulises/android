package com.unison.appproducto.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.unison.appproducto.R
import com.unison.appproducto.ui.theme.ColorPrimary
import com.unison.appproducto.ui.theme.ColorSecondary
import com.unison.appproducto.ui.theme.ColorText

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ThirdScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("CARD VIEW")
                },
                modifier = Modifier,
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Text("<-")
                    }
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = ColorPrimary,
                    titleContentColor = ColorText,
                    navigationIconContentColor = ColorText
                )
            )
        },
    ) {
        ThirdBodyContent(navController)
    }
}

@Composable
fun ThirdBodyContent(navController: NavController) {
    val uriHandler = LocalUriHandler.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.user),
            contentDescription = "Imagen del usuario",
            modifier = Modifier.padding(bottom = 16.dp)
        )
        Text(
            text = "email: 3adiosulises@gmail.com",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = ColorSecondary,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Text(
            text = "+52 6623398547",
            color = ColorSecondary,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            textDecoration = TextDecoration.Underline,
            modifier = Modifier.clickable {
                uriHandler.openUri("tel:+526623398547")
            }
        )
    }
}