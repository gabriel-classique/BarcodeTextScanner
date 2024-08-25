package com.xcvi.barcodescanner.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Card
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.accompanist.permissions.ExperimentalPermissionsApi

@ExperimentalPermissionsApi
@Composable
fun FoodListScreen(
    onAddClick: () -> Unit,
    list: List<String>,
    modifier: Modifier = Modifier
) {

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = { onAddClick() }
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "")
            }
        }
    ) { padding ->
        LazyColumn(
            modifier = modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            item {
                Text(
                    text = "List",
                    modifier.padding(4.dp),
                    fontSize = MaterialTheme.typography.headlineMedium.fontSize
                )
            }
            list.forEach {
                item {
                    Column {
                        Text(
                            text = it,
                            modifier.padding(4.dp),
                            fontSize = MaterialTheme.typography.bodyMedium.fontSize
                        )
                        Spacer(modifier = modifier.size(8.dp))
                    }
                }
            }
        }
    }
}