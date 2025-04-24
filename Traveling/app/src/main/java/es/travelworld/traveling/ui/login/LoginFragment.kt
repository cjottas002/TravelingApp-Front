package es.travelworld.traveling.ui.login

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import dagger.hilt.android.AndroidEntryPoint
import es.travelworld.traveling.R
import es.travelworld.traveling.databinding.FragmentLoginBinding
import es.travelworld.traveling.ui.home.HomeActivity
import es.travelworld.traveling.ui.register.RegisterActivity

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private var isPasswordVisible = false
    private val loginVM: LoginViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initToolbar()
        initUser()
        init()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initToolbar() {
        (activity as? LoginActivity)?.hideToolbar()
    }

    private fun initUser() {
        loginVM.initializeLoginUser("admin", "admin")
        binding.loginviewmodel = loginVM
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun init() {

        binding.ibLogin.setOnClickListener {
            loginVM.login(
                onSuccess = {
                    val intent = Intent(activity, HomeActivity::class.java).apply {
                        putExtra("USERNAME", loginVM.username.value)
                        putExtra("PASSWORD", loginVM.password.value)
                    }
                    startActivity(intent)
                    requireActivity().finish()
                },
                onError = { message ->
                    AlertDialog.Builder(requireContext())
                        .setTitle("Error")
                        .setMessage(message)
                        .setPositiveButton("OK") { dialog, _ -> dialog.dismiss() }
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show()
                }
            )
        }

        binding.etPassword.setOnTouchListener { v, event ->
            if (event.action == MotionEvent.ACTION_UP) {
                if (event.rawX >= (binding.etPassword.right - binding.etPassword.compoundDrawables[2].bounds.width())) {
                    togglePasswordVisibility(binding.etPassword)
                    v.performClick()
                    true
                } else {
                    false
                }
            } else {
                false
            }
        }

        val activityResultLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == Activity.RESULT_OK && result.data != null) {
                    val data = result.data
                    val username = data?.getStringExtra("username") ?: ""
                    val password = data?.getStringExtra("password") ?: ""

                    loginVM.initializeLoginUser(username, password)
                    binding.etUsername.setText(username)
                    binding.etPassword.setText(password)
                }
            }

        binding.tvCreateAccount.setOnClickListener {
            val intentRegister = Intent(activity, RegisterActivity::class.java)
            activityResultLauncher.launch(intentRegister)
        }

        binding.tvForgotPassword.setOnClickListener {
            Snackbar.make(it, "This feature will be implemented soon", Snackbar.LENGTH_SHORT).show()
        }
    }

    private fun togglePasswordVisibility(passwordEditText: TextInputEditText) {

        val (transformation, iconRes) = if (isPasswordVisible) {
            PasswordTransformationMethod.getInstance() to R.drawable.login_ic_lock
        } else {
            HideReturnsTransformationMethod.getInstance() to R.drawable.login_ic_lock_open
        }

        passwordEditText.transformationMethod = transformation
        passwordEditText.setCompoundDrawablesWithIntrinsicBounds(0, 0, iconRes, 0)
        passwordEditText.setSelection(passwordEditText.text?.length ?: 0)
        isPasswordVisible = !isPasswordVisible
    }
}
