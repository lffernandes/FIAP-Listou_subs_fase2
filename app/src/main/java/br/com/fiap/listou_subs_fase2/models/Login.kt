package br.com.fiap.listou_subs_fase2.models

class Login {
    constructor(user: String, password: String) {
        this.user = user
        this.password = password
    }

    var user: String
    var password: String
}