package com.example.ejercicio1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
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
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )

                    BotonesRow()
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
fun BotonesRow() {
    Row(
        modifier = Modifier
            .fillMaxWidth() // Ocupa todo el ancho de la pantalla
            .padding(16.dp), // Margen externo
        horizontalArrangement = Arrangement.spacedBy(16.dp) // Espacio entre los botones
    ) {
        // Botón Galería (usamos weight para que ambos midan lo mismo)
        Button(
            onClick = { /* Acción para Galería */ },
            modifier = Modifier.weight(1f)
        ) {
            Text(text = "Galeria")
        }

        // Botón Llamadas
        Button(
            onClick = { /* Acción para Llamadas */ },
            modifier = Modifier.weight(1f)
        ) {
            Text(text = "Llamadas")
        }
    }
}