package id.ac.akakom.pkm.simpeldawis.menu.notifikasi

import android.content.Context
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import id.ac.akakom.pkm.simpeldawis.R
import id.ac.akakom.pkm.simpeldawis.menu.notifikasi.Model.NotifikasiModel
import id.ac.akakom.pkm.simpeldawis.utils.Function

class NotifikasiAdapter (context: Context, list: MutableList<NotifikasiModel>, private val onNotifikasiAdapter: NotifikasiAdapter.OnNotifikasiAdapter) : RecyclerView.Adapter<NotifikasiAdapter.Holder>() {

    lateinit var context: Context
    lateinit var list: MutableList<NotifikasiModel>

    init {
        this.context = context
        this.list = list
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): NotifikasiAdapter.Holder {
        val layoutView: View
        layoutView = LayoutInflater.from(p0.context).inflate(R.layout.adapter_notifikasi, p0, false)
        return NotifikasiAdapter.Holder(layoutView)
    }

    override fun getItemCount(): Int {
        return list.size
//        return 5
    }

    override fun onBindViewHolder(holder: NotifikasiAdapter.Holder, position: Int) {
        var notifikasiModel = list.get(position)

        if(notifikasiModel.tipe_laporan.equals("U")){
            holder.img_icon.setImageResource(R.drawable.ic_web)
            holder.tv_content.setText("Link URL")
        } else {
            holder.img_icon.setImageResource(R.drawable.ic_file)
            holder.tv_content.setText("File")
        }

        holder.tv_date.setText(Function.parseTimestampToDate(notifikasiModel.created_at))
        holder.tv_title.setText(notifikasiModel.nama_laporan)

        holder.lay_notifikasi.setOnClickListener({
            onNotifikasiAdapter.onItemClick(notifikasiModel)
        })
    }

    interface OnNotifikasiAdapter{
        fun onItemClick(notifikasiModel: NotifikasiModel)
    }

    class Holder (itemView: View) : RecyclerView.ViewHolder(itemView) {
        lateinit var img_icon: ImageView
        lateinit var tv_title: TextView
        lateinit var tv_content: TextView
        lateinit var tv_date: TextView
        lateinit var lay_notifikasi: CardView

        init {
            img_icon = itemView.findViewById(R.id.img_icon)
            tv_title = itemView.findViewById(R.id.tv_title)
            tv_content = itemView.findViewById(R.id.tv_content)
            tv_date = itemView.findViewById(R.id.tv_date)
            lay_notifikasi = itemView.findViewById(R.id.lay_notifikasi)
            this.setIsRecyclable(false)
        }
    }
}