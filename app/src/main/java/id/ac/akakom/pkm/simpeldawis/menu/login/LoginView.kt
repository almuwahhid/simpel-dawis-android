package id.ac.akakom.pkm.simpeldawis.menu.login

import id.ac.akakom.pkm.simpeldawis.data.Model.UserModel
import lib.almuwahhid.base.BaseView

interface LoginView {
    interface Presenter{
        abstract fun requestLogin(username: String, password: String)
    }

    interface View: BaseView{
        abstract fun onSuccessLogin(model: UserModel)
        abstract fun onFailedLogin(message: String)
    }
}