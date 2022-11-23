package br.com.fiap.listou_subs_fase2.login

import br.com.fiap.listou_subs_fase2.MainActivity
import br.com.fiap.listou_subs_fase2.models.Login
import org.junit.Test
import org.mockito.Mockito

internal class MakeLoginTest {

    val main = Mockito.mock(MainActivity::class.java)
    val login = Mockito.mock(Login::class.java)

    @Test
    fun `Return false if username is not null`() {
        login.user = "Rick Sanchez"
        login.password = "wubalubadubdub"
        val result = main.validaLogin(login)[0]
       assert(result)
    }

    @Test
    fun `Return false if username is null`() {
        login.user = ""
        login.password = "wubalubadubdub"
        val result = main.validaLogin(login)[0]
        assert(!result)
    }

    @Test
    fun `Return true if password is not null`() {
        login.user = "Rick Sanchez"
        login.password = "wubalubadubdub"
        val result = main.validaLogin(login)[1]
        assert(result)
    }

    @Test
    fun `Return false if password is null`() {
        login.user = "Rick Sanchez"
        login.password = ""
        val result = main.validaLogin(login)[1]
        assert(!result)
    }
}