package id.ac.akakom.pkm.simpeldawis.menu.login

import android.content.Context
import android.util.Log
import id.ac.akakom.pkm.simpeldawis.data.EndPoints
import id.ac.akakom.pkm.simpeldawis.data.Model.UserModel
import lib.almuwahhid.base.BasePresenter
import lib.almuwahhid.utils.LibConstant
import lib.almuwahhid.utils.UiLibRequest
import org.json.JSONException
import org.json.JSONObject
import java.util.HashMap

class LoginPresenter (context: Context?, view: LoginView.View) : BasePresenter(context), LoginView.Presenter {
    internal var view: LoginView.View? = null
    init {
        this.view = view
    }

    override fun requestLogin(username: String, password: String) {
        UiLibRequest.POST(EndPoints.stringLogin(), context, object : UiLibRequest.OnPostRequest {
            override fun onPreExecuted() {
                view!!.onLoading()
            }

            override fun onSuccess(response: JSONObject) {
                view!!.onHideLoading()
                try {
                    if (response.getInt("status") == LibConstant.CODE_SUCCESS) {
                        view!!.onSuccessLogin(gson!!.fromJson(response.getJSONObject("data").toString(), UserModel::class.java))
                    } else {
                        Log.d("Login Presenter huh", response.toString())
                        view!!.onFailedLogin(response.getString("message"))
                    }
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            }

            override fun onFailure(error: String) {
                Log.d("Login Presenter ", error)
                view!!.onHideLoading()
                view!!.onFailedLogin("Bermasalah dengan Server")
            }

            override fun requestParam(): Map<String, String> {
                val param = HashMap<String, String>()
                param["username"] = username
                param["password"] = password
                return param
            }

            override fun requestHeaders(): Map<String, String> {
                return HashMap()
            }
        })
    }
}