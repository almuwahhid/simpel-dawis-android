package id.ac.akakom.pkm.simpeldawis.menu.akun.Helper;

import java.util.ArrayList;
import java.util.List;

import id.ac.akakom.pkm.simpeldawis.R;
import id.ac.akakom.pkm.simpeldawis.data.Model.UserModel;
import id.ac.akakom.pkm.simpeldawis.data.StringConstant;
import id.ac.akakom.pkm.simpeldawis.menu.akun.Model.AkunModel;

public class AkunHelper {
    public static List<AkunModel> listAkun(UserModel userModel){
        List<AkunModel> listAkun = new ArrayList<>();
        AkunModel akunModel = new AkunModel("Username", userModel.getUsername(), R.drawable.ic_user, StringConstant.ID_USERNAME, true);
        listAkun.add(akunModel);
        akunModel = new AkunModel("Email", userModel.getEmail(), R.drawable.ic_email, StringConstant.ID_EMAIL, true);
        listAkun.add(akunModel);
        akunModel = new AkunModel("Nama", userModel.getName(), R.drawable.ic_user, StringConstant.ID_NAME, true);
        listAkun.add(akunModel);
        /*akunModel = new AkunModel("NIP", userModel.getNip(), R.drawable.ic_id_card, StringConstant.ID_NIP, false);
        listAkun.add(akunModel);*/
        akunModel = new AkunModel("Alamat", userModel.getAlamat(), R.drawable.ic_home, StringConstant.ID_ALAMAT, true);
        listAkun.add(akunModel);
        akunModel = new AkunModel("Nomor Telepon", userModel.getNo_tlp(), R.drawable.ic_telephone, StringConstant.ID_NOTELP, true);
        listAkun.add(akunModel);
        return listAkun;
    }
}
