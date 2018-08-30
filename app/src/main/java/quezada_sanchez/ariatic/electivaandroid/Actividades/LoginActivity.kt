package quezada_sanchez.ariatic.electivaandroid.Actividades

import android.app.ProgressDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Base64
import android.util.Log
import android.view.View
import android.widget.Toast
import com.facebook.*
import quezada_sanchez.ariatic.electivaandroid.R
import quezada_sanchez.ariatic.electivaandroid.Utilidades.redActiva
import quezada_sanchez.ariatic.electivaandroid.Utilidades.selecionarIdioma
import quezada_sanchez.ariatic.electivaandroid.Utilidades.showToast
import com.facebook.login.LoginResult
import com.facebook.login.widget.LoginButton
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

class LoginActivity : AppCompatActivity() {
    internal var callbackManager: CallbackManager? = null
    lateinit var loginButton: LoginButton



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.AppTheme)
        setContentView(R.layout.activity_login)
        loginButton =  findViewById(R.id.login_button_fb)
        if (!redActiva(this))
        {
            showToast(this, getString(R.string.advertencia_red_no_activa), Toast.LENGTH_LONG)
        }

        facebook_login()

    }


    fun registroActivity(view: View)
    {
        startActivity(Intent(this, RegistroActivity::class.java))
    }

    fun login(view: View)
    {
        if (redActiva(this))
        {
            startActivity(Intent(this, RegistroActivity::class.java))
        } else
        {
            showToast(this, getString(R.string.red_no_activa), Toast.LENGTH_SHORT)
        }
    }

    fun cambiarIdioma(view: View)
    {
        selecionarIdioma(this)
        val intent = this.intent
        intent.flags = (Intent.FLAG_ACTIVITY_CLEAR_TOP or
                Intent.FLAG_ACTIVITY_NEW_TASK)
        this.finish()
        this.startActivity(intent)
    }

    fun facebook_login() {
        val intent = Intent(this, MainActivity::class.java)
        callbackManager = CallbackManager.Factory.create()
        if (!redActiva(this))
        {
            showToast(this, getString(R.string.advertencia_red_no_activa), Toast.LENGTH_LONG)
        }else
        {

            loginButton.registerCallback(callbackManager, object : FacebookCallback<LoginResult> {
                override fun onSuccess(loginResult: LoginResult)
                {
                    val request = GraphRequest.newMeRequest(loginResult.accessToken) { `object`, response ->
                        val accestoken = loginResult.accessToken.token.toString()
                        startActivity(intent)
                    }
                    request.executeAsync()
                }

                override fun onCancel()
                {

                    showToast(this@LoginActivity,"Cancelar",Toast.LENGTH_LONG)
                }

                override fun onError(error: FacebookException)
                {


                    showToast(this@LoginActivity,"Errror de conexion",Toast.LENGTH_LONG)
                }
            })
        }


    }




    fun keyhashes() {
        try {
            val info = packageManager.getPackageInfo(
                    "quezada_sanchez.ariatic.electivaandroid",
                    PackageManager.GET_SIGNATURES)
            for (signature in info.signatures) {
                val md = MessageDigest.getInstance("SHA")
                md.update(signature.toByteArray())
                Log.v("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT))
            }
        } catch (e: PackageManager.NameNotFoundException) {

            Log.v("KeyHash:", "error 0")
        } catch (e: NoSuchAlgorithmException) {
            Log.v("KeyHash:", "error 1")
        }

    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        //Respuesta para facebook
        callbackManager?.onActivityResult(requestCode, resultCode, data)



    }





}
