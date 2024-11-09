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
        val userMap = user.getUserMap()
        log.log(Level.INFO, "user data is $userMap")
        storageTable[user.id.id] = userMap
    }

    override fun getUserById(id: UserId): User {
        TODO("Implementation incomplete")
//        val userData = storageTable[id.id]
//
//        return User.fromMap(userData)
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
        private val storageTable = HashMap<String, HashMap<String, Any>>()
        private val log = Logger.getLogger("MemoryUserDataStore")
    }
}