package id.ac.akakom.pkm.simpeldawis.utils.Dialogs.DialogNotifikasi;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import id.ac.akakom.pkm.simpeldawis.R;
import id.ac.akakom.pkm.simpeldawis.menu.notifikasi.Model.NotifikasiModel;
import id.ac.akakom.pkm.simpeldawis.utils.Function;
import lib.almuwahhid.utils.DialogBuilder;

public class DialogNotifikasi extends DialogBuilder {
    ImageView img_ok, img_logo;
    RelativeLayout lay_dialog;
    TextView tv_name, tv_date;
    Button btn;


    public DialogNotifikasi(Context context, final NotifikasiModel notifikasiModel) {
        super(context, R.layout.dialog_notifikasi);

        initComponent(new OnInitComponent() {
            @Override
            public void initComponent(Dialog dialog) {
                lay_dialog = dialog.findViewById(R.id.lay_dialog);
                img_ok = dialog.findViewById(R.id.img_ok);
                tv_name = dialog.findViewById(R.id.tv_name);
                tv_date = dialog.findViewById(R.id.tv_date);
                btn = dialog.findViewById(R.id.btn);
                img_logo = dialog.findViewById(R.id.img_logo);

                setAnimation(R.style.DialogBottomAnimation);
                setFullWidth(lay_dialog);
                setGravity(Gravity.BOTTOM);

                tv_date.setText(Function.parseTimestampToDate(notifikasiModel.getCreated_at()));
                tv_name.setText(notifikasiModel.getNama_laporan());

                if(notifikasiModel.getTipe_laporan().equals("U")){
                    img_logo.setImageResource(R.drawable.ic_web);
                } else {
                    img_logo.setImageResource(R.drawable.ic_file);
                }

                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent httpIntent = new Intent(Intent.ACTION_VIEW);
                        httpIntent.setData(Uri.parse(notifikasiModel.getUrl_laporan()));

                        getActivity().startActivity(httpIntent);
                    }
                });

                img_ok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dismiss();
                    }
                });
            }
        });
        show();
    }
}
