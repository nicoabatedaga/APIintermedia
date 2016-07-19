package apiintermedia

import groovyx.net.*

import grails.converters.JSON

import javax.servlet.http.HttpServletResponse

class PersonaController {

    def get() {
        def empresa_id = getParams()["empresa_id"] as Integer
       def persona_dni = getParams()["persona_dni"] as Integer
        String html = "http://localhost:8080/APIdePrueba/empresas/${empresa_id}/personas/${persona_dni}".toURL().text
        render html
    }

    def getAll(){
        def empresa_id = getParams()["empresa_id"] as Integer
        String html = "http://localhost:8080/APIdePrueba/empresas/${empresa_id}/personas".toURL().text
        render html
    }

    def save() {

    }

    def update() {

    }

    def delete() {

    }

}
