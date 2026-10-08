enum class Rol {
    ADMIN, EDITOR, VISITANTE
}

interface Autenticable {
    fun iniciarSesion(clave: String): Boolean
}

data class Usuario(val id: Int, val nombre: String, val password: String, val rol: Rol) : Autenticable {
    override fun iniciarSesion(clave: String): Boolean {
        return password == clave
    }
}

object GestorUsuarios {
    val usuarios = mutableListOf<Usuario>()

    fun agregar(usuario: Usuario) {
        usuarios.add(usuario)
        println("Se agrego a ${usuario.nombre}")
    }

    fun validarRol(usuario: Usuario) {
        when (usuario.rol) {
            Rol.ADMIN -> println("${usuario.nombre} puede crear, editar y eliminar")
            Rol.EDITOR -> println("${usuario.nombre} puede crear y editar")
            Rol.VISITANTE -> println("${usuario.nombre} solo puede ver")
        }
    }

    fun login(usuario: Usuario, clave: String) {
        if (usuario.iniciarSesion(clave)) {
            println("Bienvenido ${usuario.nombre}")
            validarRol(usuario)
        } else {
            println("Clave incorrecta")
        }
    }
}

fun main() {
    val usuario1 = Usuario(1, "ana", "1234", Rol.ADMIN)
    val usuario2 = Usuario(2, "Carlos", "abcd", Rol.EDITOR)
    val usuario3 = Usuario(3, "Lucia", "0000", Rol.VISITANTE)

    GestorUsuarios.agregar(usuario1)
    GestorUsuarios.agregar(usuario2)
    GestorUsuarios.agregar(usuario3)

    println()

    GestorUsuarios.login(usuario1, "1234")
    GestorUsuarios.login(usuario2, "abcd")
    GestorUsuarios.login(usuario3, "1111")

    println()
    println(GestorUsuarios.usuarios)
}
