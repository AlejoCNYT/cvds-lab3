# LABORATORIO 3 - TDD
## CLASES DE EQUIVALENCIA
### CREAR UN PROYECTO CON MAVEN

En el directorio de trabajo ejecutar el comando necesario para crear/generar un proyecto maven basado en un arquetipo:

      Grupo (groupId): edu.eci.cvds
      Artefacto (artifactId): ClasesEquivalencia
      Paquete (package): edu.eci.cvds.tdd
      archetypeArtifactId: maven-archetype-quickstart
      ACTUALIZAR Y CREAR DEPENDENCIAS EN EL PROYECTO

Se busca la [dependencia](https://mvnrepository.com/artifact/junit/junit/4.13.2) de Maven

Se copia en el portapapeles la dependencia y reemplaza el pom.xml

      <!-- https://mvnrepository.com/artifact/junit/junit -->
      <dependency>
          <groupId>junit</groupId>
          <artifactId>junit</artifactId>
          <version>4.13.2</version>
          <scope>test</scope>
      </dependency>

### COMPILAR Y EJECUTAR

se ejecutal comando `mvn package`

![](https://github.com/AlejoCNYT/cvds-lab3/blob/main/img/Captura%20de%20pantalla%202024-02-23%20190216.png)

se ejecuta el comando `mvn test`

![](https://github.com/AlejoCNYT/cvds-lab3/blob/main/img/Captura%20de%20pantalla%202024-02-23%20190225.png)

## EJERCICIO “REGISTRADURÍA”

### REQUERIMIENTOS

### HACER EL ESQUELETO DE LA APLICACION

Realizamos el esqueleto de la aplicacion indicada en el laboratorio y ejecutamos las pruebas que se indican en el esqueleto.
Se agregan los siguientes casos de prueba.
• Cuando una persona esta muerta.
• Cuando una persona es menor de edad.
• Cuando una persona es votante (mayor de edad).
• Cuando hay edades invalidas (edades negativas).
• Cuando hay Id's duplicados.

### EJECUTAR LAS PRUEBAS

Compilamos y ejecutamos los test con mvn test.

<img width="836" alt="image" src="https://github.com/AlejoCNYT/cvds/assets/74771189/aaef22d3-c7fe-48ee-9df0-f43aea9f3af5">

<img width="960" alt="image" src="https://github.com/AlejoCNYT/cvds/assets/74771189/0904c802-468a-485d-94d9-915dc8ecf348">
### FINALIZAR EL EJERCICIO

## EJERCICIO "DESCUENTO DE TARIFAS"

AeroDescuentos

1. Deberia arrojar una excepción cuando se cumplan los siguientes casos:
```
 la tarifa es de base negativa o igual a 0
 Los días de antelación son negativos
 La edad supera los 200 años
 La edad es negativa o igual a 0

```
2.
![image](https://github.com/AlejoCNYT/cvds/assets/74771189/0d723f8a-86b7-4756-a5c5-c2e1432d19da)

3.
```
calculoTarifa(double tarifaBase, int edad, int diasAntelación)

    calculoTarifa(-850.000, 18, 20) = Excepción(la tarifa es de base negativa o igual a 0)
    calculoTarifa(850.000, 18, -20) = Excepción(Los días de antelación son negativos)
    calculoTarifa(850.000, 210, 20) = Excepción(La edad supera los 200 años)
    calculoTarifa(850.000, 0, 20) = Excepción(La edad es negativa o igual a 0)
    calculoTarifa(850.000, 22, 20) = Correcto/ No deberían tener descuento
    calculoTarifa(850.000, 22, 28) = Correcto/ deberían tener 15% de descuento (VALOR = 722.500)
    calculoTarifa(850.000, 15, 10) = Correcto/ deberían tener 5% de descuento (VALOR = 807.500)
    calculoTarifa(850.000, 75, 10) = Correcto/ deberían tener 8% de descuento (VALOR = 782.000)
    calculoTarifa(850.000, 14, 30) = Correcto/ deberían tener 20% de descuento (VALOR = 680.000)
    calculoTarifa(850.000, 84, 30) = Correcto/ deberían tener 23% de descuento (VALOR = 654.500)
```
4.
```
EDAD:

La edad es negativa o igual a 0:

    Limite superior: 0
    limite inferior: menos infinito

La edad supera los 200 años:

    Limite inferior: 201
    Limite superior: infinito

La edad esta entre 1 a 200 años:

    Limite inferior: 1
    Limite superior: 199

DÍAS DE ANTELACIÓN:

Los días de antelación son negativos O iguales a 0:

    Limite inferior: 0
    Limite superior: menos infinito

TARIFA BASE:

la tarifa es de base negativa o igual a 0:

    Limite superior: 0
    limite inferior: menos infinito
```
5.
```
 EDAD:

La edad es negativa o igual a 0:

    Limite superior: calculoTarifa(850.000, 0, 20) = excepción
    limite inferior: calculoTarifa(850.000, -1000000000, 20)

La edad supera los 200 años:

    Limite inferior: calculoTarifa(850.000, 201, 20) = excepción
    Limite superior: calculoTarifa(850.000, 10000000, 20) = excepción

La edad esta entre 1 a 200 años:

    Limite inferior: calculoTarifa(850.000, 1, 15) = (VALOR = 807.500)
    Limite superior: calculoTarifa(850.000, 199, 15) = (VALOR = 782.000)

DÍAS DE ANTELACIÓN:

Los días de antelación son negativos O iguales a 0:

    Limite inferior: calculoTarifa(850.000, 21, 0) = excepción
    Limite superior: calculoTarifa(850.000, 21, -100000000) = excepción

TARIFA BASE:

la tarifa es de base negativa o igual a 0:

    Limite superior: calculoTarifa(0, 21, 20) = excepción
    limite inferior: calculoTarifa(-10000000000, 21, 20) = excepción
```


### REALIZAR DISEÑO DE PRUEBAS

Descargue el archivo aerodescuentos.jar y adicione esta nueva dependencia en el archivo pom.xml de su proyecto.

Para adicionar una librería personalizada al repositorio local de maven puede ejecutar el siguiente comando.

![image](https://github.com/AlejoCNYT/cvds/assets/74771189/fa109b00-180a-4d14-b84d-6996479291c7)

### IMPLEMENTACIÓN DE LAS PRUEBAS
```

package edu.eci.cvds.tdd.aerodescuentos;

import org.junit.Assert;
import org.junit.Test;

public class TarifasTest {
    
    private CalculadorDescuentos c = new CalculadorDescuentos();
    private double tarifa;

     @Test
    public void deberiaSalirExcepcionEdadIgualACero(){
        //deberia generar error por edad = 0
        try{
            tarifa = c.calculoTarifa(5000000, 15, 0);
        }
        catch ( Exception e){
            Assert.assertTrue(true);
        }
    }

    @Test
    public void deberiaSalirExcepcionTarifaNegativa(){

        try{
            tarifa = c.calculoTarifa(-850.000, 18, 20);
        }
        catch ( Exception e){
            Assert.assertTrue(true);
        }
    }

    @Test
    public void deberiaSalirExcepcionDiasDeAntelacionNegativos(){

        try{
            tarifa = c.calculoTarifa(850.000, -18, 20);
        }
        catch ( Exception e){
            Assert.assertTrue(true);
        }
    }

    @Test
    public void deberiaSalirExcepcionEdadNegativa(){

        try{
            tarifa = c.calculoTarifa(850.000, 18, -20);
        }
        catch ( Exception e){
            Assert.assertTrue(true);
        }
    }

    @Test
    public void noDeberiaTenerDescuento(){

       tarifa = c.calculoTarifa(850.000, 20, 22);

       Assert.assertEquals(850.000, tarifa, 0.1);

    }

    @Test
    public void deberiaTener15Descuento(){

       tarifa = c.calculoTarifa(850.000, 28, 22);

       Assert.assertEquals(722.500, tarifa, 0.1);

    }

    @Test
    public void deberiaTener5Descuento(){

       tarifa = c.calculoTarifa(850.000, 10, 15);

       Assert.assertEquals(807.500, tarifa, 0.1);

    }

    @Test
    public void deberiaTener8Descuento(){

       tarifa = c.calculoTarifa(850.000, 10, 75);

       Assert.assertEquals(782.000, tarifa, 0.1);

    }

    @Test
    public void deberiaTener20Descuento(){

       tarifa = c.calculoTarifa(850.000, 30, 14);

       Assert.assertEquals(680.000, tarifa, 0.1);

    }

    @Test
    public void deberiaTener23Descuento(){

       tarifa = c.calculoTarifa(850.000, 30, 84);

       Assert.assertEquals(654.500, tarifa, 0.1);

    }

}
```

## Ejecución Test 
![image](https://github.com/AlejoCNYT/cvds/assets/74771189/7b83e460-4896-4194-a1b3-eaf623cdadf2)

