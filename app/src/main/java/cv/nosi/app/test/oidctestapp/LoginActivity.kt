package cv.nosi.app.test.oidctestapp

import android.app.PendingIntent
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.button.MaterialButton
import io.asgardeo.android.oidc.sdk.sso.DefaultLoginService
import io.asgardeo.android.oidc.sdk.sso.LoginService

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        findViewById<MaterialButton>(R.id.oidc_login).setOnClickListener {
            val completionIntent = Intent(this, MainActivity::class.java)
            val cancelIntent = Intent(this, LoginActivity::class.java)
            cancelIntent.putExtra("failed", true)
            cancelIntent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            val successIntent = PendingIntent.getActivity(this, 0, completionIntent, 0)
            val failureIntent = PendingIntent.getActivity(this, 0, cancelIntent, 0)
            DefaultLoginService(this).authorize(successIntent, failureIntent, true)
        }
    }

}