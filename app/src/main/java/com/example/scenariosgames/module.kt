// Code Kotlin simulant deux modules de chiffrement de données sensibles

import java.util.Base64
import javax.crypto.Cipher
import javax.crypto.KeyGenerator
import javax.crypto.SecretKey
import javax.crypto.spec.SecretKeySpec

// Module 1 : Chiffrement AES
object AesEncryptionModule {
    private const val ALGORITHM = "AES"

    fun generateKey(): SecretKey {
        val keyGen = KeyGenerator.getInstance(ALGORITHM)
        keyGen.init(128)
        return keyGen.generateKey()
    }

    fun encrypt(data: String, secretKey: SecretKey): String {
        val cipher = Cipher.getInstance(ALGORITHM)
        cipher.init(Cipher.ENCRYPT_MODE, secretKey)
        val encrypted = cipher.doFinal(data.toByteArray())
        return Base64.getEncoder().encodeToString(encrypted)
    }

    fun decrypt(encryptedData: String, secretKey: SecretKey): String {
        val cipher = Cipher.getInstance(ALGORITHM)
        cipher.init(Cipher.DECRYPT_MODE, secretKey)
        val decodedBytes = Base64.getDecoder().decode(encryptedData)
        val decrypted = cipher.doFinal(decodedBytes)
        return String(decrypted)
    }
}

// Module 2 : Chiffrement Base64 (pour des cas simples ou à fins de démonstration)
object Base64EncryptionModule {
    fun encode(data: String): String {
        return Base64.getEncoder().encodeToString(data.toByteArray())
    }

    fun decode(encodedData: String): String {
        return String(Base64.getDecoder().decode(encodedData))
    }
}

// Exemple d'utilisation
fun main() {
    val originalText = "Donnée Sensible"

    // Utilisation du module AES
    val secretKey = AesEncryptionModule.generateKey()
    val encryptedAES = AesEncryptionModule.encrypt(originalText, secretKey)
    val decryptedAES = AesEncryptionModule.decrypt(encryptedAES, secretKey)

    println("[AES] Original: $originalText")
    println("[AES] Encrypted: $encryptedAES")
    println("[AES] Decrypted: $decryptedAES")

    // Utilisation du module Base64
    val encodedBase64 = Base64EncryptionModule.encode(originalText)
    val decodedBase64 = Base64EncryptionModule.decode(encodedBase64)

    println("[Base64] Encoded: $encodedBase64")
    println("[Base64] Decoded: $decodedBase64")
} 
