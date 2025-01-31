package usermanager.workflows

import aws.sdk.kotlin.runtime.auth.credentials.StaticCredentialsProvider
import aws.sdk.kotlin.services.dynamodb.DynamoDbClient
import aws.smithy.kotlin.runtime.net.url.Url
import net.pippah.usermanager.data.EmailAddress
import net.pippah.usermanager.data.PhoneNumber
import net.pippah.usermanager.data.User.Channel.ANY
import net.pippah.usermanager.data.User.Channel.LEFT
import net.pippah.usermanager.data.User.Gender.FEMALE
import net.pippah.usermanager.data.User.Position.FORWARD
import net.pippah.usermanager.data.User.Skill.EXPERIENCED
import net.pippah.usermanager.data.User.Skill.NEVER_PLAYED
import net.pippah.usermanager.data.User.Umpire.WRITTEN_TEST
import net.pippah.usermanager.data.User.UserId
import net.pippah.usermanager.exceptions.InvalidIdException
import net.pippah.usermanager.stores.DynamoUserDataStore
import net.pippah.usermanager.workflows.UserManagement
import org.junit.jupiter.api.assertThrows
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

class UserManagementTest {

    private val ddb = DynamoDbClient {
        endpointUrl = Url.parse("http://localhost:8000")
        region = "us-west-2"
        credentialsProvider = StaticCredentialsProvider {
            accessKeyId = "dummyAccess"
            secretAccessKey = "dummySecret"
            sessionToken = "dummySession"
        }
    }
    private val manager = UserManagement(DynamoUserDataStore(ddb))

    @Test
    fun `saves a user given a full set of details`() {
        val name = "Pippa Hillebrand"
        val phone = "0781234567"
        val email = "test@example.com"
        val dob = "13/10/1993"
        val gender = FEMALE
        val skill = EXPERIENCED
        val umpire = WRITTEN_TEST
        val channel = LEFT
        val position = FORWARD

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
        assertEquals(NEVER_PLAYED, user.skillLevel)
        assertEquals(ANY, user.channel)
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