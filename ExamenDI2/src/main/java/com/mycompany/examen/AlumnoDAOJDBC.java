package com.mycompany.examen;

import models.Alumno;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class AlumnoDAOJDBC {

    public ArrayList<Alumno> getAlumnos() {
        String query = "SELECT * FROM alumno";
        ArrayList<Alumno> alumnos = new ArrayList();

        try (Statement st = JdbcUtil.getConnection().createStatement()) {
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                Alumno alumno = new Alumno();

                alumno.setID(rs.getLong("id"));
                alumno.setNombre(rs.getString("nombre"));
                alumno.setApellidos(rs.getString("apellidos"));
                alumno.setAD(rs.getInt("AD"));
                alumno.setSGE(rs.getInt("SGE"));
                alumno.setDI(rs.getInt("DI"));
                alumno.setPMDM(rs.getInt("PMDM"));
                alumno.setPSP(rs.getInt("PSP"));
                alumno.setEIE(rs.getInt("EIE"));
                alumno.setHLC(rs.getInt("HLC"));

                alumnos.add(alumno);

            }

        } catch (SQLException ex) {
            System.out.println(ex);
        }

        return alumnos;
    }


}
