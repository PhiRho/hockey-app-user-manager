package net.pippah.data

import java.util.*

data class User(
    val name: String,
    val email: EmailAddress?,
    val phone: PhoneNumber?,
    /* There are age limits (above and below) for many teams*/
    val age: Int,
    /* Gender may be required for the purposes of entry into a league*/
    val gender: Gender?
) {
    val id: String = genNewUserId()

    enum class Gender(val gender: String) {
        MALE("male"),
        FEMALE("female"),
        NON_BINARY("non-binary"),
        OTHER("other")
    }

    private fun genNewUserId(): String {
        return UUID.randomUUID().toString()
    }
}
