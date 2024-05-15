package com.dam2.proyectocliente.repositories

import com.dam2.proyectocliente.models.Role
import com.dam2.proyectocliente.network.SignUpApi
import com.dam2.proyectocliente.network.request.SignUpRequest
import com.dam2.proyectocliente.network.request.VerifyRequest
import com.dam2.proyectocliente.utils.Painter
import java.time.LocalDate

class SignUpRepository {
    suspend fun existField(value: String): Boolean {
        return SignUpApi.retrofitService.exit(VerifyRequest(value))
    }

    suspend fun signUp(fields: MutableMap<String, String>, avatar: Int): Boolean {

        val date = try {
            LocalDate.of(
                fields["anioNac"]!!.toInt(),
                fields["mesNac"]!!.toInt(),
                fields["diaNac"]!!.toInt()
            )
        }catch (e: Exception){
            null
        }

        val newUser = SignUpRequest(
            email = fields["mail"]!!,
            role = Role.valueOf(fields["rol"]!!),
            password = fields["password"]!!,
            name = fields["nombre"]!!,
            surname = fields["apellido1"]!!,
            secondSurname = fields["apellido2"]!!,
            birthDate = date,
            profilePicture = Painter.getProfilePictureName(avatar),
            company = fields["nombreEmpresa"]!!,
            cif = if (fields["cif"]!! != "") fields["cif"] else null,
            address = fields["direccion"]!!
        )
        return SignUpApi.retrofitService.signUp(newUser)
    }
}