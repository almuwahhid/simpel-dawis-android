package id.ac.akakom.pkm.simpeldawis.menu.akun

import id.ac.akakom.pkm.simpeldawis.data.Model.UserModel
import lib.almuwahhid.base.BaseView

interface AkunView {
    interface Presenter{
        fun updateUser(userModel: UserModel)
    }

    interface View: BaseView {
        fun onUpdateUser()
        fun onFailedUpdateUser(message: String)
    }
}
