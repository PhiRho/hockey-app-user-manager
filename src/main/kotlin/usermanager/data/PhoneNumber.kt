package net.pippah.usermanager.data

import kotlinx.serialization.Serializable

/**
 * Phone numbers have different validations based on the country they are from. By having a specific class for them
 * from the start I get to insert whatever validations I like later on.
 */
@Serializable
data class PhoneNumber(val countryCode: String = "+27", val number: String)
