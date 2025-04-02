import okhttp3.CertificatePinner
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.security.cert.CertificateException
import java.util.concurrent.TimeUnit
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager

class SecureNetworkClient {
    
    companion object {
        private const val BASE_URL = "https://api.yourgame.com/v1/"
        private const val CONNECTION_TIMEOUT = 30L
        private const val READ_TIMEOUT = 30L
        private const val WRITE_TIMEOUT = 30L
        
        // These hash values must match the ones in network_security_config.xml
        // And must be the hash of your server's public key
        private const val PRIMARY_PIN = "sha256/r8udi/Ucs1c4rh6RIJks0CBC9sJRQQUFGZkbNj2D4pk="
        private const val BACKUP_PIN = "sha256/backup-pin-hash-here"
    }
    
    fun createSecureOkHttpClient(): OkHttpClient {
        val certificatePinner = CertificatePinner.Builder()
            .add("api.yourgame.com", PRIMARY_PIN)
            .add("api.yourgame.com", BACKUP_PIN)
            .build()
            
        return OkHttpClient.Builder()
            .connectTimeout(CONNECTION_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
            .certificatePinner(certificatePinner)
            .addInterceptor { chain ->
                // Add standard headers or authentication tokens
                val request = chain.request().newBuilder()
                    .addHeader("Content-Type", "application/json")
                    .addHeader("Accept", "application/json")
                    .addHeader("X-App-Version", BuildConfig.VERSION_NAME)
                    .build()
                    
                val response = chain.proceed(request)
                
                // Validate response before returning it
                if (!validateResponse(response)) {
                    throw SecurityException("Invalid response received from server")
                }
                
                response
            }
            .build()
    }
    
    // 4. CREATE RETROFIT SERVICE WITH SECURE CLIENT
    fun <T> createApiService(serviceClass: Class<T>): T {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(createSecureOkHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            
        return retrofit.create(serviceClass)
    }
    
    // 5. RESPONSE VALIDATION FUNCTION
    private fun validateResponse(response: Response): Boolean {
        // 1. Check for successful status code
        if (!response.isSuccessful) {
            return false
        }
        
        // 2. Verify essential security headers are present
        val contentTypeHeader = response.header("Content-Type")
        if (contentTypeHeader == null || !contentTypeHeader.contains("application/json")) {
            return false
        }
        
        // 3. Check for additional security headers
        val xContentTypeOptions = response.header("X-Content-Type-Options")
        if (xContentTypeOptions == null || xContentTypeOptions != "nosniff") {
            // This header prevents MIME type sniffing
            return false
        }
        
        // 4. Check HSTS header for strict transport security
        val strictTransport = response.header("Strict-Transport-Security")
        if (strictTransport == null || !strictTransport.contains("max-age=")) {
            // Less critical, but good practice
            return true // Allow this to pass, but log it
        }
        
        // 5. Check response body size to prevent DOS attacks
        val contentLength = response.body?.contentLength() ?: 0
        if (contentLength > 10 * 1024 * 1024) { // 10MB
            return false
        }
        
        return true
    }
}

// 6. IMPLEMENT PAYLOAD VALIDATION
class ResponseValidator {
    
    fun validateJsonPayload(jsonString: String): Boolean {
        // 1. Basic check for script injection
        if (jsonString.contains("<script>") || 
            jsonString.contains("javascript:") ||
            jsonString.contains("eval(")) {
            return false
        }
        
        // 2. Check for reasonable size
        if (jsonString.length > 5 * 1024 * 1024) { // 5MB
            return false
        }
        
        // 3. Validate JSON structure
        try {
            val jsonObject = JSONObject(jsonString)
            
            // 4. Check for required fields in your specific API responses
            if (!jsonObject.has("status") || !jsonObject.has("data")) {
                return false
            }
            
            // 5. Check status field is valid
            val status = jsonObject.getString("status")
            if (status != "success" && status != "error") {
                return false
            }
            
            // 6. Check timestamp is recent (within 5 minutes)
            if (jsonObject.has("timestamp")) {
                val timestamp = jsonObject.getLong("timestamp")
                val currentTime = System.currentTimeMillis() / 1000
                if (Math.abs(currentTime - timestamp) > 300) { // 5 minutes
                    return false
                }
            }
            
            // 7. Validate signature if your API uses signed responses
            if (jsonObject.has("signature")) {
                val signature = jsonObject.getString("signature")
                val data = jsonObject.getJSONObject("data").toString()
                return verifySignature(data, signature)
            }
            
            return true
        } catch (e: Exception) {
            return false
        }
    }
    
    private fun verifySignature(data: String, signature: String): Boolean {
        try {
            // This is a simplified example. You would use your actual signature verification algorithm
            val serverPublicKey = getServerPublicKey()
            val signatureBytes = Base64.decode(signature, Base64.DEFAULT)
            
            val signatureAlgorithm = Signature.getInstance("SHA256withRSA")
            signatureAlgorithm.initVerify(serverPublicKey)
            signatureAlgorithm.update(data.toByteArray(Charsets.UTF_8))
            
            return signatureAlgorithm.verify(signatureBytes)
        } catch (e: Exception) {
            return false
        }
    }
    
    private fun getServerPublicKey(): PublicKey {
        // In a real implementation, you would load your server's public key from a secure location
        // This is just a placeholder
        val keyString = "-----BEGIN PUBLIC KEY-----\n" +
                        "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA..." +
                        "-----END PUBLIC KEY-----"
                        
        val keyBytes = Base64.decode(
            keyString.replace("-----BEGIN PUBLIC KEY-----", "")
                .replace("-----END PUBLIC KEY-----", "")
                .replace("\n", ""),
            Base64.DEFAULT
        )
        
        val keyFactory = KeyFactory.getInstance("RSA")
        val keySpec = X509EncodedKeySpec(keyBytes)
        return keyFactory.generatePublic(keySpec)
    }
}

// 7. CERTIFICATE EXTRACTION HELPER 
// Use this in development to get the certificate hash for pinning
class CertificateHelper {
    
    fun getCertificateHash(hostname: String): String {
        try {
            val url = URL("https://$hostname")
            val urlConnection = url.openConnection() as HttpsURLConnection
            urlConnection.connect()
            
            val certs = urlConnection.serverCertificates
            if (certs.isNotEmpty()) {
                val cert = certs[0] as X509Certificate
                val publicKey = cert.publicKey
                val encoded = publicKey.encoded
                
                val md = MessageDigest.getInstance("SHA-256")
                val digest = md.digest(encoded)
                
                return "sha256/" + Base64.encodeToString(digest, Base64.NO_WRAP)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        
        return ""
    }
}

// 8. USAGE EXAMPLE - HOW TO USE ALL THE ABOVE IN YOUR GAME
class GameNetworkManager(private val context: Context) {
    
    // Define your API service interfaces
    interface GameApiService {
        @GET("players/{playerId}/profile")
        suspend fun getPlayerProfile(@Path("playerId") playerId: String): Response<PlayerProfile>
        
        @POST("game/submit-score")
        suspend fun submitScore(@Body scoreData: ScoreData): Response<SubmissionResult>
    }
    
    // Data classes for your API
    data class PlayerProfile(
        val id: String,
        val username: String,
        val level: Int,
        val lastLogin: Long
    )
    
    data class ScoreData(
        val playerId: String,
        val score: Int,
        val levelId: String,
        val timestamp: Long,
        val signature: String // Client-side signature to verify integrity
    )
    
    data class SubmissionResult(
        val status: String,
        val message: String,
        val timestamp: Long,
        val signature: String // Server-side signature to verify response
    )
    
    // Create the secure API client
    private val secureNetworkClient = SecureNetworkClient()
    private val gameApiService = secureNetworkClient.createApiService(GameApiService::class.java)
    private val responseValidator = ResponseValidator()
    
    // Example function to submit score securely
    suspend fun submitPlayerScore(playerId: String, score: Int, levelId: String): Result<SubmissionResult> {
        return try {
            // 1. Create data object
            val timestamp = System.currentTimeMillis() / 1000
            
            // 2. Generate signature for data integrity
            val signatureData = "$playerId:$score:$levelId:$timestamp:${BuildConfig.CLIENT_SECRET_KEY}"
            val signature = generateSignature(signatureData)
            
            // 3. Create request object
            val scoreData = ScoreData(
                playerId = playerId,
                score = score,
                levelId = levelId,
                timestamp = timestamp,
                signature = signature
            )
            
            // 4. Send request through our secure channel
            val response = gameApiService.submitScore(scoreData)
            
            // 5. First level of validation is handled by the OkHttp interceptor
            // 6. Second level of validation for the response body
            val responseBody = response.body()
            if (responseBody != null && 
                responseValidator.validateJsonPayload(Gson().toJson(responseBody))) {
                Result.success(responseBody)
            } else {
                Result.failure(SecurityException("Invalid server response"))
            }
            
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    // Helper function to generate signature
    private fun generateSignature(data: String): String {
        val md = MessageDigest.getInstance("SHA-256")
        val digest = md.digest(data.toByteArray(Charsets.UTF_8))
        return Base64.encodeToString(digest, Base64.NO_WRAP)
    }
}

// 9. SSL SOCKET FACTORY FOR ADDITIONAL SECURITY
// For cases where you need even more control over SSL/TLS
class SecureSocketFactory {
    
    fun createSSLSocketFactory(): SSLSocketFactory {
        val trustManagers = arrayOf<TrustManager>(object : X509TrustManager {
            @Throws(CertificateException::class)
            override fun checkClientTrusted(chain: Array<java.security.cert.X509Certificate>, authType: String) {
                // Trust all client certificates
            }
            
            @Throws(CertificateException::class)
            override fun checkServerTrusted(chain: Array<java.security.cert.X509Certificate>, authType: String) {
                // Custom certificate validation
                // In a production app, you would implement proper certificate validation here
                for (cert in chain) {
                    // Example validation for demonstration
                    val publicKey = cert.publicKey
                    val encoded = publicKey.encoded
                    
                    val md = MessageDigest.getInstance("SHA-256")
                    val digest = md.digest(encoded)
                    val base64Digest = Base64.encodeToString(digest, Base64.NO_WRAP)
                    
                    if (base64Digest != "r8udi/Ucs1c4rh6RIJks0CBC9sJRQQUFGZkbNj2D4pk=") {
                        throw CertificateException("Certificate validation failed")
                    }
                }
            }
            
            override fun getAcceptedIssuers(): Array<java.security.cert.X509Certificate> {
                return arrayOf()
            }
        })
        
        val sslContext = SSLContext.getInstance("TLS")
        sslContext.init(null, trustManagers, java.security.SecureRandom())
        return sslContext.socketFactory
    }
}

// 10. ADDITIONAL TLS CONFIGURATION
// Ensure latest TLS only
class TlsSocketFactory(private val delegate: SSLSocketFactory) : SSLSocketFactory() {
    
    override fun getDefaultCipherSuites(): Array<String> {
        return delegate.defaultCipherSuites
    }
    
    override fun getSupportedCipherSuites(): Array<String> {
        return delegate.supportedCipherSuites
    }
    
    override fun createSocket(s: Socket, host: String, port: Int, autoClose: Boolean): Socket {
        return enableTls12(delegate.createSocket(s, host, port, autoClose))
    }
    
    override fun createSocket(host: String, port: Int): Socket {
        return enableTls12(delegate.createSocket(host, port))
    }
    
    override fun createSocket(host: String, port: Int, localHost: InetAddress, localPort: Int): Socket {
        return enableTls12(delegate.createSocket(host, port, localHost, localPort))
    }
    
    override fun createSocket(host: InetAddress, port: Int): Socket {
        return enableTls12(delegate.createSocket(host, port))
    }
    
    override fun createSocket(address: InetAddress, port: Int, localAddress: InetAddress, localPort: Int): Socket {
        return enableTls12(delegate.createSocket(address, port, localAddress, localPort))
    }
    
    private fun enableTls12(socket: Socket): Socket {
        if (socket is SSLSocket) {
            socket.enabledProtocols = arrayOf("TLSv1.2", "TLSv1.3")
            
            // Only use strong cipher suites
            val strongCiphers = socket.supportedCipherSuites.filter { cipher ->
                !(cipher.contains("EXPORT") || 
                  cipher.contains("NULL") || 
                  cipher.contains("RC4") || 
                  cipher.contains("DES") || 
                  cipher.contains("MD5"))
            }.toTypedArray()
            
            socket.enabledCipherSuites = strongCiphers
        }
        return socket
    }
}