package com.dirion.walltechtodo.data

import android.util.Log
import com.dirion.walltechtodo.data.datasource.localSource.LocalDataSource
import com.dirion.walltechtodo.data.datasource.localSource.room.Task
import com.dirion.walltechtodo.data.datasource.remote.NetworkDataSource
import com.dirion.walltechtodo.data.mapper.MapperResponse
import com.dirion.walltechtodo.data.models.network.rest.CLIENT
import com.dirion.walltechtodo.data.models.network.rest.ORDER
import com.dirion.walltechtodo.data.models.network.rest.TIPOGRAPHY
import com.dirion.walltechtodo.data.models.network.rest.USER
import com.dirion.walltechtodo.data.models.network.rest.request.post.PostAddTaskModelRequest
import com.dirion.walltechtodo.data.models.network.rest.request.put.PutUpdateTaskModelRequest
import com.dirion.walltechtodo.data.models.network.rest.response.BaseModelResponse.*
import com.dirion.walltechtodo.domain.models.BaseDomainModel
import com.dirion.walltechtodo.domain.models.TaskModelDomain
import com.dirion.walltechtodo.domain.repository.IOrdersRepository
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import javax.inject.Inject

class OrdersRepository @Inject constructor(
    val networkDataSource: NetworkDataSource,
    val localDataSource:  LocalDataSource,
    val firestore: FirebaseFirestore
): IOrdersRepository {

    companion object {
        const val USERS_LOG = "log"
        const val USERS_LIST = "users"
        const val TIPOGRAPHY = "tipography"
        const val TYPE_SOB = "type_sob"
        const val CLIENTS = "clients"
        const val ORDERS = "orders"
    }

    override suspend fun fetchOrders(): Flow<List<ORDER>> {
        //val response = networkDataSource.getTasksList()
        val orders = getAllOrders().await()
        Log.d("TasksRepository", "${orders.toList()}")
        return flowOf(orders)
    }

    override suspend fun addTask(name: String, status: String) {
        val requestModel = PostAddTaskModelRequest(title = name, status = status)
        val response = networkDataSource.addTask(requestModel)

        if (response is Success) {
                val data = Task(
                    id = response.data.id,
                    title = response.data.title,
                    status = response.data.status,
                )

        }

    }

    override suspend fun login(username: String, password: String): BaseDomainModel<Int> {

        try {
            val role = findUserTokenByLoginPassword(username, password)
            return BaseDomainModel.Success(role)
        } catch (e: Exception) {
            return BaseDomainModel.Error(e.message.toString())
        }
    }

    override suspend fun updateOrder(
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
    ): Unit = withContext(Dispatchers.IO) {
        val db = firestore.collection(ORDERS)

        val employerToken = findEmployerByLogin(employerLogin).await()

        val customerToken = findCustomerByPassport(customerPassport).await()

        val typographyToken = findTypographyByName(tipographyName).await()

        val map = mapOf(
            Pair("acceptance_date", acceptance_date),
            Pair("binding_type", binding_type),
            Pair("completion_date", completion_date),
            Pair("customer", customerToken),
            Pair("edition", edition),
            Pair("employer", employerToken),
            Pair("format", ORDER.Format.convertToString(format)),
            Pair("pages_count", pages_count),
            Pair("paper_type", paper_type),
            Pair("prepayment", prepayment),
            Pair("printing", printing),
            Pair("product_price", product_price),
            Pair("product_type", product_type),
            Pair("tipography_id", typographyToken)
        )

        db.document(id).update(
            map
        )

    }

    override suspend fun getOrder(id: String): ORDER {
        val db = firestore.collection(ORDERS)

        return getAllOrders().await()
            .filter {
                it.id == id
            }
            .first()

    }

    override suspend fun deleteOrder(id: String) {
        val db = firestore.collection(ORDERS)

        db.document(id).delete()

    }

    override suspend fun deleteTypography(id: String) {
        val db = firestore.collection(TIPOGRAPHY)

        db.document(id).delete()
    }

    override suspend fun loginFirebase(): USER {
        val db = firestore.collection(USERS_LIST)
        db.get().addOnCompleteListener {
            it.result.documents.iterator().forEach {
                val user = USER(
                    midle_name = it.data?.get("midle_name")!!.toString(),
                    name = it.data?.get("name")!!.toString(),
                    role = it.data?.get("role")!!.toString().toInt(),
                    surname = it.data?.get("surname")!!.toString(),
                    tipography_id = it.data?.get("tipography_id")!!.toString().toInt(),
                )
                Log.d("TasksRepository", user.toString())

            }
        }

        return USER("","", "", 0, "", 1)
    }

    // fetch all users tokens
    override suspend fun getAllUsersFromFirebase(): List<String> {
        val usersList = mutableListOf<String>()
        val db = firestore.collection(USERS_LOG)

        db.get().addOnCompleteListener {
            it.result.documents.iterator().forEach {
                usersList.add(it.id)
            }
        }

        return usersList
    }

    //login
    override suspend fun findUserTokenByLoginPassword(login: String, password: String) = withContext(Dispatchers.IO) {
        val db = firestore.collection(USERS_LIST)

        return@withContext async {

            db.get()
                .await()
                .documents
                .filter { documentSnapshot ->
                    documentSnapshot.data?.get("login") == login && documentSnapshot.data?.get("password") == password
                }
                .first()
                .also { doc->
                    localDataSource.insertData(
                        id = doc.id.toString(),
                        login = doc.data?.get("login").toString(),
                        name = doc.data?.get("name").toString(),
                        surname = doc.data?.get("surname").toString(),
                        middleName = doc.data?.get("midle_name").toString()
                    )
                }
                .get("role")
                .toString().toInt()
        }.await()
    }

    // fetch all typography
    suspend fun getAllTypography() = withContext(Dispatchers.IO) {
        val db = firestore.collection(TIPOGRAPHY)

        return@withContext async {
            db.get()
                .await()
                .documents
                .map { doc ->
                    TIPOGRAPHY(
                        hood =  doc.data?.get("hood").toString(),
                        name =  doc.data?.get("name").toString(),
                        phone =  doc.data?.get("phone").toString(),
                        type =  getTypeSob(doc.data?.get("type_id").toString()).await(),
                        year =  doc.data?.get("year").toString(),
                    )
                }
        }.await()

    }
    private suspend fun getTypeSob(id: String) = withContext(Dispatchers.IO) {
        val db = firestore.collection(TYPE_SOB)

        return@withContext async {
            db.get()
                .await()
                .documents
                .filter {
                    it.data?.get("id").toString() == id
                }
                .first()
                .get("name")
                .toString()
        }
    }

    //fetch all clients
    suspend fun getAllClients() = withContext(Dispatchers.IO) {
        val db = firestore.collection(CLIENTS)

        return@withContext async {
            db.get()
                .await()
                .documents
                .map { doc ->
                    CLIENT(
                        name = doc.data?.get("name").toString(),
                        middlename = doc.data?.get("middlename").toString(),
                        passport = doc.data?.get("passport").toString(),
                        surname = doc.data?.get("surname").toString(),
                        account = doc.data?.get("account").toString(),
                        address = doc.data?.get("address").toString(),
                        phone = doc.data?.get("phone").toString(),
                        birthdate = doc.data?.get("birthdate").toString().toInt(),
                        bank = doc.data?.get("bank").toString(),

                    )
                }

        }.await()

    }

    suspend fun getAllOrders() = withContext(Dispatchers.IO){
        val db = firestore.collection(ORDERS)

        return@withContext async {
            db.get()
                .await()
                .documents
                .map { doc ->
                    ORDER(
                        id = doc.id,
                        acceptance_date = doc.data?.get("acceptance_date").toString(),
                        binding_type = doc.data?.get("binding_type").toString(),
                        completion_date = doc.data?.get("completion_date").toString(),
                        customer = getClientNamesByToken(doc.data?.get("customer").toString()).await(),
                        edition = doc.data?.get("edition").toString(),
                        employer = getUserNamesByToken(doc.data?.get("employer").toString()).await(),
                        format = doc.data?.get("edition").toString(),
                        pages_count = doc.data?.get("pages_count").toString().toInt(),
                        paper_type = doc.data?.get("paper_type").toString(),
                        prepayment = doc.data?.get("prepayment").toString().toBoolean(),
                        printing = doc.data?.get("printing").toString().toInt(),
                        product_price = doc.data?.get("product_price").toString().toInt(),
                        product_type = doc.data?.get("product_type").toString(),
                        tipography_id = getTypographyNameById(doc.data?.get("tipography_id").toString()).await(),
                    )
                }
        }

    }
    private suspend fun getUserNamesByToken(token: String) = withContext(Dispatchers.IO) {
        val db = firestore.collection(USERS_LIST)
        Log.d("TasksRepository", "${token}")

        return@withContext async {
            val user = db.get()
                .await()
                .documents
                .filter {
                    it.id == token
                }
                .first()


            val names = "${user.get("surname").toString()} " +
                    "${user.get("name").toString().first().uppercaseChar()}." +
                    "${user.get("midle_name").toString().first().uppercaseChar()}."

            return@async names

        }
    }

    private suspend fun getClientNamesByToken(token: String) = withContext(Dispatchers.IO) {
        val db = firestore.collection(CLIENTS)

        return@withContext async {
            val user = db.get()
                .await()
                .documents
                .filter {
                    it.id ==  token
                }
                .first()


            val names = "${user.get("surname").toString()} " +
                    "${user.get("name").toString().first().uppercaseChar()}." +
                    "${user.get("middlename").toString().first().uppercaseChar()}."

            return@async names

        }
    }

    override suspend fun getTypographyNameById(id: String) = withContext(Dispatchers.IO) {
        val db = firestore.collection(TIPOGRAPHY)
        return@withContext async {
            db.get()
                .await()
                .documents
                .filter {
                    it.id == id
                }
                .first()
                .data
                ?.get("name")
                .toString()
        }

    }

    override suspend fun getMyOrders(): List<ORDER> = withContext(Dispatchers.IO) {
        val surname = localDataSource.getSurname()
        val name = localDataSource.getName()
        val middleName = localDataSource.getMiddleName()

        return@withContext getAllOrders().await()
            .filter {
                val names = "${surname} " +
                        "${name.first().uppercaseChar()}." +
                        "${middleName.first().uppercaseChar()}."
                it.employer == names
            }

    }

    // add //
    override suspend fun addTypography(hood: String, name: String, phone: String, typeId: String, year: String): Unit = withContext(Dispatchers.IO) {
        val db = firestore.collection(TIPOGRAPHY)

        val map = mapOf(
            Pair("hood", hood),
            Pair("name", name),
            Pair("phone", phone),
            Pair("type_id", typeId),
            Pair("year", year),
        )


        db.add(map)
    }

    override suspend fun getCustomersList(): List<CLIENT> = withContext(Dispatchers.IO) {
        val db = firestore.collection(CLIENTS)

        return@withContext async {
            db.get()
                .await()
                .documents
                .map {
                    CLIENT(
                        name = it.data?.get("name").toString(),
                        middlename = it.data?.get("middlename").toString(),
                        passport = it.data?.get("passport").toString(),
                        surname = it.data?.get("surname").toString(),
                        account = it.data?.get("account").toString(),
                        bank = it.data?.get("bank").toString(),
                        birthdate = it.data?.get("birthdate").toString().toInt(),
                        phone = it.data?.get("phone").toString(),
                        address = it.data?.get("address").toString(),
                    )
                }
        }.await()
    }

    override suspend fun getAllSimpleUsers(): List<USER> = withContext(Dispatchers.IO){
        val db = firestore.collection(USERS_LIST)

        return@withContext db.get()
            .await()
            .documents
            .map { doc ->
                USER(
                    name = doc.data?.get("name").toString(),
                    midle_name = doc.data?.get("midle_name").toString(),
                    surname = doc.data?.get("surname").toString(),
                    login = doc.data?.get("login").toString(),
                    typName = getTypographyNameById(
                        doc.data?.get("typ_id").toString()
                    ).await()
                )
            }

    }

    suspend fun addClient(
        account: String,
        address: String,
        bank: String,
        birthdate: Int,
        middlename: String,
        name: String,
        passport: String,
        phone: String,
        surname: String,
    ) = withContext(Dispatchers.IO) {
        val db = firestore.collection(CLIENTS)

        db.add(CLIENT(
            account = account,
            address = address,
            bank = bank,
            birthdate = birthdate,
            middlename = middlename,
            name = name,
            passport = passport,
            phone = phone,
            surname = surname
        ))
    }

    override suspend fun addOrder(
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
    ): Unit = withContext(Dispatchers.IO) {
        val db = firestore.collection(ORDERS)
        Log.d("TasksRepository", "" +
                "acceptance_date: $acceptance_date \n" +
                "binding_type: $binding_type \n" +
                "customerPassport: $customerPassport\n" +
                "edition: $edition\n" +
                "employerLogin: $employerLogin\n" +
                "format: $format\n" +
                "pages_count: $pages_count\n" +
                "paper_type: $paper_type\n" +
                "prepayment: $prepayment\n" +
                "printing: $printing\n" +
                "product_price: $product_price\n" +
                "product_type: $product_type\n" +
                "tipographyName: $tipographyName\n"
        )
        //find employer
        val employerToken = findEmployerByLogin(employerLogin).await()

        val customerToken = findCustomerByPassport(customerPassport).await()

        val typographyToken = findTypographyByName(tipographyName).await()

        val map = mapOf(
                Pair("acceptance_date", acceptance_date),
                Pair("binding_type", binding_type),
                Pair("completion_date", completion_date),
                Pair("customer", customerToken),
                Pair("edition", edition),
                Pair("employer", employerToken),
                Pair("format", ORDER.Format.convertToString(format)),
                Pair("pages_count", pages_count),
                Pair("paper_type", paper_type),
                Pair("prepayment", prepayment),
                Pair("printing", printing),
                Pair("product_price", product_price),
                Pair("product_type", product_type),
                Pair("tipography_id", typographyToken)
            )



        db.add(
            map
        )

    }

    override suspend fun getAllEmployers(): List<Pair<String, String>> = withContext(Dispatchers.IO) {
        val db = firestore.collection(USERS_LIST)

        return@withContext async {
            db.get()
                .await()
                .documents
                .map {doc ->
                    val login = doc.data?.get("login").toString()
                    val name = "${doc.data!!["surname"]} ${doc.data!!["name"].toString()[0].uppercaseChar()}. " +
                            "${doc.data!!["midle_name"].toString()[0].uppercaseChar()}."

                    Pair(login, name)
                }

        }.await()
    }

    override suspend fun getAllTypographys(): List<String> = withContext(Dispatchers.IO){
        val db = firestore.collection(TIPOGRAPHY)

        return@withContext async {
            db.get()
                .await()
                .documents
                .map { doc ->
                    "${doc.data!!["name"]}"
                }
        }.await()
    }

    override suspend fun fetchAllTypographys(): List<TIPOGRAPHY> = withContext(Dispatchers.IO) {
        val db = firestore.collection(TIPOGRAPHY)
        return@withContext async {
            db.get()
                .await()
                .documents
                .map { doc ->
                    TIPOGRAPHY(
                        id = doc.id,
                        hood = doc.data?.get("hood").toString(),
                        name = doc.data?.get("name").toString(),
                        phone = doc.data?.get("phone").toString(),
                        type = doc.data?.get("type").toString(),
                        year = doc.data?.get("year").toString(),
                    )
                }
        }.await()
    }


    override suspend fun getAllCutomers(): List<String> = withContext(Dispatchers.IO){
        val db = firestore.collection(CLIENTS)

        return@withContext async {
            db.get()
                .await()
                .documents
                .map {
                    it.data!!.get("passport").toString()
                }
        }.await()

    }

    override suspend fun getCurrentUser(): USER {
        return USER(
            login = localDataSource.getLogin(),
            midle_name = localDataSource.getMiddleName(),
            name = localDataSource.getName(),
            role = 1,
            surname = localDataSource.getSurname(),
            tipography_id = 0
        )
    }


    private suspend fun findEmployerByLogin(employerLogin: String) = withContext(Dispatchers.IO) {
        val db = firestore.collection(USERS_LIST)

        return@withContext async {
            db.get()
                .await()
                .documents
                .filter { doc ->
                    doc.data?.get("login").toString() == employerLogin
                }
                .first()
                .id
        }

    }
    private suspend fun findCustomerByPassport(passport: String) = withContext(Dispatchers.IO) {
        val db = firestore.collection(CLIENTS)

        return@withContext async {
            db.get()
                .await()
                .documents
                .filter { doc ->
                    doc.data?.get("passport").toString() == passport
                }
                .first()
                .id
        }

    }
    private suspend fun findTypographyByName(name: String) = withContext(Dispatchers.IO) {
        val db = firestore.collection(TIPOGRAPHY)

        return@withContext async {
            db.get()
                .await()
                .documents
                .filter { doc ->
                    doc.data?.get("name").toString() == name
                }
                .first()
                .id
        }

    }

    private suspend fun findCorrectOrderByData(order: ORDER) = withContext(Dispatchers.IO) {
        val db = firestore.collection(ORDERS)

        return@withContext async {
            db.get()
                .await()
                .documents
                .filter {doc ->
                    doc.id == order.id
                }
                .first()
                .id
        }

    }


    // update
    suspend fun updateTypography(old: TIPOGRAPHY, new: TIPOGRAPHY) = withContext(Dispatchers.IO) {
        val db = firestore.collection(TIPOGRAPHY)
        val token = findTypographyByName(old.name).await()

        db.document(token)
            .update(
                mapOf(
                    Pair("hood", new.hood),
                    Pair("name", new.name),
                    Pair("phone", new.phone),
                    Pair("typeId", new.type),
                    Pair("year", new.year),
                )
            )

    }
    suspend fun updateCustomer(old: CLIENT, new: CLIENT) = withContext(Dispatchers.IO) {
        val db = firestore.collection(CLIENTS)
        val token = findCustomerByPassport(old.passport).await()
        db.document(token)
            .update(
                mapOf(
                    Pair("account", new.account),
                    Pair("address", new.address),
                    Pair("bank", new.bank),
                    Pair("birthdate", new.birthdate),
                    Pair("middlename", new.middlename),
                    Pair("name", new.name),
                    Pair("passport", new.passport),
                    Pair("phone", new.phone),
                    Pair("surname", new.surname)
                )
            )

    }

}
