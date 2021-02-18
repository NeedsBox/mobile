package tech.needsbox.mobile.api.model.services

import android.os.Parcel
import android.os.Parcelable
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import tech.needsbox.mobile.api.model.misc.Category
import tech.needsbox.mobile.api.model.users.NeedsBoxUser
import java.time.Instant

data class Service(
    val id: Int,
    val title: String,
    val description: String,
    @JsonDeserialize(using = CategoryByIdDeserializer::class) val category: Category,
    val location: String,
    @JsonProperty("created_at") val createdAt: Instant,
    val user: NeedsBoxUser
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readParcelable<Category>(ClassLoader.getSystemClassLoader())!!,
        parcel.readString()!!,
        Instant.ofEpochSecond(parcel.readLong()),
        parcel.readParcelable<NeedsBoxUser>(ClassLoader.getSystemClassLoader())!!
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(title)
        parcel.writeString(description)
        parcel.writeParcelable(category, flags)
        parcel.writeString(location)
        parcel.writeLong(createdAt.epochSecond)
        parcel.writeParcelable(user, flags)

    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Service> {
        override fun createFromParcel(parcel: Parcel): Service {
            return Service(parcel)
        }

        override fun newArray(size: Int): Array<Service?> {
            return arrayOfNulls(size)
        }
    }
}