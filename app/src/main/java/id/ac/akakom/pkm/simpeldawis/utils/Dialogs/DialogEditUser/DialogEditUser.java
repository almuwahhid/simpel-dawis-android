package id.ac.akakom.pkm.simpeldawis.utils.Dialogs.DialogEditUser;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import id.ac.akakom.pkm.simpeldawis.R;
import id.ac.akakom.pkm.simpeldawis.menu.akun.Model.AkunModel;
import lib.almuwahhid.utils.DialogBuilder;

public class DialogEditUser extends DialogBuilder{
    EditText edt_picker;
    ImageView img_ok;
    RelativeLayout lay_dialog;
    TextView tv_title;
    OnDialogEditUser onDialogEditUser;


    public DialogEditUser(Context context, final AkunModel akunModel, final OnDialogEditUser onDialogEditUser) {
        super(context, R.layout.dialog_lupa_password);
        this.onDialogEditUser = onDialogEditUser;

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

                edt_picker.setText(akunModel.getContent());
                tv_title.setText(akunModel.getTitle());
                edt_picker.setCompoundDrawablesWithIntrinsicBounds( akunModel.getIcon(), 0, 0, 0);

                img_ok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(edt_picker.getText().toString().equals("")){
                            edt_picker.setError("Wajib diisi");
                        } else {
                            akunModel.setContent(edt_picker.getText().toString());
                            onDialogEditUser.onUpdateUser(akunModel);
                            dismiss();
                        }
                    }
                });
            }
        });
        show();
    }

    public interface OnDialogEditUser{
        void onUpdateUser(AkunModel akunModel);
    }
}
