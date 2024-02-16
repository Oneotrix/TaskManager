package com.dirion.walltechtodo.view.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.dirion.walltechtodo.databinding.FragmentLoginBinding
import com.dirion.walltechtodo.data.ApiServiceWalltechtodo
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit

class LoginFragment: Fragment() {

    private lateinit var binding: FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater, container, false)

        return binding.root
    }


    fun getRetrofit(): Retrofit {
        val contentType = "application/json".toMediaType()

        val retrofit = Retrofit.Builder()
            .baseUrl(ApiServiceWalltechtodo.BASE_URL)
            .addConverterFactory(getJson().asConverterFactory(contentType))
            .client(getOkHttpClient())
            .build()

        retrofit.create(ApiServiceWalltechtodo::class.java)

        return retrofit
    }

    fun getOkHttpClient(): OkHttpClient {
        val client = OkHttpClient.Builder()
            .addInterceptor(getInterceptor())
            .build()

        return client

    }

    fun getInterceptor(): Interceptor {
        val interceptor = Interceptor { chain ->
            val  request = chain.request().newBuilder()
                .addHeader("Content-Type", "application/json")
                .addHeader("Authorization", "Testman1:123123")
                .build()
            return@Interceptor chain.proceed(request)
        }

        return interceptor
    }

    fun getJson(): Json {
        val json = Json { ignoreUnknownKeys = true}

        return json
    }

    companion object {
        fun newInstance() = LoginFragment()
    }
}