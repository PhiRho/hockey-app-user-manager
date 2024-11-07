package net.pippah.usermanager.workflows

import net.pippah.usermanager.data.Club.ClubId
import net.pippah.usermanager.data.EmailAddress
import net.pippah.usermanager.data.PhoneNumber
import net.pippah.usermanager.data.Team.TeamId
import net.pippah.usermanager.data.User
import net.pippah.usermanager.data.User.UserId

class UserManagement {
    /**
     * Creates a user in the management system
     */
    fun registerUser(name: String ) {
        TODO("Create a user, and save it to remote")
    }

    fun deleteUser(user: UserId) {
        TODO("Remove records related to user and remove from all teams, clubs, etc")
    }

    fun uploadUserImage() {
        TODO()
    }

    fun optInAsAdmin(user: UserId) {

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


}