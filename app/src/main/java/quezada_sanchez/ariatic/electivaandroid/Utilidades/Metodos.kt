package quezada_sanchez.ariatic.electivaandroid.Utilidades

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.support.v4.content.ContextCompat.getSystemService
import android.widget.Toast
import android.net.NetworkInfo
import android.view.View


fun showToast(context: Context,texto:String,opcion:Int)
{
    if (opcion==0){
        Toast.makeText(context,
                texto,
                Toast.LENGTH_SHORT).show()
    }else{
        Toast.makeText(context,
                texto,
                Toast.LENGTH_LONG).show()
    }
}

fun validarRedMovil(context: Context): Boolean
{
    val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val networkInfo = connectivityManager.activeNetworkInfo
    return networkInfo != null && networkInfo.type == ConnectivityManager.TYPE_MOBILE
}


fun redActiva(context: Context):Boolean
{
    val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE)
    return if (connectivityManager is ConnectivityManager)
    {
        try {

            val networkInfo = connectivityManager.activeNetworkInfo
            networkInfo!=null && networkInfo.isAvailable && networkInfo.isConnected

        }
        catch (e: IllegalStateException)
        {return false}
    }else false
}

