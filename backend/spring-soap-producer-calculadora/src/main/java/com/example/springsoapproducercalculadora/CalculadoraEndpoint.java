package com.example.springsoapproducercalculadora;

import com.example.calculadora.GetSomaRequest;
import com.example.calculadora.GetSomaResponse;
import com.example.calculadora.GetSubtracaoRequest;
import com.example.calculadora.GetSubtracaoResponse;
import com.example.calculadora.GetMultiplicacaoRequest;
import com.example.calculadora.GetMultiplicacaoResponse;
import com.example.calculadora.GetDivisaoRequest;
import com.example.calculadora.GetDivisaoResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class CalculadoraEndpoint {

	//private static final String NAMESPACE_URI = "http://spring.io/guides/gs-producing-web-service";
	private static final String NAMESPACE_URI = "http://example.com/calculadora";

	/* private CountryRepository countryRepository;

	@Autowired
	public CalculadoraEndpoint(CountryRepository countryRepository) {
		this.countryRepository = countryRepository;
	} */

    private CalculadoraRepository calculadoraRepository;
    @Autowired
	public CalculadoraEndpoint(CalculadoraRepository calculadoraRepository) {
		this.calculadoraRepository = calculadoraRepository;
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getSomaRequest")
	@ResponsePayload
	public GetSomaResponse GetResultado(@RequestPayload GetSomaRequest request) {
		GetSomaResponse response = new GetSomaResponse();

		System.out.println("getCalculadoraRequest");
		//response.setCountry(countryRepository.findCountry(request.getName()));
        
		/* BigDecimal resultado = calculadora.findCalculadora("todas").getSoma().getNumero01();
		resultado.add(calculadora.findCalculadora("todas").getSoma().getNumero02()); */
		int resultado = calculadoraRepository.Somar(request.getNumero01(), request.getNumero02());;
		response.setResultado(resultado);
		
		return response;
	}
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getSubtracaoRequest")
	@ResponsePayload
	public GetSubtracaoResponse GetResultado(@RequestPayload GetSubtracaoRequest request) {
		GetSubtracaoResponse response = new GetSubtracaoResponse();

		int resultado = calculadoraRepository.Subtrair(request.getNumero01(), request.getNumero02());;
		response.setResultado(resultado);

		return response;
	}
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getMultiplicacaoRequest")
	@ResponsePayload
	public GetMultiplicacaoResponse GetResultado(@RequestPayload GetMultiplicacaoRequest request) {
		GetMultiplicacaoResponse response = new GetMultiplicacaoResponse();

		int resultado = calculadoraRepository.Multiplicar(request.getNumero01(), request.getNumero02());;
		response.setResultado(resultado);

		return response;
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getDivisaoRequest")
	@ResponsePayload
	public GetDivisaoResponse GetResultado(@RequestPayload GetDivisaoRequest request) {
		GetDivisaoResponse response = new GetDivisaoResponse();

		float resultado = calculadoraRepository.Dividir(request.getNumero01(), request.getNumero02());;
		response.setResultado(resultado);

		return response;
	}
}