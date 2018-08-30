package quezada_sanchez.ariatic.electivaandroid.Actividades

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import quezada_sanchez.ariatic.electivaandroid.R
import quezada_sanchez.ariatic.electivaandroid.Utilidades.ConexionSQLiteHelper

class MainActivity : AppCompatActivity() {

    lateinit var usersDBHelper : ConexionSQLiteHelper

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        usersDBHelper = ConexionSQLiteHelper(this)





    }
}
