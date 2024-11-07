package net.pippah.usermanager.data

/**
 * Phone numbers have different validations based on the country they are from. By having a specific class for them
 * from the start I get to insert whatever validations I like later on.
 */
data class PhoneNumber(val countryCode: String = "+27", val number: String)
