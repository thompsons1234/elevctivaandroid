package quezada_sanchez.ariatic.electivaandroid.Actividades

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main3.*
import quezada_sanchez.ariatic.electivaandroid.R
import vo.Estudiante

class Main3Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)

        var estudiante = intent.getParcelableExtra<Estudiante>("Parcelable")


        actity3_textview_nombre.text = estudiante.nombre
        actity3_fechanacimiento.text = estudiante.fechaNacimiento.toString()
        actity3_textview_hijo_unico.text = if( estudiante.esHijoUnico){
            "Es hijo unico"
        }
        else{
            "No es hijo unico"
        }


        actity3_textview_amigos.text = estudiante.amigos[0].nombre
        actity3_textview_notas.text = estudiante.notas!![0].toString()



    }
}
