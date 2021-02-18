package tech.needsbox.mobile.api.model.users

import android.os.Parcel
import android.os.Parcelable
import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty

data class NeedsBoxUser(
    val username: String,
    val name: String,
    val biography: String?,
    val contact: String?,
    val website: String?,
    @JsonProperty("account_type") val accountType: AccountType? = AccountType.PES,

    //Only used for account creation
    val email: String?,
    val password: String?,

    @JsonIgnore
    var token: String? = null
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        AccountType.valueOf(parcel.readString()!!),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(username)
        parcel.writeString(name)
        parcel.writeString(biography)
        parcel.writeString(contact)
        parcel.writeString(website)
        parcel.writeString(accountType!!.name)
        parcel.writeString(email)
        parcel.writeString(password)
        parcel.writeString(token)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<NeedsBoxUser> {
        override fun createFromParcel(parcel: Parcel): NeedsBoxUser {
            return NeedsBoxUser(parcel)
        }

        override fun newArray(size: Int): Array<NeedsBoxUser?> {
            return arrayOfNulls(size)
        }
    }
}