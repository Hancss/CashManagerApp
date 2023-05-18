package com.example.proyectof

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
/*    lateinit var inicio_mail:EditText
    lateinit var inicio_pass:EditText
    lateinit var boton_iniciar:Button*/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

/*        inicio_mail=findViewById(R.id.inicioEmail)
        inicio_pass=findViewById(R.id.inicioPass)
        boton_iniciar=findViewById(R.id.buttonIniciar)*/






        val tvGoRegister = findViewById<TextView>(R.id.tv_go_to_register)
        tvGoRegister.setOnClickListener{
        goToRegister()
        }
    }
    private fun goToRegister() {
        val i = Intent(this, RegisterActivity::class.java)
        startActivity(i)
    }

    private fun goToPrincipal() {
        val iprincipal = Intent(this, PantallaPrincipal::class.java)
        startActivity(iprincipal)
    }

    private fun validarCredenciales() {

    }



}