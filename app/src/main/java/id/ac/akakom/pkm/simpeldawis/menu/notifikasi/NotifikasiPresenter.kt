package id.ac.akakom.pkm.simpeldawis.menu.notifikasi

import android.content.Context
import id.ac.akakom.pkm.simpeldawis.data.EndPoints
import id.ac.akakom.pkm.simpeldawis.menu.notifikasi.Model.NotifikasiModel
import id.ac.akakom.pkm.simpeldawis.utils.Function
import lib.almuwahhid.base.BasePresenter
import lib.almuwahhid.utils.LibConstant
import lib.almuwahhid.utils.UiLibRequest
import org.json.JSONException
import org.json.JSONObject
import java.util.HashMap

class NotifikasiPresenter (context: Context?, view: NotifikasiView.View) : BasePresenter(context), NotifikasiView.Presenter  {

    internal var view: NotifikasiView.View? = null
    init {
        this.view = view
    }
    override fun requestLaporan() {
        UiLibRequest.POST(EndPoints.stringLaporan(), context, object : UiLibRequest.OnPostRequest {
            override fun onPreExecuted() {
                view!!.onLoading()
            }
            override fun onSuccess(response: JSONObject) {
                view!!.onHideLoading()
                try {
                    if (response.getInt("status") == LibConstant.CODE_SUCCESS) {
                        val datas = response.getJSONArray("data")
                        var list : MutableList<NotifikasiModel> = ArrayList()
                        for (i in 0..(datas.length() - 1)) {
                            list.add(gson!!.fromJson(datas.get(i).toString(), NotifikasiModel::class.java))
                            view!!.onRequestLaporan(list)
                        }

                    } else {
                        view!!.onErrorConnection()
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