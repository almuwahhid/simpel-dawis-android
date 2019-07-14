package id.ac.akakom.pkm.simpeldawis.utils.Dialogs

import android.content.Context
import android.view.View
import android.widget.LinearLayout
import android.widget.RelativeLayout
import id.ac.akakom.pkm.simpeldawis.R
import lib.almuwahhid.utils.DialogBuilder


class DialogImagePicker(context: Context?, onDialogImagePicker: OnDialogImagePicker?) : DialogBuilder(context, R.layout.dialog_image_picker) {
    internal var lay_dialogimagepicker: RelativeLayout? = null
    internal var lay_camera: LinearLayout? = null
    internal var lay_folder: LinearLayout? = null
    internal var onDialogImagePicker: OnDialogImagePicker? = null

    init {
        lay_dialogimagepicker = dialog.findViewById(R.id.lay_dialogimagepicker)
        lay_camera = dialog.findViewById(R.id.lay_camera)
        lay_folder = dialog.findViewById(R.id.lay_folder)

        this.onDialogImagePicker = onDialogImagePicker

        lay_camera!!.setOnClickListener(View.OnClickListener {
            dismiss()
            onDialogImagePicker!!.onCameraClick()
        })

        lay_folder!!.setOnClickListener(View.OnClickListener {
            dismiss()
            onDialogImagePicker!!.onFileManagerClick()
        })

        show()
    }

    public interface OnDialogImagePicker{
        fun onCameraClick();
        fun onFileManagerClick();
    }
}
