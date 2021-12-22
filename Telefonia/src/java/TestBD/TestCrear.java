/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TestBD;

import DAO.Conexion;
import DAO.PlanMovilJpaController;
import DTO.PlanMovil;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jesus
 */
public class TestCrear {
    
    public static void main(String[] args) {
        Conexion c = new Conexion();
        PlanMovilJpaController plan =new PlanMovilJpaController(c.getBd());
        PlanMovil nuevoPlan= new PlanMovil();
        
        nuevoPlan.setCodigoPlan(plan.getPlanMovilCount()+1);
        nuevoPlan.setNombrePlan("Diamante");
        nuevoPlan.setPrecio(25000);
        
        try {
            plan.create(nuevoPlan);
            System.out.println("registro creado con éxito");
        } catch (Exception ex) {
            System.out.println("No se puede hacer registro: "+ ex.getMessage());
        }
        
    }
}
