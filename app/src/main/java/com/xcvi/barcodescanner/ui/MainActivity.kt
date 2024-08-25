package com.xcvi.barcodescanner.ui

import android.Manifest
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.accompanist.permissions.shouldShowRationale
import com.xcvi.barcodescanner.AnalyzerType
import com.xcvi.barcodescanner.ui.theme.BarcodeScannerTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalPermissionsApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BarcodeScannerTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val cameraPermissionState = rememberPermissionState(Manifest.permission.CAMERA)
                    val navController = rememberNavController()
                    var type by remember {
                        mutableStateOf(AnalyzerType.UNDEFINED)
                    }

                    var foodList by remember {
                        mutableStateOf(emptyList<String>())
                    }

                    if (cameraPermissionState.status.isGranted) {

                        NavHost(navController = navController, startDestination = "food_list_screen") {
                            composable("food_list_screen") {
                                FoodListScreen(
                                    onAddClick = {
                                        navController.navigate("search_screen")
                                    },
                                    list = foodList
                                )
                            }
                            composable("search_screen"){
                                SearchScreen(
                                    onScanClick = {
                                        type = AnalyzerType.BARCODE
                                        navController.navigate("camera_screen")
                                    },
                                    onGoBack = {
                                        navController.popBackStack()
                                    },
                                    onAddFood = {
                                        foodList = foodList + listOf(it)
                                        navController.popBackStack("food_list_screen", false)
                                    }
                                )
                            }
                            composable("camera_screen") {
                                CameraScreen(
                                    analyzerType = type,
                                    onSuccess = { barcode, barcodeScanner, textScanner ->
                                        barcodeScanner?.close()
                                        textScanner?.close()
                                        foodList += listOf(barcode)
                                        type = AnalyzerType.UNDEFINED
                                        navController.popBackStack("food_list_screen", false)
                                    },
                                    onGoBack = {
                                        type = AnalyzerType.UNDEFINED
                                        navController.popBackStack()
                                    }
                                )
                            }
                        }

                    } else if (cameraPermissionState.status.shouldShowRationale) {
                        Text("Camera Permission permanently denied")
                    } else {
                        SideEffect {
                            cameraPermissionState.run { launchPermissionRequest() }
                        }
                        Text("No Camera Permission")
                    }
                }
            }
        }
    }
}
