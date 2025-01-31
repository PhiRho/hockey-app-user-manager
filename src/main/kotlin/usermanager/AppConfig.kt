package usermanager

import aws.sdk.kotlin.services.dynamodb.DynamoDbClient
import net.pippah.usermanager.stores.DynamoUserDataStore
import net.pippah.usermanager.stores.UserDataStore
import net.pippah.usermanager.workflows.UserManagement
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class AppConfig {

    @Bean
    fun userManager(store: UserDataStore): UserManagement {
        return UserManagement(store)
    }

    @Bean
    fun dataStore(@Autowired client: DynamoDbClient): UserDataStore {
        return DynamoUserDataStore(client)
    }

    @Bean
    fun client(): DynamoDbClient {
        return DynamoDbClient { region = "eu-west-1" }
    }
}
