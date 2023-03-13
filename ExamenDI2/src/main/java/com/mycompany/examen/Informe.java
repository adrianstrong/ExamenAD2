/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.examen;

import java.io.InputStream;
import java.sql.SQLException;
import java.util.HashMap;

import models.Alumno;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfExporterConfiguration;

public class Informe {

    public static void pdfReport() throws JRException, ClassNotFoundException, SQLException {

        HashMap hm = new HashMap();


        JasperReport report = JasperCompileManager.compileReport("C:\\Users\\adria\\Desktop\\ExamenAD2-main\\ExamenDI2\\src\\main\\resources\\reportnotas.jrxml");

            JasperPrint jasperPrint = JasperFillManager.fillReport(
                    report,
                    hm,
                    JdbcUtil.getConnection()
            );

            JRPdfExporter exp = new JRPdfExporter();
            exp.setExporterInput(new SimpleExporterInput(jasperPrint));
            exp.setExporterOutput(new SimpleOutputStreamExporterOutput("boletines.pdf"));
            SimplePdfExporterConfiguration conf = new SimplePdfExporterConfiguration();
            exp.setConfiguration(conf);
            exp.exportReport();
    }

}
