package net.pippah.usermanager.stores

import aws.sdk.kotlin.services.dynamodb.DynamoDbClient
import aws.sdk.kotlin.services.dynamodb.describeTable
import aws.sdk.kotlin.services.dynamodb.model.AttributeDefinition
import aws.sdk.kotlin.services.dynamodb.model.AttributeValue
import aws.sdk.kotlin.services.dynamodb.model.CreateTableRequest
import aws.sdk.kotlin.services.dynamodb.model.KeySchemaElement
import aws.sdk.kotlin.services.dynamodb.model.KeyType
import aws.sdk.kotlin.services.dynamodb.model.ScalarAttributeType
import aws.sdk.kotlin.services.dynamodb.model.TableStatus
import aws.sdk.kotlin.services.dynamodb.putItem
import aws.sdk.kotlin.services.dynamodb.waiters.waitUntilTableExists
import kotlinx.coroutines.runBlocking
import net.pippah.usermanager.data.EmailAddress
import net.pippah.usermanager.data.PhoneNumber
import net.pippah.usermanager.data.User
import net.pippah.usermanager.data.User.UserId

class DynamoUserDataStore(private val client: DynamoDbClient) : UserDataStore{

    suspend fun setupTables() {
        val userTableName = "users"
        val userAttributes = listOf(
            AttributeDefinition{
                attributeName = "id"
                attributeType = ScalarAttributeType.S
            },
            AttributeDefinition{
                attributeName = "name"
                attributeType = ScalarAttributeType.S
            },
            AttributeDefinition{
                attributeName = "email"
                attributeType = ScalarAttributeType.S
            },
            AttributeDefinition{
                attributeName = "phone"
                attributeType = ScalarAttributeType.S
            },
            AttributeDefinition{
                attributeName = "dob"
                attributeType = ScalarAttributeType.S
            },
        )

        val createUsersTableReq = CreateTableRequest {
            attributeDefinitions = userAttributes
            keySchema = listOf(KeySchemaElement{
                attributeName = "id"
                keyType = KeyType.Hash
            })
            tableName = userTableName
        }

        client.use { client ->
            client.createTable(createUsersTableReq)
            client.waitUntilTableExists { tableName = userTableName }
        }

    }

    private suspend fun ensureTable(name: String) {
        client.use { client ->
            val response = client.describeTable {
                tableName = name
            }
            if (response.table?.tableStatus != TableStatus.Active) {
                setupTables()
            }
        }
    }

    override fun saveUser(user: User) {
        runBlocking {
            ensureTable("users")

            val userValues = mutableMapOf<String, AttributeValue>()
            userValues["name"] = AttributeValue.S(user.name)
            userValues["email"] = AttributeValue.S(user.email.email)
            userValues["phone"] = AttributeValue.S(user.phone.number)
            userValues["dob"] = AttributeValue.S(user.dob)

            client.use { client ->
                client.putItem {
                    tableName = "users"
                    item = userValues
                }
            }
        }
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
}