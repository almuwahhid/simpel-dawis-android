package id.ac.akakom.pkm.simpeldawis.menu.akun

import android.Manifest
import android.app.Activity
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.support.annotation.NonNull
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.firebase.messaging.FirebaseMessaging
import com.theartofdev.edmodo.cropper.CropImage
import com.theartofdev.edmodo.cropper.CropImageView
import id.ac.akakom.pkm.simpeldawis.R
import id.ac.akakom.pkm.simpeldawis.data.Model.UserModel
import id.ac.akakom.pkm.simpeldawis.menu.akun.Model.AkunModel
import lib.almuwahhid.Activity.FragmentPermission
import id.ac.akakom.pkm.simpeldawis.utils.Function
import id.ac.akakom.pkm.simpeldawis.utils.Dialogs.DialogUbahPassword.DialogUbahPassword

import id.ac.akakom.pkm.simpeldawis.menu.akun.Helper.AkunHelper
import id.ac.akakom.pkm.simpeldawis.utils.avatarview.loader.PicassoLoader
import id.ac.akakom.pkm.simpeldawis.utils.avatarview.AvatarPlaceholder
import kotlinx.android.synthetic.main.activity_akun.*
import id.ac.akakom.pkm.simpeldawis.menu.login.LoginActivity
import id.ac.akakom.pkm.simpeldawis.utils.Dialogs.DialogEditUser.DialogEditUser
import lib.almuwahhid.utils.LibUi
import id.ac.akakom.pkm.simpeldawis.data.StringConstant
import id.ac.akakom.pkm.simpeldawis.utils.Dialogs.DialogEditPhoto.DialogEditPhoto
import lib.almuwahhid.utils.PermissionChecker
import pl.aprilapps.easyphotopicker.EasyImage
import id.ac.akakom.pkm.simpeldawis.utils.Dialogs.DialogImagePicker
import lib.almuwahhid.utils.AlertDialogBuilder
import pl.aprilapps.easyphotopicker.DefaultCallback
import java.io.File
import java.io.IOException

class AkunActivity : FragmentPermission(), AkunView.View {


    lateinit var userModel: UserModel
    lateinit var akunAdapter: AkunAdapter
    lateinit var dialogEditPhoto: DialogEditPhoto
    lateinit var list: MutableList<AkunModel>
    private val RequiredPermissions = arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE)
    protected var permissionChecker = PermissionChecker()


    override fun onUpdateUser() {
        userModel = Function.getUserPreference(context)
        initUser(userModel)
    }

    override fun onFailedUpdateUser(message: String) {
        LibUi.ToastShort(context, message)
    }

    override fun onErrorConnection() {

    }

    override fun onHideLoading() {
        LibUi.hideLoadingDialog(context)
    }

    override fun onLoading() {
        LibUi.showLoadingDialog(context, R.drawable.logo)
    }

    lateinit var presenter: AkunPresenter

    companion object {
        @JvmStatic fun newInstance(): AkunActivity{
            return AkunActivity()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter = AkunPresenter(context, this)
        list = ArrayList()
        akunAdapter = AkunAdapter(context!!, list, object : AkunAdapter.OnAkunADapter{
            override fun onItemClick(akunModel: AkunModel) {
                DialogEditUser(context, akunModel, object : DialogEditUser.OnDialogEditUser{
                    override fun onUpdateUser(akunModel: AkunModel?) {
                        var usm = UserModel(userModel.id,
                                            userModel.username,
                                            userModel.email,
                                            userModel.name,
                                            userModel.level,
                                            userModel.alamat,
                                            userModel.no_tlp,
                                            userModel.nama_divisi,
                                            userModel.foto,
                                            userModel.token
                                            )
                        when(akunModel!!.index){
                            StringConstant.ID_EMAIL -> {
                                usm.email = akunModel.content
                            }
                            StringConstant.ID_NAME -> {
                                usm.name = akunModel.content
                            }
                            StringConstant.ID_USERNAME -> {
                                usm.username = akunModel.content
                            }
                            StringConstant.ID_ALAMAT -> {
                                usm.alamat = akunModel.content
                            }
                            StringConstant.ID_NOTELP -> {
                                usm.no_tlp = akunModel.content
                            }
                        }
                        usm.foto = "-"
                        presenter.updateUser(usm)
                    }
                })
            }
        })

        userModel = Function.getUserPreference(context)
        rv_biodata.layoutManager = LinearLayoutManager(context)
        rv_biodata.adapter = akunAdapter

        lay_logout.setOnClickListener({
            AlertDialogBuilder(context, "Anda yakin ingin logout?", "Ya", "Tidak", object : AlertDialogBuilder.OnAlertDialog{
                override fun onPositiveButton(dialog: DialogInterface?) {
                    var sub = "D";
                    if(Function.getUserPreference(context).level == 1)
                        sub = "P"
                    else
                        sub = "D"
                    FirebaseMessaging.getInstance().unsubscribeFromTopic(sub)
                    FirebaseMessaging.getInstance().unsubscribeFromTopic("A")
                    Function.logoutUser(context)
                    startActivity(Intent(context, LoginActivity::class.java))
                    activity!!.finish()
                }

                override fun onNegativeButton(dialog: DialogInterface?) {

                }

            })
        })

        card_photo.setOnClickListener({
            dialogEditPhoto = DialogEditPhoto(context, userModel, object : DialogEditPhoto.OnDialogEditPhoto{
                override fun onUpdateUser(isUpdate: Boolean, umodel: UserModel?) {
                    if(isUpdate)
                        presenter.updateUser(umodel!!)
                }

                override fun onOpenCamera() {
                    permissionChecker.verifyPermissions(activity, RequiredPermissions, object : PermissionChecker.VerifyPermissionsCallback {
                        override fun onPermissionAllGranted() {
                            DialogImagePicker(context, object : DialogImagePicker.OnDialogImagePicker{
                                override fun onFileManagerClick() {
                                    EasyImage.openGallery(this@AkunActivity, 0)
                                }

                                override fun onCameraClick() {
                                    EasyImage.openCamera(this@AkunActivity, 0)
                                }

                            })
                        }

                        override fun onPermissionDeny(permissions: Array<String>) {
                            Toast.makeText(context, "Please grant required permissions.", Toast.LENGTH_LONG).show()
                        }
                    })
                }

            })
        })

        lay_ubahpassword.setOnClickListener({
            DialogUbahPassword(context)
        })
        initUser(userModel)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.activity_akun, container, false)
        return view
    }

    private fun initUser(userModel: UserModel){
        list.clear()
        list.addAll(AkunHelper.listAkun(userModel))
        akunAdapter.notifyDataSetChanged()

        val imageLoader = PicassoLoader()
        var x = Function.getUserPreference(context).name
        var y = Function.getUserPreference(context).foto

        if(Function.getUserPreference(context).foto.equals("")){
            y = "http://google.com"
        }
        val refreshableAvatarPlaceholder = AvatarPlaceholder(x)
        imageLoader.loadImage(avatarView, refreshableAvatarPlaceholder, y)
        tv_name.setText(userModel.name)
        tv_divisi.setText(userModel.nama_divisi)
        tv_email.setText(userModel.email)

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, resultData: Intent?) {
        Log.d("ChatroomFragment ", ""+resultCode+" -- "+resultData)
//        resultData != null &&
        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE -> {
                    val result = CropImage.getActivityResult(resultData)
                    if (resultCode === Activity.RESULT_OK) {
                        val resultUri = result.uri
//                        showFileAttachmentDialog(resultUri)
                        handleCropResult(resultUri)
                    } else if (resultCode === CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                        val error = result.error
                        Toast.makeText(context, ""+error, Toast.LENGTH_SHORT).show()
                    }
                }
                else -> {
                    EasyImage.handleActivityResult(requestCode, resultCode, resultData, activity, object : DefaultCallback() {
                        override fun onImagePickerError(e: Exception?, source: EasyImage.ImageSource?, type: Int) {
                            //Some error handling
                            e!!.printStackTrace()
                        }

                        override fun onImagesPicked(imageFiles: List<File>, source: EasyImage.ImageSource, type: Int) {
                            println(imageFiles)
                            try {
                                val imageUri = Uri.fromFile(imageFiles[0])
                                var bitmapImage = MediaStore.Images.Media.getBitmap(context!!.getContentResolver(), imageUri)
                                if (bitmapImage != null) {
                                    startCropActivity(Uri.fromFile(imageFiles[0]))
                                }

                            } catch (e: IOException) {
                                e.printStackTrace()
                            }

                        }

                        override fun onCanceled(source: EasyImage.ImageSource?, type: Int) {
                            //Cancel handling, you might wanna remove taken photo if it was canceled
                            if (source == EasyImage.ImageSource.CAMERA) {
                                val photoFile = EasyImage.lastlyTakenButCanceledPhoto(context!!)
                                photoFile?.delete()
                            }
                        }
                    })
                }
            }
        }
    }

    private fun startCropActivity(uri: Uri){
        CropImage.activity(uri)
                .setAspectRatio(1,1)
                .setCropShape(CropImageView.CropShape.RECTANGLE)
                .start(context!!, this);
    }

    private fun handleCropResult(@NonNull resultUri: Uri) {
        if (resultUri != null) {
            try {
                val bitmapVisa: Bitmap = MediaStore.Images.Media.getBitmap(context!!.contentResolver, resultUri)
                dialogEditPhoto.updatePhoto(bitmapVisa)
            } catch (e: IOException) {
                e.printStackTrace()
            }

        } else {
            Toast.makeText(context, "Cannot Retrieve Cropped Image", Toast.LENGTH_SHORT).show()
        }
    }
}
