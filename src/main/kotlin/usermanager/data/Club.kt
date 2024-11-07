package net.pippah.usermanager.data

import java.util.UUID

data class Club(val id: ClubId = genClubId()) {
    data class ClubId(val id: String)
}

private fun genClubId(): Club.ClubId {
    return Club.ClubId(UUID.randomUUID().toString())
}
