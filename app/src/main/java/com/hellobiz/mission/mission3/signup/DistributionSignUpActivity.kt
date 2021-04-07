package com.hellobiz.mission.mission3.signup

import android.Manifest
import android.R.attr
import android.content.Context
import android.content.Intent
import android.content.pm.ActivityInfo
import android.database.Cursor
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import com.gun0912.tedpermission.PermissionListener
import com.gun0912.tedpermission.TedPermission
import com.hellobiz.mission.BuildConfig
import com.hellobiz.mission.R
import com.hellobiz.mission.databinding.ActivityDistributionSignUpBinding
import com.hellobiz.mission.error.model.ErrorRespose
import com.hellobiz.mission.mission3.signup.model.ModificationModel
import com.hellobiz.mission.mission3.signup.model.SignUpModel
import com.hellobiz.mission.mission3.signup.service.ModificationService
import com.hellobiz.mission.mission3.signup.service.SignUpService
import com.hellobiz.mission.mission3.signup.signupinterface.EmployeeModification
import com.hellobiz.mission.mission3.signup.signupinterface.SignUp
import okio.ByteString.Companion.toByteString
import java.io.File
import java.lang.Exception


class DistributionSignUpActivity : AppCompatActivity(), View.OnClickListener,EmployeeModification {
    private var mBinding: ActivityDistributionSignUpBinding? = null
    private val binding get() = mBinding!!
    private val PICK_FROM_ALBUM = 1 //onActivityResult 에서 requestCode 로 반환되는 값
    private var tempFile: File? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityDistributionSignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding()              //setOnEditorActionListener, setOnClickListener 를 등록
        signUpCreateEvent()    //버튼을 눌렀을 시

    }

    /*
    startActivityForResult를 통해 다른 Activity로 이동한 후 다시 돌아오게 되면 onActivityResult 가 동작,
    이때 startActivityForResult 의 두번 째 파라미터로 보낸 값 (PICK_FROM_ALBUM)이 requestCode로 반환
    */
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == PICK_FROM_ALBUM) {   //onActivityResult 의 requestCode 값이 PICK_FROM_ALBUM 이면 해당 로직이 실행
            val photoUri: Uri? =
                data?.data     //data.data() 를 통해 갤러리에서 선택한 이미지의 Uri를 content:///형태로 저장
            var cursor: Cursor? = null
            try {
                val proj = arrayOf(MediaStore.Images.Media.DATA)    //MediaStore에서 미디어 파일 정보를 담아줌
                Log.i(TAG, "proj = $proj")  //[Ljava.lang.String;@6478cd7

                /**
                query : 데이터베이스에 저장된 데이터를 얻기 위해서 데이터베이스 시스템에 정보를 요청,
                우리는 그것을 "데이터베이스에 쿼리(Query)"한다고 말할 수 있음
                (원하는 데이터를 얻기 위해 데이터베이스에 정보를 요청(Request)하는 것)
                SQLite 데이터베이스에서 그 요청(Request)은 "SELECT" 문을 사용하여 작성할 수 있음.

                cursor : 사용자가 현재 주시하고 있는 위치에 대한 표시 키보드 커서 또는 마우스 커서 등이 대표적
                데이터베이스에 저장된 데이터를 쿼리하면 그 결과 데이터는, 한 개의 레코드만 가지거나,
                또는 여러 개의 레코드가 포함된 레코드 집합(RecordSet)임. 이 때 레코드 집합(RecordSet)에
                들어 있는 개별 레코드에 접근하여 그 값을 확인할 수 있는 기능을 제공해주는 것이 바로 커서(Cursor)임
                참조 : https://recipes4dev.tistory.com/120
                 **/

                //cursor를 통해 Uri 스키마를 content:/// 에서 file:/// 로  변경
                cursor = photoUri?.let { contentResolver.query(it, proj, null, null, null) }
                Log.i(
                    TAG,
                    "cursor = $cursor"
                )  // android.content.ContentResolver$CursorWrapperInner@ffc30ad

                if (cursor == null || cursor.getCount() < 1) {     //커서가 null(아무것도 미선택 되었을때 뒤로가기를 눌렀을 경우)
                    return                                          //원래 화면으로 return
                }

                //column의 index를 가져옴
                val column_index: Int = cursor!!.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
                Log.i(TAG, "column_index = $column_index")  //0

                //cursor를 제일 첫번째 행(Row)으로 이동 시킨다.
                cursor.moveToFirst()

                //Uri로 부터 FilePath를 가져옴
                tempFile = File(cursor.getString(column_index))
                Log.i(TAG, "tempFile = $tempFile")  ///storage/emulated/0/DCIM/Camera/cat.jpg

            } finally {
                cursor?.close()
            }
            setImage()   //이미지 포맷을 decode하여 bitmap으로 변환시켜 imageview에 띄워줌
        }
    }

    //이미지 포맷을 decode하여 bitmap으로 변환시켜 imageview에 띄워줌
    private fun setImage() {
        val options = BitmapFactory.Options()
        val originalBm = BitmapFactory.decodeFile(tempFile?.absolutePath, options)
        binding.signupImageview.setImageBitmap(originalBm)
    }

    //"/^(?=.*[A-Za-z])(?=.*\\d)(?=.*[\$@\$!%*#?&])[A-Za-z\\d\$@\$!%*#?&]{10,}\$/"
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
                    getSignUpService()
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
                goToAlbum()   //앨범으로 이동
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
                tedPermission()    //권한 요청 설정
            }
            binding.signupFindImageview -> {
                tedPermission()    //권한 요청 설정
            }
        }
    }

    companion object {
        const val TAG = "DistributionSignUpActivity"
    }

    private fun getSignUpService() {
        val patchSignUpService = ModificationService(this)
        patchSignUpService.getModificationService(
            2,
            binding.signupName.text.toString(),
            binding.signupPhonenumber.text.toString(),
            binding.signupEmail.text.toString(),
            binding.signupPw.text.toString()
        )
    }

    override fun modificationSuccess(modificationModel: ModificationModel?) {
            when(modificationModel?.code){
                200->{
                    Toast.makeText(applicationContext,"패치성공",Toast.LENGTH_SHORT).show()
                }
            }
    }

    override fun modificationError(errorResponse: ErrorRespose) {

    }

    override fun modificationFailure(message: Throwable?) {

    }
}

