/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Negocio;

import DAO.Conexion;
import DAO.PlanMovilJpaController;
import DTO.PlanMovil;
import java.util.List;

/**
 * Responde a una FACADE
 *
 * @author jesus
 */
public class Telefonia_negocio {

    private Conexion c = new Conexion();

    public Telefonia_negocio() {

    }

    public String getListadoPlanes() {

        String tabla = "\n<table border='1'>";
        tabla += "\n<tr>"
                + "\n<td> Id Plan</td>"
                + "\n<td> Nombre Plan</td>"
                + "\n<td> Precio Plan</td>"
                + "\n</tr>";

        PlanMovilJpaController plan = new PlanMovilJpaController(c.getBd());
        List<PlanMovil> planes = plan.findPlanMovilEntities();
        for (PlanMovil p : planes) {
            tabla += "\n<tr>"
                    + "\n<td>" + p.getCodigoPlan() + "</td>"
                    + "\n<td>" + p.getNombrePlan() + "</td>"
                    + "\n<td>" + p.getPrecio() + "</td>"
                    + "\n</tr>";
        }

        return tabla + "</table>";
    }

}
