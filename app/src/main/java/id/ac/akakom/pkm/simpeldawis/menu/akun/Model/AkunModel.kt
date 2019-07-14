package id.ac.akakom.pkm.simpeldawis.menu.akun.Model

import java.io.Serializable

data class AkunModel(
                     var title: String = "",
                     var content: String = "",
                     var icon: Int = 0,
                     var index: String = "",
                     var isEditable: Boolean = true
                    ): Serializable