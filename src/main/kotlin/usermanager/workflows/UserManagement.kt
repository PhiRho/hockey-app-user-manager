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

class UserManagement(private val store: UserDataStore) {
    /**
     * Creates a user in the management system
     */
    fun registerUser(name: String,
                     phone: String,
                     email: String,
                     age: Int
    ): UserId {
        return registerUser(name, phone, email, age, null, null, null, null, null)
    }

    fun registerUser(name: String,
                     phone: String,
                     email: String,
                     age: Int,
                     gender: Gender?,
                     skill: Skill?,
                     umpire: Umpire?,
                     channel: Channel?,
                     position: Position?
    ): UserId {
        val emailAddress = EmailAddress(email)
        val phoneNumber = PhoneNumber(countryCode = "+27", number = phone)
        val user = User(
            name = name,
            email = emailAddress,
            phone = phoneNumber,
            age = age,
        )
        gender?.let { user.gender = gender }
        skill?.let { user.skillLevel = skill }
        umpire?.let { user.umpireLevel = umpire}
        channel?.let { user.channel = channel }
        position?.let { user.position = position }

        try {
            store.saveUser(user)
        } catch (e: Exception) {
            log.log(Level.SEVERE, "Unable to save user", e)
        }
        return user.id
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
        return store.getUserById(userId)
    }

    fun getUserByEmail(email: EmailAddress): User {
        return store.searchUserByEmail(email)
    }

    fun getUserByPhoneNumber(phone: PhoneNumber): User {
        return store.searchUserByPhone(phone)
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