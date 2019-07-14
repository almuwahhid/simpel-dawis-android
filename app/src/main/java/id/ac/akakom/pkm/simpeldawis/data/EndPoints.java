package id.ac.akakom.pkm.simpeldawis.data;


import id.ac.akakom.pkm.simpeldawis.BuildConfig;

public class EndPoints {
    public static String a = BuildConfig.base_url;
//    public static String a = LibUi.getSPString();
    private static String b = BuildConfig.api;
    private static String c = a+b;

    public static String stringLogin() {
        return c + BuildConfig.login;
    }

    public static String stringLupaPassword() {
        return c + BuildConfig.lupa_password;
    }

    public static String stringLaporan() {
        return c + BuildConfig.list_laporan;
    }

    public static String stringUpdateProfile() {
        return c + BuildConfig.edit_profile;
    }

    public static String stringUbahPassword() {
        return c + BuildConfig.ubah_password;
    }
}
