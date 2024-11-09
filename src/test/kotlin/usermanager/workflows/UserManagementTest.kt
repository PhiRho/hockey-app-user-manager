package usermanager.workflows

import net.pippah.usermanager.data.User.Channel
import net.pippah.usermanager.data.User.Gender
import net.pippah.usermanager.data.User.Position
import net.pippah.usermanager.data.User.Skill
import net.pippah.usermanager.data.User.Umpire
import net.pippah.usermanager.workflows.UserManagement
import usermanager.stores.MemoryUserDataStore
import kotlin.test.Test

class UserManagementTest {

    private val manager = UserManagement(MemoryUserDataStore())

    @Test
    fun `saves a user given a full set of details`() {
        val name = "Pippa Hillebrand"
        val phone = "0781234567"
        val email = "test@example.com"
        val age = 30
        val gender = Gender.FEMALE
        val skill = Skill.EXPERIENCED
        val umpire = Umpire.WRITTEN_TEST
        val channel = Channel.ANY
        val position = Position.ANY_FIELD

        manager.registerUser(name, phone, email, age, gender, skill, umpire, channel, position)
    }

    @Test
    fun `saves a user with default values`() {
        val name = "Pippa Hillebrand"
        val phone = "0781234567"
        val email = "test@example.com"
        val age = 30

        manager.registerUser(name, phone, email, age)
    }
}