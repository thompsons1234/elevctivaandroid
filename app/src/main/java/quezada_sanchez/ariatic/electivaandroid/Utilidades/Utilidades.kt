package quezada_sanchez.ariatic.electivaandroid.Utilidades

import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.os.Build
import android.os.Environment
import java.util.*
import android.os.Environment.getExternalStorageDirectory
import android.util.Log
import android.view.View
import com.snappydb.SnappyDB
import com.snappydb.DB
import com.snappydb.DBFactory
import com.snappydb.SnappydbException


const val MIS_PREFERENCIAS = "aplicacionDB"
const val NOMBRE_DB = "aplicacionDB"
const val LENGUAJE_DE_PREFERENCIA = "languaje_preferences"
const val LENGUAJE_SELECCIONADO = "lenguaje_seleccionado"
const val LENGUAJE_ES = "es"
const val LENGUAJE_EN = "en"



fun selecionarIdioma(context: Context)
{

    var lenguaje = ""
    val snappydb = DBFactory.open(context, NOMBRE_DB)
    if (Locale.getDefault().language == "es")
    {
        snappydb.put(LENGUAJE_SELECCIONADO, LENGUAJE_EN);
        lenguaje = LENGUAJE_EN

    }else
    {
        snappydb.put(LENGUAJE_SELECCIONADO, LENGUAJE_ES);
        lenguaje = LENGUAJE_ES
    }

    val local= Locale(lenguaje)
    Locale.setDefault(local)
    val config = Configuration()
    if( Build.VERSION.SDK_INT >= Build.VERSION_CODES.N )
    {
        config.setLocale(local)
    }else{
        config.locale = local
    }
    context.resources.updateConfiguration(config, null)
    snappydb.close()




}