package id.ac.akakom.pkm.simpeldawis.utils.Dialogs.DialogUbahPassword;

import lib.almuwahhid.base.BaseView;

public interface DialogUbahPasswordView {
    interface Presenter{
        void requestUbahPassword(String password, String username);
    }

    interface View extends BaseView {
        void onRequestUbahPassword(boolean isSuccess);
    }
}
