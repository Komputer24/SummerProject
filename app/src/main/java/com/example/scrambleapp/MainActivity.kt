package com.example.scrambleapp

import android.R
import android.os.Bundle
import android.widget.Button
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import android.util.Base64
import java.security.Key
import javax.crypto.Cipher
import javax.crypto.KeyGenerator
import javax.crypto.SecretKey
import javax.crypto.spec.SecretKeySpec

/*ject AESUtil {

    private const val ALGORITHM = "AES"
    private const val KEY_SIZE = 256

    // Generate a new AES key
    @Throws(Exception::class)
    fun generateKey(): SecretKey {
        val keyGen = KeyGenerator.getInstance(ALGORITHM)
        keyGen.init(KEY_SIZE)
        return keyGen.generateKey()
    }

    // Encrypt a plain text using the AES key
    @Throws(Exception::class)
    fun encrypt(data: String, key: Key): String {
        val cipher = Cipher.getInstance(ALGORITHM)
        cipher.init(Cipher.ENCRYPT_MODE, key)
        val encryptedBytes = cipher.doFinal(data.toByteArray(Charsets.UTF_8))
        return Base64.encodeToString(encryptedBytes, Base64.DEFAULT)
    }

    // Decrypt an encrypted text using the AES key
    @Throws(Exception::class)
    fun decrypt(encryptedData: String, key: Key): String {
        val cipher = Cipher.getInstance(ALGORITHM)
        cipher.init(Cipher.DECRYPT_MODE, key)
        val decodedBytes = Base64.decode(encryptedData, Base64.DEFAULT)
        val decryptedBytes = cipher.doFinal(decodedBytes)
        return String(decryptedBytes, Charsets.UTF_8)
    }

    // Convert a Base64 encoded string into a SecretKey object
    fun getKeyFromString(keyString: String): SecretKey {
        val decodedKey = Base64.decode(keyString, Base64.DEFAULT)
        return SecretKeySpec(decodedKey, 0, decodedKey.size, ALGORITHM)
    }

    // Convert a SecretKey object into a Base64 encoded string
    fun getStringFromKey(key: SecretKey): String {
        return Base64.encodeToString(key.encoded, Base64.DEFAULT)
    }
}
*/
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Column{
                MessageCard("ScramblerApp")
                Text("You can encrypt your texts Here!")
                Image(
                    painter = painterResource(R.drawable.encryption_pic),
                    contentDescription = "Contact profile picture",
                )
            }
        }
    }
}

@Composable
fun MessageCard(name: String) {
    Text(text = "Welcome to the $name!")
}

@Preview
@Composable
fun PreviewMessageCard() {
    Column{
        MessageCard("ScramblerApp")
        Text("You can encrypt your texts Here!")
        Image(
            painter = painterResource(R.drawable.encryption_pic),
            contentDescription = "Encrypt Pic",
        )
        /*Button(onClick= { /* Handle button click */ }) {
            Text("Click me")
        }*/
    }
}