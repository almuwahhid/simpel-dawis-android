package id.ac.akakom.pkm.simpeldawis.menu.sop

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.barteksc.pdfviewer.listener.OnLoadCompleteListener
import id.ac.akakom.pkm.simpeldawis.R
import lib.almuwahhid.Activity.FragmentPermission
import com.github.barteksc.pdfviewer.scroll.DefaultScrollHandle
import kotlinx.android.synthetic.main.activity_sop.*
import lib.almuwahhid.utils.LibUi


class SopActivity : FragmentPermission(), SopView.View, OnLoadCompleteListener {
    override fun loadComplete(nbPages: Int) {
        LibUi.hideLoadingDialog(context)
    }

    override fun onErrorConnection() {

    }

    override fun onHideLoading() {

    }

    override fun onLoading() {

    }

    lateinit var presenter: SopPresenter

    companion object {
        @JvmStatic fun newInstance(): SopActivity{
            return SopActivity()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        displayFromAsset()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.activity_sop, container, false)
        presenter = SopPresenter(context, this)

        return view
    }

    private fun displayFromAsset() {
//        pdfFileName = assetFileName
        LibUi.showLoadingDialog(context, R.drawable.logo)
        pdfView.fromAsset("sop.pdf")
//                .defaultPage(pageNumber)
                .enableSwipe(true)

                .swipeHorizontal(false)
//                .onPageChange(this)
                .enableAnnotationRendering(true)
                .onLoad(this)
                .scrollHandle(DefaultScrollHandle(context))
                .load()
    }
}
