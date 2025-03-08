/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

/**
 *
 * @author cristiandresgp
 */
public class FuncionesInterfaz {
    private static final Bienvenida bienvenida= new Bienvenida(); 
    private static Simulacion simulacion = new Simulacion();

    public static Bienvenida getBienvenida() {
        return bienvenida;
    }
    
    public static Simulacion getSimulacion() {
        return simulacion;
    }
    
    public static void openSimulacion() {
        getSimulacion().setVisible(true);
        getSimulacion().setSize(750,500);
        getSimulacion().setLocationRelativeTo(null);
        getSimulacion().setResizable(false);
    }
    
}
