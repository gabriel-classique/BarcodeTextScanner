package com.xcvi.barcodescanner.ui

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.LargeFloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SearchScreen(
    onScanClick: () -> Unit,
    onAddFood: (food: String) -> Unit,
    onGoBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    val searchSimulationList = listOf(
        "Pane", "Riso", "Carne"
    )
    BackHandler {
        onGoBack()
    }
    Scaffold(
        floatingActionButton = {
            LargeFloatingActionButton(
                onClick = { onScanClick() }
            ) {
                Icon(imageVector = Icons.Default.Menu, contentDescription = "")
            }
        }
    ) { padding ->
        LazyColumn(
            modifier = modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            searchSimulationList.forEach {
                item {
                    Column {
                        Card(
                            modifier = modifier
                                .fillMaxWidth()
                                .padding(4.dp),
                            onClick = {
                                onAddFood(it)
                            }
                        ) {
                            Text(
                                text = it,
                                modifier.padding(4.dp),
                                fontSize = MaterialTheme.typography.headlineMedium.fontSize
                            )
                        }
                        Spacer(modifier = modifier.size(2.dp))
                    }
                }
            }
        }
    }
}