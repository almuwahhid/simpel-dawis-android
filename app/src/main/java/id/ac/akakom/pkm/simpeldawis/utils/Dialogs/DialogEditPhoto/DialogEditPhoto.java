package id.ac.akakom.pkm.simpeldawis.utils.Dialogs.DialogEditPhoto;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.CardView;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import id.ac.akakom.pkm.simpeldawis.R;
import id.ac.akakom.pkm.simpeldawis.data.Model.UserModel;
import id.ac.akakom.pkm.simpeldawis.utils.Function;
import id.ac.akakom.pkm.simpeldawis.utils.avatarview.AvatarPlaceholder;
import id.ac.akakom.pkm.simpeldawis.utils.avatarview.loader.PicassoLoader;
import id.ac.akakom.pkm.simpeldawis.utils.avatarview.views.AvatarView;
import lib.almuwahhid.utils.DialogBuilder;
import lib.almuwahhid.utils.LibUi;

public class DialogEditPhoto extends DialogBuilder {

    ImageView img_ok;
    RelativeLayout lay_dialog;
    OnDialogEditPhoto onDialogEditPhoto;
    AvatarView avatarView;
    CardView btn;
    UserModel userModel;

    public DialogEditPhoto(Context context, final UserModel userModel, final OnDialogEditPhoto onDialogEditPhoto) {
        super(context, R.layout.dialog_edit_photo);
        this.userModel = userModel;
        this.onDialogEditPhoto = onDialogEditPhoto;

        initComponent(new OnInitComponent() {
            @Override
            public void initComponent(Dialog dialog) {
                lay_dialog = dialog.findViewById(R.id.lay_dialog);
                img_ok = dialog.findViewById(R.id.img_ok);
                avatarView = dialog.findViewById(R.id.avatarView);
                btn = dialog.findViewById(R.id.btn);
                isUpdate = false;

                setAnimation(R.style.DialogBottomAnimation);
                setFullWidth(lay_dialog);
                setGravity(Gravity.BOTTOM);

                String y = Function.getUserPreference(getContext()).getFoto();


                PicassoLoader imageLoader = new PicassoLoader();
                if(y.equals("")){
                    y = "http://google.com";
                }
                AvatarPlaceholder refreshableAvatarPlaceholder = new AvatarPlaceholder(Function.getUserPreference(getContext()).getName());
                imageLoader.loadImage(avatarView, refreshableAvatarPlaceholder, y);

                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        onDialogEditPhoto.onOpenCamera();
                    }
                });

                img_ok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(!isUpdate)
                            dismiss();
                        else
                            onDialogEditPhoto.onUpdateUser(isUpdate, userModel);
                    }
                });
            }
        });
        show();
    }

    Boolean isUpdate;
    public void updatePhoto(Bitmap bitmap){
        isUpdate = true;
        avatarView.setImageBitmap(bitmap);
        userModel.setFoto(LibUi.convertToBase64(bitmap));
    }

    public interface OnDialogEditPhoto{
        void onUpdateUser(boolean isUpdate, UserModel userModel);
        void onOpenCamera();
    }
}
