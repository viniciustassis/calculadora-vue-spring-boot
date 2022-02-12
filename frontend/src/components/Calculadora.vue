<template>
    <container>
        <div class="d-flex  flex-column justify-center align-center">
            <h1>Calculadora</h1>
            <h3>Cálculo de dois números inteiros positivos</h3>
        </div>
        
        <div class="container">
            <div class="d-flex flex-column mx-auto container-texto" width="30vw">
                <div class="d-inline texto" height="30px">
                    {{ telaAcima }}
                </div>
            
                <div class="d-flex flex-row-reverse" height="40px">
                    <div class="mx-1">
                        <v-btn v-on:click="clickResultado('=')" :disabled="!!operadorNaoEscolhido" color="transparent"><i class="fas fa-equals white"></i></v-btn>
                    </div>
                    <div class="mx-1">
                        <v-btn v-on:click="reset()" color="transparent"><i class="fas fa-undo-alt white"></i></v-btn>
                    </div>
                    <div height="35px" class="align-self-center mx-4">
                        {{ telaAbaixo }}
                    </div>
                </div>
                
            </div>

            <v-card class="d-flex flex-column mx-auto" width="30vw" tile outlined>
                <v-card class="d-flex flex-row justify-center mx-auto my-2">
                    <v-card key="0">
                        <v-btn v-on:click="digitar(0)" :color="corBotao">0</v-btn>
                    </v-card>
                </v-card>
                <v-card class="d-flex flex-row justify-center mx-auto my-2">
                    <v-card
                        v-for="n in [7, 8, 9]"
                        :key="n"
                    >
                        <v-btn v-on:click="digitar(n)" :color="corBotao">{{ n }}</v-btn>
                    </v-card>
                </v-card>
                <v-card class="d-flex flex-row justify-center mx-auto my-2">
                    <v-card
                        v-for="n in [4, 5, 6]"
                        :key="n"
                    >
                        <v-btn v-on:click="digitar(n)" :color="corBotao">{{ n }}</v-btn>
                    </v-card>
                </v-card>
                <v-card class="d-flex flex-row justify-center mx-auto my-2">
                    <v-card
                        v-for="n in [1, 2, 3]"
                        :key="n"
                    >
                        <v-btn v-on:click="digitar(n)" :color="corBotao">{{ n }}</v-btn>
                    </v-card>
                </v-card>
                <v-card class="d-flex flex-row justify-center mx-auto my-5">
                    <v-card>
                        <v-btn v-on:click="clickOperador('+')" :color="corBotao" :disabled="verificarDigitos">+</v-btn>
                    </v-card>
                    <v-card>
                        <v-btn v-on:click="clickOperador('-')" :color="corBotao" :disabled="verificarDigitos">-</v-btn>
                    </v-card>
                    <v-card>
                        <v-btn v-on:click="clickOperador('*')" :color="corBotao" :disabled="verificarDigitos">*</v-btn>
                    </v-card>
                    <v-card>
                        <v-btn v-on:click="clickOperador('/')" :color="corBotao" :disabled="verificarDigitos">/</v-btn>
                    </v-card>
                </v-card>
            </v-card>
        </div>
    </container>
</template>

<script>
import CalculadoraService from "@/services/CalculadoraService.js";

export default {
    name: "Calculadora",

    data: () => ({
        telaAcima: " ",
        telaAbaixo: " ",
        corBotao: "rgb(36, 39, 39)",
        calculo: {
            num1: "",
            operador: "",
            num2: ""
        },
        operadorNaoEscolhido: true,
        calculadoraService: new CalculadoraService(),
    }),
    methods: {
        digitar: function (numero) {
            if(this.operadorNaoEscolhido) {
                this.calculo.num1 += numero;
            } else {
                this.calculo.num2 += numero;
            }
            
            this.telaAbaixo += numero;
        },
        clickOperador: function (operador) {
            if(!(this.operadorNaoEscolhido)) {
                return;
            }

            this.calculo.operador = operador;
            this.operadorNaoEscolhido = !this.operadorNaoEscolhido;
            this.telaAbaixo += " " + operador + " ";
        },
        clickResultado: async function (operador) {
            if(this.calculo.num1 == "" || this.calculo.num2 == "") return;

            this.telaAbaixo += " " + operador + " ";
            let expressao = this.telaAbaixo;
            this.telaAcima = expressao;

            var retorno;

            try {
                retorno = await this.calculadoraService.calcular(this.calculo);
                /* this.telaAcima += retorno.data.resultado; */
                this.telaAcima += retorno;
            } catch (error) {
                console.error(error);
            } finally {
                this.reset();
            }
            
        },
        reset: function() {
            this.telaAbaixo = " ";
            this.calculo = {num1: "", operador: "", num2: ""};
            this.operadorNaoEscolhido = true;
        }
    },

    computed: {
        verificarDigitos: function () {
            return this.calculo.num1 == "";
        }
    }
}
</script>

<style scoped>
*{
    background-color: white;    
}

body {
  font-family: 'Brush Script MT', cursive;
  font-weight: bold;
}

.container >>> .v-card{
    background-color: rgb(36, 39, 39);
    color: white;
}

.container >>> .v-btn {
    color: whitesmoke;
}

.container-texto {
    background: white;
    color: rgb(36, 39, 39);
    width: 30vw;
    height: 70px;
    border: groove 2px rgb(36, 39, 39);
    border-radius: 3px;
}

.texto {
    width: 100%;
    height: 100%;
}

i {
    color: black;
}
</style>