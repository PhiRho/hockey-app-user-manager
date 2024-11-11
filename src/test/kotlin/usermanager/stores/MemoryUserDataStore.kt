package usermanager.stores

import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import net.pippah.usermanager.data.EmailAddress
import net.pippah.usermanager.data.PhoneNumber
import net.pippah.usermanager.data.User
import net.pippah.usermanager.data.User.UserId
import net.pippah.usermanager.exceptions.InvalidIdException
import net.pippah.usermanager.stores.UserDataStore
import java.util.logging.Level
import java.util.logging.Logger

class MemoryUserDataStore: UserDataStore {

    override fun saveUser(user: User) {
        log.log(Level.INFO, "Got a request to save user with ID ${user.id.id}")
        val jsonData = Json.encodeToString(user)
        log.log(Level.INFO, "User in JSON format is $jsonData")
        storageTable[user.id.id] = jsonData
    }

    override fun getUserById(id: UserId): User {
        log.log(Level.INFO, "Got a request to retrieve user with ID ${id.id}")
        val jsonData = storageTable[id.id]
        val usr = jsonData?.let { Json.decodeFromString<User>(jsonData) }
        if (usr == null) {
          throw InvalidIdException("Could not retrieve user with ID ${id.id}")
        }
        return usr
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
        private val storageTable = HashMap<String, String>()
        private val log = Logger.getLogger("MemoryUserDataStore")
    }
}