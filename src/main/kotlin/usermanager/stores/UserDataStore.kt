package net.pippah.usermanager.stores

import net.pippah.usermanager.data.EmailAddress
import net.pippah.usermanager.data.PhoneNumber
import net.pippah.usermanager.data.User
import net.pippah.usermanager.data.User.UserId

interface UserDataStore {
    // Write
    fun saveUser(user: User)

    // Read
    fun getUserById(id: UserId): User
    fun searchUserByEmail(email: EmailAddress): User
    fun searchUserByPhone(phone: PhoneNumber): User

    // Delete
    fun deleteUser(id: UserId)
}