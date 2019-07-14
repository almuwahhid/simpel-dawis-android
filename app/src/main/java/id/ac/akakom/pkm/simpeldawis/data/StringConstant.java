package id.ac.akakom.pkm.simpeldawis.data;


import id.ac.akakom.pkm.simpeldawis.BuildConfig;

public class StringConstant {
    private static String package_name = BuildConfig.APPLICATION_ID;

//    public static final String SERVICECHAT_RECEIVE_MESSAGE      = "received message";
    public static final String SERVICECHAT_RECEIVE_MESSAGE      = "listen";
    public static final String SERVICECHAT_RECEIVE_UPDATESTATUS = "listen_update_status";

    public static final String ID_USERNAME = "idusername";
    public static final String ID_EMAIL = "IDEMAIL";
    public static final String ID_NAME = "IDNAME";
    public static final String ID_NIP = "IDNIP";
    public static final String ID_ALAMAT = "IDALAMAT";
    public static final String ID_NOTELP = "IDNOTELP";
    public static final int CODE_ERROR = 401;

    public static final String[] mimeTypes =
            {"image/*","application/pdf","application/msword","application/vnd.ms-powerpoint","application/vnd.ms-excel","text/plain"};

    public static final String[] mimeTypesFile =
            {"application/pdf","application/msword","application/vnd.ms-powerpoint","application/vnd.ms-excel"};
}
