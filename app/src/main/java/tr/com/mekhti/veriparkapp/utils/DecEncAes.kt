package tr.com.mekhti.veriparkapp.utils
import java.util.*
import javax.crypto.Cipher
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec

class DecEncAes {

    companion object {


        fun encrypt(cipherText: ByteArray?): String {
            val cipher = Cipher.getInstance("AES/CBC/PKCS7Padding")
            val keySpec = SecretKeySpec(Base64.getDecoder().decode(HandshakeData.aesKey.toByteArray()), "AES")
            val ivSpec = IvParameterSpec(Base64.getDecoder().decode(HandshakeData.aesIV.toByteArray()))
            cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivSpec)
            val cipherTextEncypt = cipher.doFinal(cipherText)
            return Base64.getEncoder().encodeToString(cipherTextEncypt)
        }


        fun decrypt(cipherText: ByteArray?,): String {
            val cipher = Cipher.getInstance("AES/CBC/PKCS7Padding")
            val keySpec = SecretKeySpec(Base64.getDecoder().decode(HandshakeData.aesKey.toByteArray()), "AES")
            val ivSpec = IvParameterSpec(Base64.getDecoder().decode(HandshakeData.aesIV.toByteArray()))
            cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec)
            val decryptedText = cipher.doFinal(Base64.getDecoder().decode(cipherText))
            return String(decryptedText)
        }

    }
}