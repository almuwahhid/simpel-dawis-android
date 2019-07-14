package id.ac.akakom.pkm.simpeldawis.menu.notifikasi

import id.ac.akakom.pkm.simpeldawis.menu.notifikasi.Model.NotifikasiModel
import lib.almuwahhid.base.BaseView

interface NotifikasiView {
    interface Presenter{
        fun requestLaporan()
    }

    interface View: BaseView {
        fun onRequestLaporan(notifikasiModels: List<NotifikasiModel>)
    }
}