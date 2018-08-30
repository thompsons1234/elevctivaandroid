package quezada_sanchez.ariatic.electivaandroid.Actividades

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import quezada_sanchez.ariatic.electivaandroid.R

class RegistroActivity : AppCompatActivity()
{


    lateinit var editText_nombre: EditText
    lateinit var editText_apellido: EditText
    lateinit var editText_email: EditText
    lateinit var editText_pass: EditText
    lateinit var progressBar: ProgressBar
    lateinit var dbReference: DatabaseReference
    lateinit var database: FirebaseDatabase
    lateinit var auth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)
        editText_nombre=findViewById(R.id.registro_editext_nombre)
        editText_apellido=findViewById(R.id.registro_editText_apellido)
        editText_email=findViewById(R.id.registro_editext_nombre)
        editText_pass=findViewById(R.id.registro_editText_pass)
        progressBar= findViewById(R.id.progressBar)
        database= FirebaseDatabase.getInstance()
        auth=FirebaseAuth.getInstance()
        dbReference = database.reference.child("Usuario")

    }

    fun registro(view:View)
    {
      crearUsuario()
    }

    private fun action()
    {
        startActivity(Intent(this, MainActivity::class.java))
    }

    private fun crearUsuario()
    {
        val nombre:String = editText_nombre.text.toString()
        val apellido:String = editText_apellido.text.toString()
        val email:String = editText_email.text.toString()
        val pass:String = editText_pass.text.toString()

        if (!TextUtils.isEmpty(nombre) and !TextUtils.isEmpty(apellido)
                and !TextUtils.isEmpty(email) and !TextUtils.isEmpty(pass))
        {

            progressBar.visibility= View.VISIBLE

            auth.createUserWithEmailAndPassword(email,pass)
                    .addOnCompleteListener(this)
                    {
                        task ->
                        if (task.isComplete)
                        {
                            val user=auth.currentUser
                            val email_verificado:Boolean = user?.isEmailVerified ?: false

                            if (user!=null && email_verificado)
                            {
                                val userDb = database.reference.child(user?.uid!!)
                                userDb.child("Nombre").setValue(nombre)
                                userDb.child("Apellido").setValue(apellido)
                            }else
                            {
                                Toast.makeText(this, getString(R.string.no_create) + user,Toast.LENGTH_SHORT).show()
                                progressBar.visibility = View.GONE
                            }

                        }else{
                            Toast.makeText(this,getString(R.string.problema_de_internet),Toast.LENGTH_SHORT).show()
                            progressBar.visibility = View.GONE
                        }


                    }

        }else
        {
            Toast.makeText(this, R.string.campos_vacios,Toast.LENGTH_SHORT).show()
        }
    }


    private fun verificarEmail(user:FirebaseUser?){
        user?.sendEmailVerification()
                ?.addOnCompleteListener(this){
                    task ->
                    progressBar.visibility =View.GONE
                    if (task.isSuccessful)
                    {
                        Toast.makeText(this,getString(R.string.correoEnviado),Toast.LENGTH_SHORT).show()
                    }else
                    {
                        Toast.makeText(this,getString(R.string.ErrorEnvioCorreo),Toast.LENGTH_SHORT).show()
                    }
                }
    }
}
