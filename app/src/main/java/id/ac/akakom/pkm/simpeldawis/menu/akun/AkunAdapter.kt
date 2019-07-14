package id.ac.akakom.pkm.simpeldawis.menu.akun

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import id.ac.akakom.pkm.simpeldawis.R
import id.ac.akakom.pkm.simpeldawis.menu.akun.Model.AkunModel

class AkunAdapter (context: Context, list: MutableList<AkunModel>, private val onAkunAdapter: AkunAdapter.OnAkunADapter) : RecyclerView.Adapter<AkunAdapter.Holder>() {

    lateinit var context: Context
    lateinit var list: MutableList<AkunModel>

    init {
        this.context = context
        this.list = list
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): AkunAdapter.Holder {
        val layoutView: View
        layoutView = LayoutInflater.from(p0.context).inflate(R.layout.adapter_akun, p0, false)
        return AkunAdapter.Holder(layoutView)
    }



    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: AkunAdapter.Holder, p1: Int) {
        val akunModel = list.get(p1)
        holder.img_icon.setImageResource(akunModel.icon)
        holder.tv_title.setText(akunModel.title)
        holder.tv_content.setText(akunModel.content)

        if(akunModel.isEditable){
//            onAkunAdapter.onItemClick(akunModel)
            holder.lay_akun.setOnClickListener({
                onAkunAdapter.onItemClick(akunModel)
            })
        } else {
            holder.img_edit.visibility = View.GONE
        }

    }

    interface OnAkunADapter{
        fun onItemClick(akunModel: AkunModel)
    }

    class Holder (itemView: View) : RecyclerView.ViewHolder(itemView) {
        lateinit var img_icon: ImageView
        lateinit var img_edit: ImageView
        lateinit var tv_title: TextView
        lateinit var tv_content: TextView
        lateinit var lay_akun: RelativeLayout

        init {
            img_icon = itemView.findViewById(R.id.img_icon)
            img_edit = itemView.findViewById(R.id.img_edit)
            tv_title = itemView.findViewById(R.id.tv_title)
            lay_akun = itemView.findViewById(R.id.lay_akun)
            tv_content = itemView.findViewById(R.id.tv_content)
            this.setIsRecyclable(false)
        }
    }
}