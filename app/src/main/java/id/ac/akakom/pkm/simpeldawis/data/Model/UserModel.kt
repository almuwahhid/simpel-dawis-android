package id.ac.akakom.pkm.simpeldawis.data.Model

import java.io.Serializable

data class UserModel(var id: String = "",
                     var username: String = "dikdik_simpeldarwis",
                     var email: String = "dikdik@simpeldarwis.com",
                     var name: String = "Dikdik",
                     var level: Int = 0,
//                     var nip: String = "155410092",
                     var alamat: String = "Yogyakarta",
                     var no_tlp: String = "0878876776",
                     var nama_divisi: String = "Divisi Keuangan",
                     var foto: String = "",
                     var token: String = ""
) : Serializable