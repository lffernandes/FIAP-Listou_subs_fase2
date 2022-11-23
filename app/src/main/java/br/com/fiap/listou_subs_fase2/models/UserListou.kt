package br.com.fiap.listou_subs_fase2.models

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class UserListou(
    @SerializedName("usuario")
    @Expose
    var usuario: String?,

    @SerializedName("senha")
    @Expose
    var senha: String?
) : Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString()
    ) {
        usuario = parcel.readString()
        senha = parcel.readString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(usuario)
        parcel.writeString(senha)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<UserListou> {
        override fun createFromParcel(parcel: Parcel): UserListou {
            return UserListou(parcel)
        }

        override fun newArray(size: Int): Array<UserListou?> {
            return arrayOfNulls(size)
        }
    }

}