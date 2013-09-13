package cn.fm.web.action.ireport;

import java.sql.Connection;
import java.util.Collection;
import java.util.Map;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
//import ar.clarin.fwjava.componentes.CLJOptionPane;

public class JasperHelper {

        public static JasperReport loadReporte(String jasperPath) {
                try {
                        return (JasperReport) JRLoader.loadObject(new JasperHelper().getClass().getResourceAsStream(jasperPath));
                } catch (JRException e) {
                        e.printStackTrace();
                        return null;
                }
        }

        public static <T extends Map<String,Object>> JasperPrint fillReport(JasperReport report, T parameters) {
                try {
                        return JasperFillManager.fillReport(report, parameters);
                } catch (JRException e) {
                        e.printStackTrace();
                        return null;
                }
        }

        public static <T extends Map<String,Object>> JasperPrint fillReport(JasperReport report, T parameters, Collection<?> coleccion) {
                try {
                        return JasperFillManager.fillReport(report, parameters, new JRBeanCollectionDataSource(coleccion));
                } catch (JRException e) {
                        e.printStackTrace();
                        return null;
                }
        }

        public static<T extends Map<String,Object>> JasperPrint fillReport(JasperReport report, T parameters, Connection jdbcConnection) {
                try {
                        return JasperFillManager.fillReport(report, parameters, jdbcConnection);
                } catch (JRException e) {
                        e.printStackTrace();
                        return null;
                }
        }

        public static void exportarAPDF(JasperPrint reporte, String pdfPath) throws JRException {
                JasperExportManager.exportReportToPdfFile(reporte, pdfPath);
        }

        public static void visualizarReporte(JasperPrint reporte) {
                JasperViewer.viewReport(reporte, false);
        }

        public static Integer imprimirReporte(JasperPrint reporte, boolean conDialogoImpresion, boolean esperarPorCopia, int copias) throws JRException {
                Integer ret = 0;
                for (int i = 0; i < copias; i++) {
                        JasperPrintManager.printReport(reporte, conDialogoImpresion && i==0);
                        ret++;
                        if (esperarPorCopia && (i+1)<copias) {
//                                if (CLJOptionPane.showQuestionMessage(null, "Continuar?", "Impresión") == CLJOptionPane.NO_OPTION) {
//                                        break;
//                                }
                        }
                }
                return ret;
        }
}