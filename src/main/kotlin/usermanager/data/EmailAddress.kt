package net.pippah.usermanager.data

data class EmailAddress(val email: String) {
    private val parts = email.split('@')
    val user = parts[0]
    val domain = parts[1]
}
