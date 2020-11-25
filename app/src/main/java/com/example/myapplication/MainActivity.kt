package com.example.myapplication

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    val mainViewModel: IPCheckViewModel by viewModels()

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mainViewModel.result.observe(this, { result ->
            when (result) {
                IpCheckResult.NotValid -> binding.resultTextView.text =
                    getString(R.string.ip_address_not_valid)
                is IpCheckResult.Error -> binding.resultTextView.text = result.exception.message
                is IpCheckResult.Result -> {
                    val model: ResultUIModel = result.model
                    if (binding.ipEditText.text.isEmpty()) {
                        binding.ipEditText.setText(model.request)
                    }
                    binding.resultTextView.text = model.info
                }
            }
        })

        binding.checkButton.setOnClickListener {
            mainViewModel.check(binding.ipEditText.text.toString())
        }
    }
}

class ResultUIModel(
    val request: String,
    val info: String //TODO: add required fields
)

