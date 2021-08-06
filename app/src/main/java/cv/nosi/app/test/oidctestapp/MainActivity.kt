package cv.nosi.app.test.oidctestapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import com.google.android.material.button.MaterialButton
import io.asgardeo.android.oidc.sdk.activity.TokenManagementActivity
import io.asgardeo.android.oidc.sdk.context.AuthenticationContext
import io.asgardeo.android.oidc.sdk.sso.DefaultLoginService

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        TokenManagementActivity.mAuthenticationContext = intent.getSerializableExtra("context") as AuthenticationContext?

        val userName = TokenManagementActivity.mAuthenticationContext.user.userName
        val userAttributes = TokenManagementActivity.mAuthenticationContext.user.attributes

        findViewById<TextView>(R.id.user_name).text = userName
        findViewById<TextView>(R.id.user_attributes).text = userAttributes.toString()

        val oAuth2TokenResponse = TokenManagementActivity.mAuthenticationContext.oAuth2TokenResponse

        findViewById<TextView>(R.id.token_response_id_token).text = oAuth2TokenResponse.idToken
        findViewById<TextView>(R.id.token_response_access_token).text = oAuth2TokenResponse.accessToken
        findViewById<TextView>(R.id.token_response_expiration_time).text = oAuth2TokenResponse.accessTokenExpirationTime.toString()
        findViewById<TextView>(R.id.token_response_token_type).text = oAuth2TokenResponse.tokenType
        findViewById<TextView>(R.id.token_response_refresh_token).text = oAuth2TokenResponse.refreshToken

        val idTokenResponse = TokenManagementActivity.mAuthenticationContext
            .oAuth2TokenResponse.idTokenResponse

        findViewById<TextView>(R.id.token_response_issuer).text = idTokenResponse.issuer
        findViewById<TextView>(R.id.token_response_subject).text = idTokenResponse.subject
        findViewById<TextView>(R.id.token_response_issue_time).text = idTokenResponse.issueTime.toString()
        findViewById<TextView>(R.id.token_response_expiry_time).text = idTokenResponse.expiryTime.toString()
        findViewById<TextView>(R.id.token_response_audience).text = idTokenResponse.audience.toString()


        findViewById<MaterialButton>(R.id.logout_button).setOnClickListener {
            DefaultLoginService(this).logout(this, TokenManagementActivity.mAuthenticationContext)
        }
    }

    override fun onResume() {
        super.onResume()
        Intent(baseContext, LoginActivity::class.java).apply {
            startActivity(this)
        }
        finish()
    }
}