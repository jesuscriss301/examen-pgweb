/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TestBD;

import DAO.Conexion;
import DAO.PlanMovilJpaController;
import DTO.PlanMovil;
import java.util.List;

/**
 *
 * @author jesus
 */
public class TestBD1 {
    public static void main(String[] args) {
        Conexion c = new Conexion();
        PlanMovilJpaController plan =new PlanMovilJpaController(c.getBd());
        List<PlanMovil> planes = plan.findPlanMovilEntities();
        
        for (PlanMovil p : planes) {
            System.out.println(p.getCodigoPlan()+", "+ p.getNombrePlan()+ " , "+ p.getPrecio());
        }
    }
}
