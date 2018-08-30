package quezada_sanchez.ariatic.electivaandroid.Utilidades

import android.content.Context
import android.database.sqlite.SQLiteOpenHelper
import android.database.sqlite.SQLiteDatabase

class ConexionSQLiteHelper(context: Context) : SQLiteOpenHelper(context, "baseDatos", null, 1)
{

    val crearUsuario = "CREATE TABLE usuarios(id Integer, nombre TEXT, telefono TEXT)"


    override fun onCreate(db: SQLiteDatabase)
    {
        db.execSQL(crearUsuario)
    }


    override fun onUpgrade(db: SQLiteDatabase?, versionAntigua: Int, versionNueva: Int)
    {
        if (db != null) {
            db.execSQL("DROP TABLE IF EXISTS usuarios")
            onCreate(db)
        }
    }






}