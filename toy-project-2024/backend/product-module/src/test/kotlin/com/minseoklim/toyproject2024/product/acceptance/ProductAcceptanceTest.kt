package com.minseoklim.toyproject2024.product.acceptance

import com.minseoklim.toyproject2024.auth.acceptance.AuthAcceptanceTestFixture.`로그인 요청`
import com.minseoklim.toyproject2024.auth.acceptance.AuthAcceptanceTestUtil.extractAccessToken
import com.minseoklim.toyproject2024.member.acceptance.MemberAcceptanceTestFixture.`회원 가입 요청`
import com.minseoklim.toyproject2024.product.acceptance.ProductAcceptanceTestFixture.`상품 등록 요청`
import com.minseoklim.toyproject2024.product.acceptance.ProductAcceptanceTestFixture.`상품 등록됨`
import com.minseoklim.toyproject2024.product.acceptance.ProductAcceptanceTestFixture.`상품 목록 조회 요청`
import com.minseoklim.toyproject2024.product.acceptance.ProductAcceptanceTestFixture.`상품 목록 조회됨`
import com.minseoklim.toyproject2024.product.acceptance.ProductAcceptanceTestFixture.`상품 삭제 요청`
import com.minseoklim.toyproject2024.product.acceptance.ProductAcceptanceTestFixture.`상품 삭제됨`
import com.minseoklim.toyproject2024.product.acceptance.ProductAcceptanceTestFixture.`상품 수정 요청`
import com.minseoklim.toyproject2024.product.acceptance.ProductAcceptanceTestFixture.`상품 수정됨`
import com.minseoklim.toyproject2024.product.acceptance.ProductAcceptanceTestFixture.`상품 재고 감소 요청`
import com.minseoklim.toyproject2024.product.acceptance.ProductAcceptanceTestFixture.`상품 재고 감소됨`
import com.minseoklim.toyproject2024.product.acceptance.ProductAcceptanceTestFixture.`상품 재고 추가 요청`
import com.minseoklim.toyproject2024.product.acceptance.ProductAcceptanceTestFixture.`상품 재고 추가됨`
import com.minseoklim.toyproject2024.product.acceptance.ProductAcceptanceTestFixture.`상품 조회 요청`
import com.minseoklim.toyproject2024.product.acceptance.ProductAcceptanceTestFixture.`상품 조회됨`
import com.minseoklim.toyproject2024.test.AcceptanceTest
import com.minseoklim.toyproject2024.test.util.TestUtil.extractId
import org.junit.jupiter.api.Test

class ProductAcceptanceTest : AcceptanceTest() {

    @Test
    fun `상품 관리 서비스`() {
        // given
        val memberJoinRequest = mapOf(
            "loginId" to MEMBER_ID,
            "password" to PASSWORD,
            "name" to "testName",
            "email" to "test@test.com"
        )
        `회원 가입 요청`(memberJoinRequest)

        // given
        val loginRequest = mapOf(
            "loginId" to MEMBER_ID,
            "password" to PASSWORD
        )
        val loginResponse = `로그인 요청`(loginRequest)
        val accessToken = loginResponse.extractAccessToken()

        // given
        val productRegisterRequest = mapOf(
            "name" to "testProduct",
            "price" to 1000,
            "stockQuantity" to 100
        )

        // when
        val productRegisterResponse = `상품 등록 요청`(accessToken, productRegisterRequest)

        // then
        `상품 등록됨`(productRegisterResponse)

        // when
        val productListResponse = `상품 목록 조회 요청`(accessToken)

        // then
        `상품 목록 조회됨`(productListResponse)

        // given
        val productId = productRegisterResponse.extractId()

        // when
        val productResponse = `상품 조회 요청`(accessToken, productId)

        // then
        `상품 조회됨`(productResponse)

        // given
        val productUpdateRequest = mapOf(
            "name" to "updatedProduct",
            "price" to 2000
        )

        // when
        val productUpdateResponse = `상품 수정 요청`(accessToken, productId, productUpdateRequest)

        // then
        `상품 수정됨`(productUpdateResponse)

        // given
        val stockQuantityAddRequest = mapOf(
            "increment" to 100
        )

        // when
        val stockQuantityAddResponse = `상품 재고 추가 요청`(accessToken, productId, stockQuantityAddRequest)

        // then
        `상품 재고 추가됨`(stockQuantityAddResponse)

        // given
        val stockQuantityRemoveRequest = mapOf(
            "decrement" to 50
        )

        // when
        val stockQuantityRemoveResponse = `상품 재고 감소 요청`(accessToken, productId, stockQuantityRemoveRequest)

        // then
        `상품 재고 감소됨`(stockQuantityRemoveResponse)

        // when
        val productDeleteResponse = `상품 삭제 요청`(accessToken, productId)

        // then
        `상품 삭제됨`(productDeleteResponse)
    }

    companion object {
        private const val MEMBER_ID = "test1234"
        private const val PASSWORD = "password"
    }
}
