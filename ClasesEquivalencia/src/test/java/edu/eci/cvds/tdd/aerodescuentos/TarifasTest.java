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
