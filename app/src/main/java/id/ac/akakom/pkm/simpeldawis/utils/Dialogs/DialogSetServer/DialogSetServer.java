package id.ac.akakom.pkm.simpeldawis.utils.Dialogs.DialogSetServer;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import id.ac.akakom.pkm.simpeldawis.R;
import id.ac.akakom.pkm.simpeldawis.data.Preferences;
import lib.almuwahhid.utils.DialogBuilder;
import lib.almuwahhid.utils.LibUi;

public class DialogSetServer  extends DialogBuilder {
    EditText edt_picker;
    ImageView img_ok;
    RelativeLayout lay_dialog;
    TextView tv_title;


    public DialogSetServer(Context context) {
        super(context, R.layout.dialog_lupa_password);

        initComponent(new OnInitComponent() {
            @Override
            public void initComponent(Dialog dialog) {
                lay_dialog = dialog.findViewById(R.id.lay_dialog);
                img_ok = dialog.findViewById(R.id.img_ok);
                edt_picker = dialog.findViewById(R.id.edt_picker);
                tv_title = dialog.findViewById(R.id.tv_title);

                setAnimation(R.style.DialogBottomAnimation);
                setFullWidth(lay_dialog);
                setGravity(Gravity.BOTTOM);

                edt_picker.setHint("Base Server Anda disini");
                tv_title.setText("Server Lokal");
                edt_picker.setCompoundDrawablesWithIntrinsicBounds( R.drawable.ic_android_black_24dp, 0, 0, 0);

                img_ok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(edt_picker.getText().toString().equals("")){
                            LibUi.ToastShort(getContext(), "Isi dulu ya");
                        } else {
                            LibUi.setSPString(getContext(), Preferences.SERVER, edt_picker.getText().toString());
                            dismiss();
                        }
                    }
                });
            }
        });
        show();
    }
}
