package apiintermedia

import grails.converters.JSON
import groovyx.net.http.ContentType
import groovyx.net.http.HTTPBuilder
import static groovyx.net.http.ContentType.URLENC
import groovyx.net.http.Method

class EmpresaController {

    def getAll() {
        println("entra al getAll")
        String html = 'http://localhost:8080/APIdePrueba/empresas'.toURL().text
        render html
    }

    def get() {
        def empresa_id = getParams()["empresa_id"] as Integer
        String html = "http://localhost:8080/APIdePrueba/empresas/${empresa_id}".toURL().text
        println("http://localhost:8080/APIdePrueba/empresas/${empresa_id}")
        render html
    }

    def save() {
        def getParams = request.getJSON()
        def empresa_id = getParams["empresa_id"] as Integer
        def empresa_nombre = getParams["empresa_nombre"] as String

//        def postBody = [empresa_id: empresa_id, empresa_nombre: empresa_nombre]

        def http = new HTTPBuilder( 'http://localhost:8080/APIdePrueba/empresas')
        http.request(Method.POST , ContentType.JSON) {
            uri.path = ''
            requestContentType = ContentType.JSON
//            body = JSON.stringify({'empresa_id': empresa_id, 'empresa_nombre': empresa_nombre})
            body = ([empresa_id: empresa_id, empresa_nombre: empresa_nombre] as JSON).toString()
//            println("body : : $bod")
            response.success = { resp ->
//                println "Success! ${resp.status}"
                println("success, response: $resp.properties")
            }

            response.failure = { resp ->
                println "Request failed with status ${resp.status}, response $resp.properties"
            }

            render([response: response.properties, status: response.status] as JSON)
        }

//        http.post(path: '/', requestContentType: URLENC , body: postBody){resp ->
//            println("POST success: ${resp.status}")
//        }
    }

    def update() {

    }

    def delete() {

    }

}
