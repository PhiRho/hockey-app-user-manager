package net.pippah.usermanager.data

import net.pippah.usermanager.data.Team.TeamId
import java.util.UUID

data class Team(val id: TeamId = genTeamId()) {
    data class TeamId(val id: String)
}

private fun genTeamId(): TeamId {
    return TeamId(UUID.randomUUID().toString())
}
