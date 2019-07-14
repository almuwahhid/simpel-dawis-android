package id.ac.akakom.pkm.simpeldawis.menu.login

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.firebase.messaging.FirebaseMessaging
import com.google.gson.Gson
import id.ac.akakom.pkm.simpeldawis.R
import id.ac.akakom.pkm.simpeldawis.data.Model.UserModel
import id.ac.akakom.pkm.simpeldawis.menu.main.MainActivity
import id.ac.akakom.pkm.simpeldawis.utils.Dialogs.DialogLupaPassword.DialogLupaPassword
import id.ac.akakom.pkm.simpeldawis.utils.Function

import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.layout_helper.*
import lib.almuwahhid.utils.LibUi
import uk.co.chrisjenx.calligraphy.CalligraphyConfig
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper
import java.util.ArrayList

class LoginActivity : AppCompatActivity(), LoginView.View {

    var gson: Gson? = null;
    lateinit var presenter: LoginPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        gson = Gson()


        CalligraphyConfig.initDefault(CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/SanFransisco-Regular.otf")
                .setFontAttrId(R.attr.fontPath)
                .build()

        )

        if(Function.checkUserPreference(this)){
            finish()
            startActivity(Intent(this@LoginActivity, MainActivity::class.java))
        }

        btn.setOnClickListener({
//            startActivity(Intent(this@LoginActivity, MainActivity::class.java))
            validate()
        })

        presenter = LoginPresenter(this, this)
        setFormsToValidate()

        helper_loading_top.setInOutAnimation(R.anim.pull_in_bottom, R.anim.push_out_top)

        if(Function.checkUserPreference(this)){
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }

        tv_lupapassword.setOnClickListener({
            DialogLupaPassword(this@LoginActivity)
        })
    }

    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase))
    }

    /*override fun onResume() {
        super.onResume()
        if(LibUi.getSPString(this, Preferences.SERVER).equals("")){
            DialogSetServer(this)
        }
    }*/

    override fun onSuccessLogin(model: UserModel) {
        Function.setUserPreference(this, gson!!.toJson(model))
        var sub = "D"
        if(model.level == 1)
            sub = "P"
        else
            sub = "D"

        Log.d("LoginActivity ", sub.toString())
        FirebaseMessaging.getInstance().subscribeToTopic(sub)
        FirebaseMessaging.getInstance().subscribeToTopic("A")
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    override fun onFailedLogin(message: String) {
        tv_loading_top.setText(message)
        helper_loading_top.showForAWhile(this)
    }

    override fun onErrorConnection() {
        tv_loading_top.setText("Bermasalah dengan server")
        helper_loading_top.showForAWhile(this)
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    override fun onHideLoading() {
        LibUi.hideLoadingDialog(this)
    }

    override fun onLoading() {
        LibUi.showLoadingDialog(this, R.drawable.logo)
    }

    internal var forms: MutableList<Int> = ArrayList()
    private fun setFormsToValidate() {
        forms.add(R.id.edt_password)
        forms.add(R.id.edt_username)
    }

    private fun validate() {
        if (LibUi.isFormValid(this, window.decorView, forms)) {
            presenter!!.requestLogin(edt_username.text.toString(), edt_password.text.toString())
        }
    }
}
