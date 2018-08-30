package vo

import android.os.Parcel
import android.os.Parcelable
import java.util.*

class Estudiante(var fechaNacimiento: Date,
                 var nombre: String, var esHijoUnico: Boolean,
                 var notas: FloatArray?) : Parcelable {

    var amigos : MutableList<Estudiante> = mutableListOf()

    constructor(parcel: Parcel) : this(
            parcel.readSerializable() as Date,
            parcel.readString(),
            parcel.readByte() != 0.toByte(), parcel.createFloatArray()) {
        amigos = parcel.createTypedArrayList(Estudiante.CREATOR)
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeSerializable(fechaNacimiento)
        parcel.writeString(nombre)
        parcel.writeByte(if (esHijoUnico) 1 else 0)
        parcel.writeFloatArray(notas)
        parcel.writeTypedList(amigos)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Estudiante> {
        override fun createFromParcel(parcel: Parcel): Estudiante {
            return Estudiante(parcel)
        }

        override fun newArray(size: Int): Array<Estudiante?> {
            return arrayOfNulls(size)
        }
    }
}