package net.pippah.usermanager.data

import kotlinx.serialization.Serializable
import java.util.UUID

@Serializable
data class Club(val id: ClubId = genClubId()) {
    @Serializable
    data class ClubId(val id: String)
}

private fun genClubId(): Club.ClubId {
    return Club.ClubId(UUID.randomUUID().toString())
}
