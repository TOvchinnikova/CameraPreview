package com.t_ovchinnikova.android.camerapreview

import android.Manifest
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.t_ovchinnikova.android.camerapreview.ui.theme.CameraPreview
import com.t_ovchinnikova.android.camerapreview.ui.theme.CameraPreviewTheme

class MainActivity : ComponentActivity() {

    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        if (isGranted) {
            return@registerForActivityResult
        } else {
            Toast.makeText(
                this,
                "Permissions not granted by the user.",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requestPermissionLauncher.launch(Manifest.permission.CAMERA)

        setContent {
            CameraPreviewTheme {
                // A surface container using the 'background' color from the theme
                CameraPreview()
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    CameraPreviewTheme {
        Greeting("Android")
    }
}