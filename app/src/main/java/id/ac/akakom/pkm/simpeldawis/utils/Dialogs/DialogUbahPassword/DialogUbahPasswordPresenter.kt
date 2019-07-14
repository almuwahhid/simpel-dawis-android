package id.ac.akakom.pkm.simpeldawis.utils.Dialogs.DialogUbahPassword

import android.content.Context
import android.util.Log
import id.ac.akakom.pkm.simpeldawis.data.EndPoints
import id.ac.akakom.pkm.simpeldawis.utils.Function
import lib.almuwahhid.base.BasePresenter
import lib.almuwahhid.utils.LibConstant
import lib.almuwahhid.utils.UiLibRequest
import org.json.JSONException
import org.json.JSONObject
import java.util.HashMap

class DialogUbahPasswordPresenter(context: Context?, view: DialogUbahPasswordView.View) : BasePresenter(context), DialogUbahPasswordView.Presenter {
    internal var view: DialogUbahPasswordView.View? = null
    init {
        this.view = view
    }

    override fun requestUbahPassword(password_lama: String?, password_baru: String?) {
        UiLibRequest.POST(EndPoints.stringUbahPassword(), context, object : UiLibRequest.OnPostRequest {
            override fun onPreExecuted() {
                view!!.onLoading()
            }

            override fun onSuccess(response: JSONObject) {
                view!!.onHideLoading()
                try {
                    if (response.getInt("status") == LibConstant.CODE_SUCCESS) {
                        view!!.onRequestUbahPassword(true)
                    } else {
                        view!!.onRequestUbahPassword(false)
                    }
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            }

            override fun onFailure(error: String) {
                Log.d("Login Presenter ", error)
                view!!.onHideLoading()
                view!!.onErrorConnection()
            }

            override fun requestParam(): Map<String, String> {
                val param = HashMap<String, String>()
                param["old_password"] = password_lama!!
                param["new_password"] = password_baru!!
                return param
            }

            override fun requestHeaders(): Map<String, String> {
                var param = HashMap<String, String>()
                param.put("Authorization", "Bearer "+ Function.getUserPreference(context).token)
                return param
            }
        })
    }
}