package id.ac.akakom.pkm.simpeldawis.utils.Dialogs.DialogUbahPassword;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.scottyab.showhidepasswordedittext.ShowHidePasswordEditText;

import id.ac.akakom.pkm.simpeldawis.R;
import id.ac.akakom.pkm.simpeldawis.utils.Function;
import lib.almuwahhid.utils.DialogBuilder;
import lib.almuwahhid.utils.LibUi;

public class DialogUbahPassword extends DialogBuilder implements DialogUbahPasswordView.View{

    RelativeLayout lay_dialog;
    ShowHidePasswordEditText edt_password_lama, edt_password_baru, edt_password_ulangi;
    ImageView img_ok;

    DialogUbahPasswordPresenter presenter;

    public DialogUbahPassword(Context context) {
        super(context, R.layout.dialog_ubah_password);
        presenter = new DialogUbahPasswordPresenter(getContext(), this);

        initComponent(new OnInitComponent() {
            @Override
            public void initComponent(Dialog dialog) {
                lay_dialog = dialog.findViewById(R.id.lay_dialog);
                edt_password_lama = dialog.findViewById(R.id.edt_password_lama);
                edt_password_baru = dialog.findViewById(R.id.edt_password_baru);
                edt_password_ulangi = dialog.findViewById(R.id.edt_password_ulangi);
                img_ok = dialog.findViewById(R.id.img_ok);



                setAnimation(R.style.DialogBottomAnimation);
                setFullWidth(lay_dialog);
                setGravity(Gravity.BOTTOM);

                img_ok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        validate();
                    }
                });
            }
        });
        show();
    }

    private void validate(){
        boolean isValid = true;
        if(edt_password_baru.getText().toString().equals("")){
            edt_password_baru.setError("Wajib diisi");
            isValid = false;
        }
        if(edt_password_lama.getText().toString().equals("")){
            edt_password_lama.setError("Wajib diisi");
            isValid = false;
        }
        if(edt_password_ulangi.getText().toString().equals("")){
            edt_password_ulangi.setError("Wajib diisi");
            isValid = false;
        }

        if(isValid){
            if(edt_password_ulangi.getText().toString().equals(edt_password_baru.getText().toString())){
                presenter.requestUbahPassword(edt_password_lama.getText().toString(), edt_password_baru.getText().toString());
            } else {
                edt_password_ulangi.setError("Password belum sesuai");
                LibUi.ToastShort(getContext(), "Password belum sesuai");
            }
        }

    }

    @Override
    public void onRequestUbahPassword(boolean isSuccess) {
        if(isSuccess){
            LibUi.ToastShort(getContext(), "Berhasil mengubah password");
            dismiss();
        } else {
            LibUi.ToastShort(getContext(), "Gagal mengubah password");
        }

    }

    @Override
    public void onLoading() {
        LibUi.showLoadingDialog(getContext(), R.drawable.logo);
    }


    @Override
    public void onHideLoading() {
        LibUi.hideLoadingDialog(getContext());
    }

    @Override
    public void onErrorConnection() {
        LibUi.ToastShort(getContext(), "Ada yang bermasalah dengan server");
    }
}
