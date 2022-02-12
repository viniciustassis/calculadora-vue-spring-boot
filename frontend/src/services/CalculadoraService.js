import axios from 'axios';

// import xml2js Module
import { parseString } from "xml2js"; 

class CalculadoraService {
    calcular = async (data) => {
        try {
            let operacao;

            switch (data.operador) {
                case "+":
                    operacao = "getSomaRequest";
                    break;
                case "-":
                    operacao = "getSubtracaoRequest";
                    break;
                case "*":
                    operacao = "getMultiplicacaoRequest";
                    break;
                case "/":
                    operacao = "getDivisaoRequest";
                    break;
            }
            
            let xmls = `<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"
                                    xmlns:gs="http://example.com/calculadora">
                        <soapenv:Header/>
                        <soapenv:Body>
                            <gs:${operacao}>
                                <gs:numero01>${data.num1}</gs:numero01>
                                <gs:numero02>${data.num2}</gs:numero02>
                            </gs:${operacao}>
                        </soapenv:Body>
                        </soapenv:Envelope>`;
            /* let xmls = '<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"'+
                                    'xmlns:gs="http://example.com/calculadora">'+
                        '<soapenv:Header/>'+
                        '<soapenv:Body>'+
                            '<'+operacao+'>'+
                                '<numero01>'+data.num1+'</numero01>'+
                                '<numero02>'+data.num2+'</numero02>'+
                            '</'+operacao+'>'+
                        '</soapenv:Body>'+
                        '</soapenv:Envelope>'; */
            const options = {
                method: "POST",
                url: "http://localhost:8080/ws/",
                /* body: xmls, */
                data: xmls,
                headers: {
                    'Content-Type': 'text/xml'
                },
            };
            
            var xmldata = await axios(options);
            var dados;
            
            // parsing xml data
            parseString(xmldata.data, function (err, results) {
                // parsing to json
                let data = JSON.stringify(results)
                
                // display the json data
                dados = data;
            });
            
            var texto = dados.split("ns2:resultado\":[\"");
            var resultado = texto[1].split("\"");
            return resultado[0];
            /* url: "http://localhost:8080/ws/calculadora.wsdl", */
            /* "Access-Control-Allow-Origin": "*" */
            
        } catch (error) {
            console.error(error)
        }
    }
}

export default CalculadoraService;

