package com.example.myapplication

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import junit.framework.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule


class IPCheckViewModelTest : BaseTest() {

    @get:Rule
    val testRule: TestRule = InstantTaskExecutorRule()

    private val ipCheckRepository: IpCheckRepository = mockk(relaxed = true)
    private val ipAddressVerifier: IpAddressVerifier = mockk(relaxed = true)

    private lateinit var cut: IPCheckViewModel

    @Test
    fun `If IP address is valid then get response from repository and if the response is successful then post data to UI`() {
        //given
        val address = ""
        coEvery { ipCheckRepository.getLatestCached() } returns null
        coEvery { ipCheckRepository.getInfo(address) } returns mockk(relaxed = true)
        coEvery { ipAddressVerifier.isValid(address) } returns true

        //when
        cut = IPCheckViewModel(ipCheckRepository, ipAddressVerifier)
        cut.check(address)

        //then
        coVerify { ipCheckRepository.getInfo(address) }
        assertTrue(cut.result.value is IpCheckResult.Result)
        assertTrue((cut.result.value as IpCheckResult.Result).model.request == address)
    }

}