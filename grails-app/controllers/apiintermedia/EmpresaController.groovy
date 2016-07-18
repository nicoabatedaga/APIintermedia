package apiintermedia

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

    def post() {

    }

    def update() {

    }

    def delete() {

    }

}
