package apiintermedia

import grails.converters.*
import groovy.json.JsonOutput
import org.apache.commons.collections.Closure
import org.codehaus.groovy.grails.web.json.*; // package containing JSONObject,  JSONArray,...
import groovyx.net.http.*
import groovyx.net.http.HTTPBuilder
import groovyx.net.http.Method
import static groovyx.net.http.ContentType.*
import static groovyx.net.http.Method.*

class EmpresaController {

    def http = new HTTPBuilder("http://localhost:8080/APIdePrueba/")


    def getAll() {
        println("entra al GetAll de empresa")

        http.request(Method.GET , ContentType.JSON){
            uri.path = 'empresas/'
            contentType = 'application/json'

            response.success = {resp,json ->
                println("resp : ${resp.properties}")
                println("json : ${json}")

                render (json as JSON)
            }

            response.failure = {resp ->
                println("resp : ${resp.properties}")

                render ("Ocurrio un error :: status:${resp.status}")
            }
        }

        /*http.get(
                path:"empresas/",
                contentType: ContentType.JSON
        ){resp , reader ->
            println("resp : ${resp.properties}")
            println("reader : ${reader.properties}")
        }*/

//        try {
//            def resp = http.get(path:"empresas")
//
//            println("sale del GetAll successful")
//        } catch(HttpResponseException e) {
//            def r = e.response
//            println("Success: $r.success")
//            println("Status:  $r.status")
//            println("Reason:  $r.statusLine.reasonPhrase")
//            println("Content: \n${JsonOutput.prettyPrint(JsonOutput.toJson(r.data))}")
//            println("sale del GetAll por catch")
//        }
//        println(getResponse().getHeader('data'))

        render ""
    }

    def get() {
        println("entra al Get de empresa")
        def empresa_id = getParams()["empresa_id"] as Integer

        try {
            def resp = http.get(
                    path:"empresas/${empresa_id}",
                    requestContentType: ContentType.JSON,
                    headers: [
                            'Accept':'application/json',
                            'Content-Type':'application/json'
                    ]
            )
            println("respuesta: ${resp.getData()}")
//            render(response.properties)
            println("sale del Get successful")
        } catch(HttpResponseException e) {
            def r = e.response
            println("Success: $r.success")
            println("Status:  $r.status")
            println("Reason:  $r.statusLine.reasonPhrase")
            println("Content: \n${JsonOutput.prettyPrint(JsonOutput.toJson(r.data))}")
            println("sale del Get por catch")
        }

        render ""
    }

    def save() {
        println("entra al save de empresa")
        def getParams = request.getJSON()
        def empresa_id = getParams["empresa_id"] as Integer
        def empresa_nombre = getParams["empresa_nombre"] as String

        try {
            def resp = http.post(
                    path:'empresas',
                    requestContentType: ContentType.JSON,
                    headers: [
                            'Accept':'application/json',
                            'Content-Type':'application/json'
                    ],
                    body:[
                            empresa_id: empresa_id,
                            empresa_nombre: empresa_nombre
                    ]
            )
            println("respuesta: ${resp.getData()}")
//            render(response.properties)
            println("sale del Save successful")
        } catch(HttpResponseException e) {
            def r = e.response
            println("Success: $r.success")
            println("Status:  $r.status")
            println("Reason:  $r.statusLine.reasonPhrase")
            println("Content: \n${JsonOutput.prettyPrint(JsonOutput.toJson(r.data))}")
            println("sale del Save por catch")
        }

        render ""

    }

    def update() {

    }

    def delete() {

    }

}
