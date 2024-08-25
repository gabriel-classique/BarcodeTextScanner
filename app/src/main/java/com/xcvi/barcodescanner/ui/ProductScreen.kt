package com.xcvi.barcodescanner.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

@Composable
fun ProductScreen(
    foodId: String,
    onConfirm: (foodId: String, amount: String) -> Unit
) {
    var amountValue by remember {
        mutableStateOf("")
    }
    Column {
        Text(text ="Food: " + foodId)
        TextField(value = amountValue, onValueChange = {amountValue = it})
        Button(onClick = { onConfirm(foodId, amountValue) }) {
            Text(text = "Add this food")
        }
    }
}