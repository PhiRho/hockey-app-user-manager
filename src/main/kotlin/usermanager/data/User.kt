package net.pippah.usermanager.data

import kotlinx.serialization.Serializable
import java.util.UUID
import java.util.logging.Logger


@Serializable
data class User(
    val name: String,
    val email: EmailAddress,
    val phone: PhoneNumber,
    /* There are age limits (above and below) for many teams*/
    val dob: String,
    // TODO val password: ???,
) {
    var id: UserId = genNewUserId()
    /* Gender may be required for the purposes of entry into a league*/
    var gender: Gender = Gender.OTHER
    var photoLocation: String? = null
    var skillLevel: Skill = Skill.NEVER_PLAYED
    var umpireLevel: Umpire = Umpire.NEVER_UMPED
    var channel: Channel = Channel.ANY
    var position: Position = Position.ANY_FIELD
    var adminOptIn: Boolean = false
    var clubs: List<Club.ClubId> = emptyList()

    enum class Gender(val gender: String) {
        MALE("male"),
        FEMALE("female"),
        NON_BINARY("non-binary"),
        OTHER("other")
    }

    enum class Skill(val skill: Int) {
        NEVER_PLAYED(0),
        BASIC_SKILL(1),
        EXPERIENCED(2),
        SEMI_PROFESSIONAL(3),
        PROFESSIONAL(4),
    }

    enum class Umpire(val level: Int) {
        NEVER_UMPED(-1),
        WRITTEN_TEST(0),
        FIH_ONE(1),
        FIH_TWO(2),
        FIH_THREE(3),
        FIH_INTERNATIONAL(4),
    }

    enum class Channel(val channel: String) {
        LEFT("left"),
        RIGHT("right"),
        CENTER("center"),
        ANY("any")
    }

    enum class Position(val position: String) {
        FORWARD("forward"),
        LINK("link"),
        DEFENSE("defender"),
        GOALIE("goal keeper"),
        ANY_FIELD("outfield")
    }

    @Serializable
    data class UserId(val id: String)

    companion object {
        private val log = Logger.getLogger("User")
    }
}

/**
 * Don't tie the unique user ID irrevocably to email or name. They may need to be unique for search purposes, but there
 * is no reason a user should not be allowed to change that detail.
 */
private fun genNewUserId(): User.UserId {
    return User.UserId(UUID.randomUUID().toString())
}
