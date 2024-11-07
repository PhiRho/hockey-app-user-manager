package net.pippah.usermanager.data

import net.pippah.usermanager.data.League.LeagueId
import java.util.UUID

data class League(val id: LeagueId = genLeagueId()) {
    data class LeagueId(val id: String)
}

private fun genLeagueId(): LeagueId {
    return LeagueId(UUID.randomUUID().toString())
}
