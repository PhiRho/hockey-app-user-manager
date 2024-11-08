package net.pippah.usermanager.workflows

import net.pippah.usermanager.data.Club.ClubId
import net.pippah.usermanager.data.EmailAddress
import net.pippah.usermanager.data.PhoneNumber
import net.pippah.usermanager.data.Team.TeamId
import net.pippah.usermanager.data.User
import net.pippah.usermanager.data.User.Channel
import net.pippah.usermanager.data.User.Gender
import net.pippah.usermanager.data.User.Position
import net.pippah.usermanager.data.User.Skill
import net.pippah.usermanager.data.User.Umpire
import net.pippah.usermanager.data.User.UserId
import net.pippah.usermanager.stores.UserDataStore
import java.util.logging.Level
import java.util.logging.Logger

class UserManagement(val store: UserDataStore) {
    /**
     * Creates a user in the management system
     */
    fun registerUser(name: String,
                     phone: String,
                     email: String,
                     age: Int,
                     gender: Gender?,
                     skill: Skill?,
                     umpire: Umpire?,
                     channel: Channel?,
                     position: Position?
    ) {
        val emailAddress = EmailAddress(email)
        val phoneNumber = PhoneNumber(countryCode = "+27", number = phone)
        val user = User(
            name = name,
            email = emailAddress,
            phone = phoneNumber,
            age = age,
            gender = gender,
            skillLevel = skill,
            umpireLevel = umpire,
            channel = channel,
            position = position,
            id = null,
            photoLocation = null,
            adminOptIn = false,
            clubs = null
        )

        try {
            store.saveUser(user)
        } catch (e: Exception) {
            log.log(Level.SEVERE, "Unable to save user", e)
        }
    }

    fun deleteUser(user: UserId) {
        TODO("Remove records related to user and remove from all teams, clubs, etc")
    }

    fun uploadUserImage() {
        TODO()
    }

    fun optInAsAdmin(user: UserId) {
        TODO()
    }

    fun getUserById(userId: UserId): User {
        TODO("Return the full details of the user")
    }

    fun getUserByEmail(email: EmailAddress): User {
        TODO("Search for the user by their email address")
    }

    fun getUserByPhoneNumber(phone: PhoneNumber): User {
        TODO("Search for the user by their phone number")
    }

    //===============
    // LIST - I may choose to move these later
    //===============
    fun listUsers(): List<User> {
        TODO("Pull a complete (paginated?) list of users")
    }

    fun listUsersInClub(club: ClubId): List<User> {
        TODO("Return a list of users who are members of a specific club")
    }

    fun listUsersInTeam(team: TeamId): List<User> {
        TODO("Return a list of the users registered in the Team")
    }

    companion object {
        val log = Logger.getLogger("UserManagement")
    }

}