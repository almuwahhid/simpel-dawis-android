package id.ac.akakom.pkm.simpeldawis.menu.notifikasi

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import id.ac.akakom.pkm.simpeldawis.R
import id.ac.akakom.pkm.simpeldawis.menu.notifikasi.Model.NotifikasiModel
import kotlinx.android.synthetic.main.activity_notifikasi.*
import lib.almuwahhid.Activity.FragmentPermission
import id.ac.akakom.pkm.simpeldawis.utils.Dialogs.DialogNotifikasi.DialogNotifikasi
import kotlinx.android.synthetic.main.layout_helper.*

class NotifikasiActivity : FragmentPermission(), NotifikasiView.View {
    override fun onRequestLaporan(notifikasiModels: List<NotifikasiModel>) {
        list.clear()
        list.addAll(notifikasiModels)
        adapter.notifyDataSetChanged()
    }

    lateinit var adapter: NotifikasiAdapter
    lateinit var list: MutableList<NotifikasiModel>

    override fun onErrorConnection() {
//        helper_error.visibility = View.VISIBLE
    }

    override fun onHideLoading() {
        helper_loading.visibility = View.GONE
    }

    override fun onLoading() {
        helper_loading.visibility = View.VISIBLE
    }

    lateinit var presenter: NotifikasiPresenter

    companion object {
        @JvmStatic fun newInstance(): NotifikasiActivity{
            return NotifikasiActivity()
        }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rv.layoutManager = LinearLayoutManager(context)
        list = ArrayList()
        adapter = NotifikasiAdapter(context!!, list, object : NotifikasiAdapter.OnNotifikasiAdapter{
            override fun onItemClick(notifikasiModel: NotifikasiModel) {
                DialogNotifikasi(context, notifikasiModel)
            }
        })
        rv.adapter = adapter

        swipe.setOnRefreshListener {
            swipe.isRefreshing = false
            presenter.requestLaporan()
        }

        presenter.requestLaporan()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.activity_notifikasi, container, false)
//        (activity as AppCompatActivity).setSupportActionBar(toolbar)
//        (activity as AppCompatActivity).supportActionBar!!.setDisplayShowTitleEnabled(false)
//        setHasOptionsMenu(true)
//        activity!!.invalidateOptionsMenu()

        presenter = NotifikasiPresenter(context, this)

        return view
    }
}
