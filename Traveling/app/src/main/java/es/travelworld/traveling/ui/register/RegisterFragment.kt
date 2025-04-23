package es.travelworld.traveling.ui.register

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import es.travelworld.traveling.databinding.FragmentRegisterBinding
import kotlinx.coroutines.launch

@AndroidEntryPoint
class RegisterFragment : Fragment() {

    private lateinit var binding: FragmentRegisterBinding
    private val viewModel: RegisterViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        observeViewModel()
    }

    private val requestCameraPermission = registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
        if (isGranted) {
            openCamera()
        } else {
            Toast.makeText(context, "Camera permission denied", Toast.LENGTH_SHORT).show()
        }
    }

    private val captureImage = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK && result.data != null) {
            val imageBitmap = result.data?.getParcelableExtra<Bitmap>("data")
            imageBitmap?.let {
                binding.profileImage.setImageBitmap(it)
            }
        }
    }

    private fun init() {
        setupTextWatchers()
        setupAgeAutoCompleteTextView()
        setupFocusErrorHandlers()
        setupListeners()
    }

    private fun setupTextWatchers() {
        binding.bRegister.isEnabled = false

        val textWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val nameInput = binding.etName.text?.toString()?.trim() ?: ""
                val lastNameInput = binding.etLastName.text?.toString()?.trim() ?: ""
                viewModel.onNameOrLastNameChanged(nameInput, lastNameInput)
            }

            override fun afterTextChanged(s: Editable?) {}
        }

        binding.etName.addTextChangedListener(textWatcher)
        binding.etLastName.addTextChangedListener(textWatcher)
    }

    private fun setupAgeAutoCompleteTextView() {
        val ageRanges = arrayOf("0-5", "6-11", "12-17", "18-99")
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_dropdown_item_1line, ageRanges)
        binding.acAge.setAdapter(adapter)

        binding.acAge.setOnItemClickListener { _, _, position, _ ->
            val adult = position >= 3
            viewModel.setIsAdult(adult)
            binding.acAge.error = if (!adult) "This app is not for you" else null
        }
    }

    private fun setupFocusErrorHandlers() {
        binding.etName.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus && binding.etName.text?.isNotEmpty() == true) {
                binding.etName.error = if (viewModel.isNombreValid.value)
                    null else "Por favor, introduce un nombre válido"
            }
        }
        binding.etLastName.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus && binding.etLastName.text?.isNotEmpty() == true) {
                binding.etLastName.error = if (viewModel.isApellidosValid.value)
                    null else "Por favor, introduce unos apellidos válidos"
            }
        }
    }

    private fun setupListeners() {
        binding.tvTerms.setOnClickListener {
            val url = "https://developers.google.com/ml-kit/terms"
            val intent = Intent(Intent.ACTION_VIEW, url.toUri())
            startActivity(intent)
        }

        binding.bRegister.setOnClickListener {
            var valid = true
            if (!viewModel.isNombreValid.value) {
                binding.etName.error = "Nombre inválido"
                valid = false
            }
            if (!viewModel.isApellidosValid.value) {
                binding.etLastName.error = "Apellidos inválidos"
                valid = false
            }
            if (!valid) return@setOnClickListener

            viewModel.register(
                onSuccess = {
                    val resultIntent = Intent().apply {
                        putExtra("username", binding.etName.text.toString())
                        putExtra("password", binding.etLastName.text.toString())
                    }
                    requireActivity().setResult(Activity.RESULT_OK, resultIntent)
                    requireActivity().finish()
                },
                onError = { message ->
                    Snackbar.make(binding.root, message, Snackbar.LENGTH_LONG).show()
                }
            )
        }

        binding.cameraIcon.setOnClickListener {
            requestCameraPermission.launch(Manifest.permission.CAMERA)
        }
    }

    private fun openCamera() {
        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        if (cameraIntent.resolveActivity(requireActivity().packageManager) != null) {
            captureImage.launch(cameraIntent)
        }
    }

    private fun observeViewModel() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModel.isRegisterEnabled.collect { enabled ->
                        binding.bRegister.isEnabled = enabled
                    }
                }
            }
        }
    }
}
