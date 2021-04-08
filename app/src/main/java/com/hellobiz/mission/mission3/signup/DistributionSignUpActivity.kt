package com.hellobiz.mission.mission3.signup

import android.Manifest
import android.R.attr
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.provider.Settings
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.bumptech.glide.Glide
import com.esafirm.imagepicker.features.ImagePicker
import com.esafirm.imagepicker.features.ImagePickerFragment
import com.esafirm.imagepicker.features.camera.DefaultCameraModule
import com.esafirm.imagepicker.features.camera.OnImageReadyListener
import com.esafirm.imagepicker.helper.ImagePickerPreferences
import com.gun0912.tedpermission.PermissionListener
import com.gun0912.tedpermission.TedPermission
import com.hellobiz.mission.R
import com.hellobiz.mission.databinding.ActivityDistributionSignUpBinding
import com.hellobiz.mission.error.model.ErrorRespose
import com.hellobiz.mission.mission3.signup.model.ProfileUpdateModel
import com.hellobiz.mission.mission3.signup.service.ProfileUpdateService
import com.hellobiz.mission.mission3.signup.signupinterface.ProfileUpdate
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File


class DistributionSignUpActivity : AppCompatActivity(), View.OnClickListener, ProfileUpdate {
    private var mBinding: ActivityDistributionSignUpBinding? = null
    private val binding get() = mBinding!!
    private val PICK_FROM_ALBUM = 1 //onActivityResult 에서 requestCode 로 반환되는 값
    private var tempFile: File? = null
    private lateinit var photoUri : Uri
    private val REQUEST_CODE_PICKER = 2
    private val RC_REQUEST_CAMERA = 3


//    private val launcher = registerImagePicker {
//        // handle result here
//    }
//    launcher.launch()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityDistributionSignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding()              //setOnEditorActionListener, setOnClickListener 를 등록
        signUpCreateEvent()    //버튼을 눌렀을 시

    }
//    fun ComponentActivity.registerImagePicker(
//        callback: ImagePickerCallback
//    ): ImagePickerLauncher {
//        return ImagePickerLauncher(this, createLauncher(callback))
//    }

    private fun imagePicker(){

        ImagePicker.create(this) // Activity or Fragment
            .folderMode(true).single()
            .start(REQUEST_CODE_PICKER)

        ImagePicker.create(this).getIntent(applicationContext)
    }



    /*
    startActivityForResult를 통해 다른 Activity로 이동한 후 다시 돌아오게 되면 onActivityResult 가 동작,
    이때 startActivityForResult 의 두번 째 파라미터로 보낸 값 (PICK_FROM_ALBUM)이 requestCode로 반환
    */
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        val cameraModule = DefaultCameraModule() // or ImmediateCameraModule  startActivityForResult(cameraModule.getIntent(context), RC_REQUEST_CAMERA);
        if (requestCode === RC_REQUEST_CAMERA && resultCode === RESULT_OK && attr.data != null) {
            cameraModule.getImage(applicationContext, data, OnImageReadyListener {
                // do what you want to do with the image ...

                // it's either List<Image> with one item or null (still need improvement)
            }
            )
        }
    }

    //이미지 url을 가지고  imageview에 띄워줌
    private fun setImage(){
        Glide.with(applicationContext) .load(photoUri) .into(binding.signupImageview)
    }


    private fun openAppSettings() {
        val intent = Intent(
            Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
            Uri.fromParts("package", this.packageName, null))
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
    }

    //Validation 구성
    private fun signUpCreateEvent() {
        binding.signupBtn.setOnClickListener {
            val emailRegex = Regex("^[_a-z0-9-]+(.[_a-z0-9-]+)*@(?:\\w+\\.)+\\w+$") //이메일 형식
            val pwRegex =
                Regex("^(?=.*[A-Za-z])(?=.*[0-9])(?=.*[\$@\$!%*#?&]).{8,}.\$")    //영문+숫자 8자이상 특수문자 1 포함
            val nameRegex = Regex("[a-zA-Z\\uac00-\\ud7a3]+")                     //한글 또는 영문만 가능
            val digitsRegex = Regex("^[0-9]+$")                                   //숫자만 가능


            //이메일이 비어있을 시
            if (binding.signupEmail.text.toString().isEmpty()) {
                return@setOnClickListener Toast.makeText(
                    applicationContext,
                    resources.getString(R.string.pleaseInputEmail),
                    Toast.LENGTH_SHORT
                ).show()
            }

            //이메일의 형식이 올바르지 않을 시
            if (binding.signupEmail.text.toString()
                    .isNotEmpty() && !binding.signupEmail.text.toString().matches(emailRegex)
            ) {
                return@setOnClickListener Toast.makeText(
                    applicationContext,
                    resources.getString(R.string.pleaseInputCorrectEmail),
                    Toast.LENGTH_SHORT
                ).show()
            }

            //비밀번호가 비어있을 시
            if (binding.signupPw.text?.isEmpty()!!) {
                return@setOnClickListener Toast.makeText(
                    applicationContext,
                    resources.getString(R.string.pleaseInputPw),
                    Toast.LENGTH_SHORT
                ).show()
            }

            //비밀번호가 영문 또는 숫자 또는 특수문자가 아니거나 10자리 미만일 시
            if (binding.signupPw.text?.isNotEmpty()!! && !binding.signupPw.text.toString()
                    .matches(pwRegex)
            ) {
                return@setOnClickListener Toast.makeText(
                    applicationContext,
                    resources.getString(R.string.pleaseEnterCorrectPw),
                    Toast.LENGTH_SHORT
                ).show()
            }

            //확인비밀번호가 비어있을 시
            if (binding.confirmPw.text.toString().isEmpty()) {
                return@setOnClickListener Toast.makeText(
                    applicationContext,
                    resources.getString(R.string.pleaseInputConfirmPw),
                    Toast.LENGTH_SHORT
                ).show()
            }

            //입력한 두 비밀번호가 다를 시
            if (binding.signupPw.text.toString() != binding.confirmPw.text.toString()) {
                Toast.makeText(
                    applicationContext,
                    resources.getString(R.string.passwordsEnteredAreIncorrect),
                    Toast.LENGTH_SHORT
                )
                    .show()
                return@setOnClickListener
            }

            //이름이 비어있을 시
            if (binding.signupName.text.toString().isEmpty()) {
                return@setOnClickListener Toast.makeText(
                    applicationContext,
                    resources.getString(R.string.pleaseInputName),
                    Toast.LENGTH_SHORT
                ).show()
            }

            //이름에 특수문자 있을 시
            if (binding.signupName.text.toString()
                    .isNotEmpty() && !binding.signupName.text.toString().matches(nameRegex)
            ) {
                return@setOnClickListener Toast.makeText(
                    applicationContext,
                    resources.getString(R.string.pleaseInputCorrectName),
                    Toast.LENGTH_SHORT
                ).show()
            }

            //전화번호가 비어있을 시
            if (binding.signupPhonenumber.text.toString().isEmpty()) {
                return@setOnClickListener Toast.makeText(
                    applicationContext,
                    resources.getString(R.string.pleaseInputDigits),
                    Toast.LENGTH_SHORT
                ).show()
            }

            //전화번호가 올바르지 않을 경우
            if ((binding.signupPhonenumber.text.toString()
                    .isNotEmpty() && !binding.signupPhonenumber.text.toString()
                    .matches(digitsRegex)) || binding.signupPhonenumber.text.toString().length < 11
            ) {
                return@setOnClickListener Toast.makeText(
                    applicationContext,
                    resources.getString(R.string.pleaseInputCorrectDigits),
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                try {
                    //                getModificationService()
                    if(tempFile==null){
                        Toast.makeText(applicationContext, "사진을 선택해 주세요", Toast.LENGTH_SHORT).show()
                    }
                    else {
                        getProfileUpdateService()
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }

            }
        }
    }

    //키보드를 내려주는 메소드
    private fun hideKeyBoard() {
        val imm = this.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(binding.signupEmail.windowToken, 0)
    }

    //이너 클래스 리스너를 이용해서 setOnEditorActionListener를 사용
    inner class EnterListener : TextView.OnEditorActionListener {
        override fun onEditorAction(p0: TextView?, p1: Int, p2: KeyEvent?): Boolean {
            hideKeyBoard()
            return true
        }
    }

    //해당 edittext에서 엔터를 입력 시 키보드가 내려가게 리스너 설정
    private fun binding() {
        val listener = EnterListener()
        //edittext에 action 리스너 등록
        binding.signupEmail.setOnEditorActionListener(listener)
        binding.signupPw.setOnEditorActionListener(listener)
        binding.confirmPw.setOnEditorActionListener(listener)
        binding.signupName.setOnEditorActionListener(listener)
        binding.signupPhonenumber.setOnEditorActionListener(listener)

        //imageview에 click 리스너 등록
        binding.signupFindImageview.setOnClickListener(this)
        binding.signupImageview.setOnClickListener(this)
    }

    //권한 요청 설정
    private fun tedPermission() {
        val permissionListener: PermissionListener = object : PermissionListener {
            override fun onPermissionGranted() {
                imagePicker()   //앨범으로 이동
            }

            override fun onPermissionDenied(deniedPermissions: MutableList<String>?) {
                Toast.makeText(
                    this@DistributionSignUpActivity,
                    resources.getString(R.string.permission_failed),
                    Toast.LENGTH_LONG
                ).show()
            }

        }
        TedPermission.with(this)
            .setPermissionListener(permissionListener)
            .setRationaleMessage(resources.getString(R.string.permission_2))
            .setDeniedMessage(resources.getString(R.string.permission_1))
            .setPermissions(
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE
            )
            .check()
    }

    //앨범으로 이동
    private fun goToAlbum() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = MediaStore.Images.Media.CONTENT_TYPE

        //startActivityForResult : 새 액티비티를 열어줌 + 결과값 전달 (쌍방향)
        startActivityForResult(intent, PICK_FROM_ALBUM)

    }

    override fun onClick(v: View?) {
        when (v) {
            binding.signupImageview -> {
                tedPermission() //권한 요청 설정
            }
            binding.signupFindImageview -> {
                tedPermission() //권한 요청 설정
            }
        }
    }

    companion object {
        const val TAG = "DistributionSignUpActivity"
    }

//    private fun getModificationService() {
//        val modificationService = ModificationService(this)
//        modificationService.getModificationService(
//            2,
//            binding.signupName.text.toString(),
//            binding.signupPhonenumber.text.toString(),
//            binding.signupEmail.text.toString(),
//            binding.signupPw.text.toString()
//        )
//    }


    //참고 : https://velog.io/@dev_thk28/Android-Retrofit2-Multipart%EC%82%AC%EC%9A%A9%ED%95%98%EA%B8%B0-Java
    //파라미터를 넣어 프로필 업데이트 서비스 연동하여 PATCH 진행
    private fun getProfileUpdateService() {
        val profileUpdateService = ProfileUpdateService(this)

        //api에 들어가야 하는 RequestBody 데이터 타입으로 변경
        val nameBody: RequestBody =
            binding.signupName.text.toString().toRequestBody("text/plain".toMediaTypeOrNull())
        val ageBody: RequestBody = binding.signupPhonenumber.text.toString()
            .toRequestBody("text/plain".toMediaTypeOrNull())
        val pwBody: RequestBody =
            binding.signupPw.text.toString().toRequestBody("text/plain".toMediaTypeOrNull())
        val emailBody: RequestBody =
            binding.signupEmail.text.toString().toRequestBody("text/plain".toMediaTypeOrNull())

        //map 에 서버에서 요청한 key 값에 맞춰서 담아줌
        val paramsMap: MutableMap<String, RequestBody> = HashMap()
        paramsMap.put("MEM_NAME", nameBody)
        paramsMap.put("MEM_TEL", ageBody)
        paramsMap.put("MEM_PASS", pwBody)
        paramsMap.put("MEM_EMAIL", emailBody)

        //파라미터 전달 및 서비스 연결
        tempFile?.let {     //tempFile 이 null 이 아니여야함
            profileUpdateService.getProfileUpdateService(
                2,
                paramsMap,
                it
            )
        }
    }

    override fun profileUpdateSuccess(profileUpdateModel: ProfileUpdateModel?) {
        when (profileUpdateModel?.code) {
            200 -> {
                Toast.makeText(applicationContext, "서버 연동 성공", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun profileUpdateError(errorResponse: ErrorRespose) {
    }

    override fun profileUpdateFailure(message: Throwable?) {
    }




}


