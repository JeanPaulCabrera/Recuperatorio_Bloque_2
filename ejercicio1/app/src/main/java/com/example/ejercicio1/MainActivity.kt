package com.example.ejercicio1

import android.Manifest
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ejercicio1.ui.theme.Ejercicio1Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Ejercicio1Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    // Agregamos un padding top al Greeting para que no se solape
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )

                    // Pasamos el modifier para posicionar los botones dentro del Scaffold correctamente
                    BotonesRow(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Ejercicio1Theme {
        Greeting("Android")
    }
}

@Composable
fun BotonesRow(modifier: Modifier = Modifier) {
    val context = LocalContext.current

    // 1. Launcher para permiso de GALERÍA
    val galleryPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            Toast.makeText(context, "Permiso de Galería Concedido", Toast.LENGTH_SHORT).show()
            // Aquí iría el código para abrir la galería
        } else {
            Toast.makeText(context, "Permiso de Galería Denegado", Toast.LENGTH_SHORT).show()
        }
    }

    // 2. Launcher para permiso de LLAMADAS
    val callPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            Toast.makeText(context, "Permiso de Llamadas Concedido", Toast.LENGTH_SHORT).show()
            // Aquí iría el código para realizar la llamada
        } else {
            Toast.makeText(context, "Permiso de Llamadas Denegado", Toast.LENGTH_SHORT).show()
        }
    }

    Row(
        modifier = modifier // Usamos el modifier que viene del Scaffold
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Botón Galería
        Button(
            onClick = {
                // Lógica para solicitar permiso según versión de Android
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    // Android 13+ usa READ_MEDIA_IMAGES
                    galleryPermissionLauncher.launch(Manifest.permission.READ_MEDIA_IMAGES)
                } else {
                    // Android 12 e inferior usa READ_EXTERNAL_STORAGE
                    galleryPermissionLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE)
                }
            },
            modifier = Modifier.weight(1f)
        ) {
            Text(text = "Galeria")
        }

        // Botón Llamadas
        Button(
            onClick = {
                callPermissionLauncher.launch(Manifest.permission.CALL_PHONE)
            },
            modifier = Modifier.weight(1f)
        ) {
            Text(text = "Llamadas")
        }
    }
}