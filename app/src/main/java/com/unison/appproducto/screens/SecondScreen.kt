package com.unison.appproducto.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.unison.appproducto.models.Product
import com.unison.appproducto.navigation.AppScreens
import com.unison.appproducto.ui.theme.ColorBackground
import com.unison.appproducto.ui.theme.ColorPrimary
import com.unison.appproducto.ui.theme.ColorSecondary
import com.unison.appproducto.viewmodels.ProductViewModel

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SecondScreen(navController: NavController, products: List<Product>, viewModel: ProductViewModel) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("LIST VIEW")
                },
                modifier = Modifier,
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Text("<-")
                    }
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = ColorPrimary,
                    titleContentColor = ColorBackground,
                    navigationIconContentColor = ColorBackground
                )
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navController.navigate(route = AppScreens.AddProductScreen.route)
                },
                containerColor = ColorPrimary,
                contentColor = ColorBackground
            ) {
                Icon(
                    Icons.Default.Add,
                    contentDescription = "Add"
                )
            }
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            items(products) { product ->
                ProductItem(product, viewModel, navController)
            }
        }
    }
}


@Composable
fun ProductItem(product: Product, viewModel: ProductViewModel, navController: NavController) {
    val showDialog = remember { mutableStateOf(false) }

    if (showDialog.value) {
        MyDialog(product, viewModel) {
            showDialog.value = false
        }
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        colors = CardDefaults.cardColors(
            containerColor = ColorSecondary
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Top
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = product.name,
                    style = MaterialTheme.typography.titleLarge,
                    color = ColorPrimary
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = product.desc,
                    style = MaterialTheme.typography.bodyLarge,
                    color = ColorBackground
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "$${product.price}",
                    style = MaterialTheme.typography.labelLarge,
                    color = ColorBackground
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "${product.stock}",
                    style = MaterialTheme.typography.labelLarge,
                    color = ColorBackground
                )
            }
            Column(
                modifier = Modifier
                    .padding(start = 8.dp)
                    .fillMaxHeight(),
                horizontalAlignment = Alignment.End,
            ) {
                Icon(
                    Icons.Default.Edit,
                    contentDescription = "Editar",
                    tint = ColorPrimary,
                    modifier = Modifier
                        .clickable {
                            navController.navigate("edit_product/${product.id}")
                        }
                        .padding(top = 8.dp, bottom = 40.dp)
                )
                Icon(
                    Icons.Default.Delete,
                    contentDescription = "Eliminar",
                    tint = ColorPrimary,
                    modifier = Modifier
                        .clickable {
                            showDialog.value = true
                        }
                        .padding(8.dp)
                )
            }
        }
    }
}

@Composable
fun MyDialog(product: Product, viewModel: ProductViewModel, onDismiss: () -> Unit) {
    AlertDialog(
        onDismissRequest = { onDismiss() },
        confirmButton = {
            TextButton(
                onClick = {
                    viewModel.deleteProduct(product)
                    onDismiss()
                }
            ) {
                Text("Eliminar")
            }
        },
        dismissButton = {
            TextButton(
                onClick = {
                    onDismiss()
                }
            ) {
                Text("Cancelar")
            }
        },
        title = { Text(text = "Eliminar artículo") },
        text = { Text(text = "¿Desea eliminar este artículo?") }
    )
}