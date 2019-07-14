package id.ac.akakom.pkm.simpeldawis.menu.akun

import android.content.Context
import id.ac.akakom.pkm.simpeldawis.data.EndPoints
import id.ac.akakom.pkm.simpeldawis.data.Model.UserModel
import id.ac.akakom.pkm.simpeldawis.utils.Function
import lib.almuwahhid.base.BasePresenter
import lib.almuwahhid.utils.LibConstant
import lib.almuwahhid.utils.UiLibRequest
import org.json.JSONException
import org.json.JSONObject
import java.util.HashMap

class AkunPresenter(context: Context?, view: AkunView.View) : BasePresenter(context), AkunView.Presenter  {

    internal var view: AkunView.View? = null
    init {
        this.view = view
    }
    override fun updateUser(userModel: UserModel) {
        UiLibRequest.POST(EndPoints.stringUpdateProfile(), context, object : UiLibRequest.OnPostRequest {
            override fun onPreExecuted() {
                view!!.onLoading()
            }
            override fun onSuccess(response: JSONObject) {
                view!!.onHideLoading()
                try {
                    if (response.getInt("status") == LibConstant.CODE_SUCCESS) {
                        var userModel = gson.fromJson(response.getJSONObject("data").toString(), UserModel::class.java)
                        userModel.token = Function.getUserPreference(context).token
                        Function.setUserPreference(context, gson.toJson(userModel))
                        view!!.onUpdateUser()
                    } else {
                        view!!.onFailedUpdateUser(response.getString("message"))
                    }
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            }

            override fun onFailure(error: String) {
                view!!.onHideLoading()
                view!!.onErrorConnection()
            }

            override fun requestParam(): Map<String, String> {
                val param = HashMap<String, String>()
                param.put("data", gson.toJson(userModel))
                return param
            }

            override fun requestHeaders(): Map<String, String> {
                var param = HashMap<String, String>()
                param.put("Authorization", "Bearer "+Function.getUserPreference(context).token)
                return param
            }
        })
    }
}