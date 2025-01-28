package usermanager.workflows

import net.pippah.usermanager.data.EmailAddress
import net.pippah.usermanager.data.PhoneNumber
import net.pippah.usermanager.data.User.Channel
import net.pippah.usermanager.data.User.Gender
import net.pippah.usermanager.data.User.Position
import net.pippah.usermanager.data.User.Skill
import net.pippah.usermanager.data.User.Umpire
import net.pippah.usermanager.data.User.UserId
import net.pippah.usermanager.exceptions.InvalidIdException
import net.pippah.usermanager.workflows.UserManagement
import org.junit.jupiter.api.assertThrows
import usermanager.stores.MemoryUserDataStore
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

class UserManagementTest {

    private val manager = UserManagement(MemoryUserDataStore())

    @Test
    fun `saves a user given a full set of details`() {
        val name = "Pippa Hillebrand"
        val phone = "0781234567"
        val email = "test@example.com"
        val dob = "13/10/1993"
        val gender = Gender.FEMALE
        val skill = Skill.EXPERIENCED
        val umpire = Umpire.WRITTEN_TEST
        val channel = Channel.LEFT
        val position = Position.FORWARD

        val id = manager.registerUser(name, phone, email, dob, gender, skill, umpire, channel, position)
        assertNotNull(id)
        val user = manager.getUserById(id)
        assertNotNull(user)
        assertEquals(name, user.name)
        assertEquals(dob, user.dob)
        // Verifies that the non-default fields are saved
        assertEquals(skill, user.skillLevel)
        assertEquals(channel, user.channel)
    }

    @Test
    fun `saves a user with default values`() {
        val name = "Pippa Hillebrand"
        val phone = "0781234567"
        val email = "test@example.com"
        val dob = "13/10/1993"

        val id = manager.registerUser(name, phone, email, dob)
        assertNotNull(id)
        val user = manager.getUserById(id)
        assertNotNull(user)
        assertEquals(name, user.name)
        assertEquals(dob, user.dob)
        // Verifies that default values are correctly applied
        assertEquals(Skill.NEVER_PLAYED, user.skillLevel)
        assertEquals(Channel.ANY, user.channel)
    }

    @Test
    fun `throws if the user does not exist`() {
        assertThrows<InvalidIdException> { manager.getUserById(UserId("faker")) }
    }

    @Test
    fun `retrieves a user by email address`() {
        val name = "Pippa Hillebrand"
        val phone = "0781234567"
        val email = "test@example.com"
        val dob = "13/10/1993"

        val id = manager.registerUser(name, phone, email, dob)
        assertNotNull(id)
        val user = manager.getUserByEmail(EmailAddress(email))
        assertNotNull(user)
        assertEquals(id, user.id)
        assertEquals(name, user.name)
        assertEquals(dob, user.dob)
    }

    @Test
    fun `retrieves a user by phone number`() {
        val name = "Pippa Hillebrand"
        val phone = "0781234567"
        val email = "test@example.com"
        val dob = "13/10/1993"

        val id = manager.registerUser(name, phone, email, dob)
        assertNotNull(id)
        val user = manager.getUserByPhoneNumber(PhoneNumber("+27", phone))
        assertNotNull(user)
        assertEquals(id, user.id)
        assertEquals(name, user.name)
        assertEquals(dob, user.dob)
    }
}