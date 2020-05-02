package helloworld

import com.amazonaws.services.lambda.runtime.Context
import com.amazonaws.services.lambda.runtime.RequestHandler
import java.io.IOException
import java.net.URL

/**
 * Handler for requests to Lambda function.
 */
class App : RequestHandler<Any?, Any> {
    override fun handleRequest(input: Any?, context: Context?): Any {
        val headers: MutableMap<String, String> = HashMap()
        headers["Content-Type"] = "application/json"
        headers["X-Custom-Header"] = "application/json"
        return try {
            val pageContents = getPageContents("https://checkip.amazonaws.com")
            val output = String.format("{ \"message\": \"hello world\", \"location\": \"%s\" }", pageContents)
            GatewayResponse(output, headers, 200)
        } catch (e: IOException) {
            GatewayResponse("{}", headers, 500)
        }
    }

    @Throws(IOException::class)
    private fun getPageContents(address: String): String {
        return URL(address).readText().trim()
    }
}
