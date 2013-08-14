package cn.fm.web.action.employee;
import java.io.ByteArrayOutputStream;
import java.util.Map;
import java.io.UnsupportedEncodingException;

import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import java.io.IOException;
import java.sql.Connection;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;





import cn.fm.utils.ConnUtil;
import cn.fm.utils.Constant;
import cn.fm.utils.StringUtil;
import cn.fm.utils.WMSResource;
import cn.fm.web.action.BaseAction;

public class ReportAction  extends BaseAction implements ServletRequestAware, ServletResponseAware{

	private HttpServletRequest request;
    private HttpServletResponse response;
    private Connection conn;
    public void setServletRequest(HttpServletRequest request) {
        this.request = request;
    }

    public void setServletResponse(HttpServletResponse response) {
        this.response = response;
    }

    public void write(byte[] bytes, String contentType) throws Exception {
        ServletOutputStream ouputStream = null;

        try {
            if (response != null) {
                ouputStream = response.getOutputStream();
                response.setContentType(contentType);
                response.setContentLength(bytes.length);
            }

            if (ouputStream != null) {
                ouputStream.write(bytes, 0, bytes.length);
                ouputStream.flush();
            }
        } catch (Exception e) {
            //ignore
        } finally {
            if (ouputStream != null)
                try {
                    ouputStream.close();
                } catch (IOException e) {
                }
        }
    }
	
	 @SuppressWarnings("unchecked")
	public void readToExcel(String jrxmlPath, String jasperPath, Map parameters) throws Exception {
			        JasperCompileManager.compileReportToFile(jrxmlPath, jasperPath);
			        readToExcel(jasperPath,parameters);
			    }


		@SuppressWarnings("unchecked")
	public void readToExcel(String jasperPath, Map parameters) throws Exception 
	{
		        JRXlsExporter exporter = buildXlsExporter(jasperPath,parameters); // Excel
		        ByteArrayOutputStream oStream = new ByteArrayOutputStream();
		        exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, oStream);
		        exporter.exportReport();
		        if (parameters.get("Title") != null)
		            setExcelFileName(parameters.get("Title").toString());
		        byte[] bytes = oStream.toByteArray();
		        write(bytes, "application/vnd.ms-excel");
		        ConnUtil.closeConn(conn);
	}
    public void setExcelFileName(String filename) throws UnsupportedEncodingException
		 {
		        String agent = request.getHeader("USER-AGENT");
		        if (null != agent && -1 != agent.indexOf("MSIE")) {
		            filename = java.net.URLEncoder.encode(filename, "UTF-8");
		        } else if (null != agent && -1 != agent.indexOf("Mozilla")) {
		            filename = "=?UTF-8?B?" + (new String(org.apache.commons.codec.binary.Base64.encodeBase64(filename.getBytes("UTF-8")))) + "?=";
		        }
		        if (!StringUtil.isEmpty(filename))
		            response.setHeader("Content-Disposition", "attachment;filename=\"" + filename + ".xls\";");//打开,保存

		   }
		  @SuppressWarnings("unchecked")
		public JRXlsExporter buildXlsExporter(String jasperPath, Map parameters) throws Exception {
		        String currentPath =WMSResource.getResourcesRootPath()+ "/report/";
		        parameters.put("jasperRootDir", currentPath);
		        parameters.put("exportType", Constant.WMS_REPORT_EXCEL_EXPORT_TYPE);
		       // parameters.put("Constant.EGIS_LOGIN_USER_DIVISION", "");
		        conn = ConnUtil.getConn();
		        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperPath, parameters, conn);
		        JRXlsExporter exporter = new JRXlsExporter();
		        exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
		        exporter.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, false);
		        exporter.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND, false);
		        exporter.setParameter(JRXlsExporterParameter.IS_FONT_SIZE_FIX_ENABLED, true);
		        exporter.setParameter(JRXlsExporterParameter.IS_IMAGE_BORDER_FIX_ENABLED, true);
		        exporter.setParameter(JRXlsExporterParameter.IS_COLLAPSE_ROW_SPAN, true);
		        exporter.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_COLUMNS, true);
		        return exporter;
		    }

	
	
}
