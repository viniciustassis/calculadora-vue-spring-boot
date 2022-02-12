package com.example.springsoapproducercalculadora;

import javax.annotation.PostConstruct;

import com.example.calculadora.Calculadora;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Component
public class CalculadoraRepository {
	private static final Map<String, Calculadora> calculadoras = new HashMap<>();

    //private Calculadora calculadora = new Calculadora();

	@PostConstruct
	public void initData() {
		/* Calculadora calculadora = new Calculadora();
        calculadora.setNumero01(new BigDecimal(2));
        parametros.setNumero02(new BigDecimal(3));
		
        calculadora.setSoma(parametros);
        calculadora.setSubtracao(parametros);
        calculadora.setMultipicacao(parametros);
        calculadora.setDivisao(parametros);
		calculadoras.put("todas", calculadora); */
	}

	public Calculadora findCalculadora(String name) {
		Assert.notNull(name, "A operação must not be null");
		return calculadoras.get(name);
	}

	public int Somar(int numero01, int numero02) {
		int resultado = numero01 + numero02;
		return resultado;
	}

	public int Subtrair(int numero01, int numero02) {
		int resultado = numero01 - numero02;
		return resultado;
	}

	public int Multiplicar(int numero01, int numero02) {
		int resultado = numero01 * numero02;
		return resultado;
	}

	public float Dividir(int numero01, int numero02) {
		float resultado = 1f * numero01 / numero02;
		return resultado;
	}
}