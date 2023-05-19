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

        val botonIngresoSistema=findViewById<Button>(R.id.buttonIniciar)
        botonIngresoSistema.setOnClickListener {
            validarCredenciales()
        }




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
        val correoIngresar: EditText = findViewById(R.id.inicioEmail)
        val passwordIngresar: EditText = findViewById(R.id.inicioPass)

        val correo = correoIngresar.text.toString()
        val claveIngreso = passwordIngresar.text.toString()
        if (correo == "css@gmail.com" && claveIngreso == "1234") {
            val intent = Intent(this, PantallaPrincipal::class.java)
            startActivity(intent)
        }else {
            // Las credenciales son inválidas, mostrar un mensaje de error al usuario
            Toast.makeText(this, "Correo electrónico o contraseña inválidos", Toast.LENGTH_SHORT).show()}

    }

}