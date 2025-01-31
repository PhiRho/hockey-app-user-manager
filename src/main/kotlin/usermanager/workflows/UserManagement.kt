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
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.util.logging.Level
import java.util.logging.Logger

@RestController
@RequestMapping("/user/")
class UserManagement(private val store: UserDataStore) {
    /**
     * Creates a user in the management system
     */
    @PostMapping
    fun registerUser(@RequestParam("name") name: String,
                     @RequestParam("phone") phone: String,
                     @RequestParam("email") email: String,
                     @RequestParam("dob") dob: String
    ): UserId {
        return registerUser(name, phone, email, dob, null, null, null, null, null)
    }

    fun registerUser(name: String,
                     phone: String,
                     email: String,
                     dob: String,
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
            dob = dob,
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

    @GetMapping
    fun getUserById(@RequestParam("user-id") userId: UserId): User {
        return store.getUserById(userId)
    }

    @GetMapping
    fun getUserByEmail(@RequestParam("email") email: EmailAddress): User {
        return store.searchUserByEmail(email)
    }

    @GetMapping
    fun getUserByPhoneNumber(@RequestParam("phone") phone: PhoneNumber): User {
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