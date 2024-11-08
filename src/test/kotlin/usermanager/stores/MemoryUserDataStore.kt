package usermanager.stores

import net.pippah.usermanager.data.EmailAddress
import net.pippah.usermanager.data.PhoneNumber
import net.pippah.usermanager.data.User
import net.pippah.usermanager.data.User.UserId
import net.pippah.usermanager.stores.UserDataStore
import java.util.logging.Level
import java.util.logging.Logger

class MemoryUserDataStore: UserDataStore {
    override fun saveUser(user: User) {
        log.log(Level.INFO, "Got a request to save $user")
    }

    override fun getUserById(id: UserId): User {
        TODO("Not yet implemented")
    }

    override fun searchUserByEmail(email: EmailAddress): User {
        TODO("Not yet implemented")
    }

    override fun searchUserByPhone(phone: PhoneNumber): User {
        TODO("Not yet implemented")
    }

    override fun deleteUser(id: UserId) {
        TODO("Not yet implemented")
    }

    companion object {
        val log = Logger.getLogger("MemoryUserDataStore")
    }
}