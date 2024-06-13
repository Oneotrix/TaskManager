package com.dirion.walltechtodo.domain.repository

import com.dirion.walltechtodo.data.models.network.rest.CLIENT
import com.dirion.walltechtodo.data.models.network.rest.ORDER
import com.dirion.walltechtodo.data.models.network.rest.TIPOGRAPHY
import com.dirion.walltechtodo.data.models.network.rest.USER
import com.dirion.walltechtodo.domain.models.BaseDomainModel
import com.google.firebase.inject.Deferred
import kotlinx.coroutines.flow.Flow

interface IOrdersRepository {
     suspend fun fetchOrders() : Flow<List<ORDER>>

     suspend fun addTask(name: String, status: String)

     suspend fun login(username: String, password: String) : BaseDomainModel<Int>

     suspend fun updateOrder(
          id: String,
          acceptance_date: String,
          binding_type: String,
          completion_date: String,
          customerPassport: String,
          edition: String,
          employerLogin: String,
          format: ORDER.Format,
          pages_count: Int,
          paper_type: String,
          prepayment: Boolean,
          printing: Int,
          product_price: Int,
          product_type: String,
          tipographyName: String
     )

     suspend fun getOrder(id: String) : ORDER

     suspend fun deleteOrder(id: String)

     suspend fun deleteTypography(id: String)

     suspend fun loginFirebase() : USER

     suspend fun getAllUsersFromFirebase() : List<String>

     suspend fun findUserTokenByLoginPassword(login: String, password: String) : Int

     suspend fun addOrder(
          acceptance_date: String,
          binding_type: String,
          completion_date: String,
          customerPassport: String,
          edition: String,
          employerLogin: String,
          format: ORDER.Format,
          pages_count: Int,
          paper_type: String,
          prepayment: Boolean,
          printing: Int,
          product_price: Int,
          product_type: String,
          tipographyName: String
     )

     suspend fun getAllEmployers() : List<Pair<String, String>>
     suspend fun getAllTypographys() : List<String>
     suspend fun fetchAllTypographys() : List<TIPOGRAPHY>

     suspend fun getAllCutomers() : List<String>

     suspend fun getCurrentUser() : USER

     suspend fun addTypography(hood: String, name: String, phone: String, typeId: String, year: String)

     suspend fun getCustomersList(): List<CLIENT>

     suspend fun getAllSimpleUsers() : List<USER>

     suspend fun getTypographyNameById(id: String) : kotlinx.coroutines.Deferred<String>

     suspend fun getMyOrders() : List<ORDER>
}