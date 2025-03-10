package com.example.apprest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.apprest.ui.theme.AppRestTheme
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppRestTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MessageScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun MessageScreen(modifier: Modifier = Modifier) {
    var message by remember { mutableStateOf("Cargando...") }

    LaunchedEffect(Unit) {
        RetrofitClient.instance.obtenerMensaje().enqueue(object : Callback<Map<String, String>> {
            override fun onResponse(call: Call<Map<String, String>>, response: Response<Map<String, String>>) {
                message = response.body()?.get("mensaje") ?: "Error al recibir datos"
            }

            override fun onFailure(call: Call<Map<String, String>>, t: Throwable) {
                message = "Error: ${t.message}"
            }
        })
    }

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = androidx.compose.ui.Alignment.Center) {
        Text(text = message)
    }
}

@Preview(showBackground = true)
@Composable
fun MessagePreview() {
    AppRestTheme {
        MessageScreen()
    }
}
