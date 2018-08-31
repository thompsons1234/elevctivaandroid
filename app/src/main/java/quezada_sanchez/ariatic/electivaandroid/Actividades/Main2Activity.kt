package quezada_sanchez.ariatic.electivaandroid.Actividades

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main2.*
import quezada_sanchez.ariatic.electivaandroid.R
import vo.Estudiante
import java.util.*



class Main2Activity : AppCompatActivity()
{
    lateinit var estudiante:Estudiante
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val notas:FloatArray = floatArrayOf(1.0f,2f,3f,4f,5f)
        estudiante = Estudiante(fechaNacimiento = Date(), nombre="Kaka",
                esHijoUnico = true, notas = notas)

        val amigo = Estudiante(fechaNacimiento = Date(), nombre = "Roni",
                esHijoUnico = false, notas = floatArrayOf(3f,4f))
        estudiante.amigos.add(amigo)

        actity2_textview_nombre.text = estudiante.nombre
        actity2_fechanacimiento.text = estudiante.fechaNacimiento.toString()
        actity2_textview_hijo_unico.text = if( estudiante.esHijoUnico){
            "Es hijo unico"
        }
        else{
            "No es hijo unico"
        }


        actity2_textview_amigos.text = estudiante.amigos[0].nombre
        actity2_textview_notas.text = estudiante.notas!![0].toString()


    }

    override fun onSaveInstanceState(guardEstado: Bundle?) {
        super.onSaveInstanceState(guardEstado)
        guardEstado?.putString("texto","Android con kotlin")
        guardEstado?.putInt("entero",17)
    }
    override fun onRestoreInstanceState(recupEstado: Bundle?) {
        super.onRestoreInstanceState(recupEstado)
        var texto = recupEstado?.get("texto")
        var numero = recupEstado?.get("entero")
        Log.v("infoRecu","$texto")
        Log.v("infoRecu", "$numero")
    }
    fun pasarAVentanaDos(view: View) {
        Log.v("Main", "Pasar a ventana dos")
        val intent = Intent(this, Main3Activity::class.java)
        intent.putExtra("Parcelable", estudiante)
        startActivityForResult(intent,1)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data:
    Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val resultado = data?.getExtras()?.getString("RESULTADO")


        if (resultCode == RESULT_OK)
        {
            var resultado = data?.getParcelableExtra("Parcelable") as Estudiante
            var nombre:String = resultado.nombre
            Toast.makeText(this, "Se recibio como respuesta un estudiante de nombre $nombre", Toast.LENGTH_LONG).show()
        }
    }
}
