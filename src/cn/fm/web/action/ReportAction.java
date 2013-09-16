package cn.fm.web.action;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.sql.Connection;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.sql.DataSource;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;

import net.sf.jasperreports.engine.JRBoxContainer;
import net.sf.jasperreports.engine.base.JRBaseParagraph;
import net.sf.jasperreports.engine.JRParagraphContainer;
import net.sf.jasperreports.engine.base.JRBaseStaticText;
//import net.sf.jasperreports.engine.base.JRBaseParagraph.paragraphContainer;


import org.apache.commons.codec.binary.Base64;
import org.apache.struts2.util.ServletContextAware;




@SuppressWarnings({ "serial", "unused" })
public abstract class ReportAction extends BaseAction implements ServletContextAware {
	private static final String JASPER_FILS_PATH = "/report";
	@Resource
	private DataSource dataSource;
	
	private ServletContext context;
	
	public void setServletContext(ServletContext context) {
		this.context=context;
	}
	
	/**
	 * 根据内置sql导出excel
	 * @param jasperFileName .jasper文件名
	 * @param downLoadFileName 下载文件名
	 * @param parameters 传入参数
	 * @throws Exception
	 */
    public void downloadExcel(String jasperFileName,String downLoadFileName,Map<String,Object> parameters) throws Exception{
        Connection conn = dataSource.getConnection();
        ByteArrayOutputStream oStream = new ByteArrayOutputStream();
        String currentPath=context.getRealPath(JASPER_FILS_PATH+"/"+jasperFileName);
        JasperPrint jasperPrint = JasperFillManager.fillReport(currentPath, parameters, conn);
        JRXlsExporter exporter = new JRXlsExporter();
        setExporterDefault(exporter,jasperPrint,oStream);
        exporter.exportReport();
        byte[] bytes = oStream.toByteArray();
        response.setHeader("Content-Disposition", "attachment;filename=\"" + generateDownloadFileName(downLoadFileName) + ".xls\";");
        write(bytes,"application/vnd.ms-excel");
        conn.close();
    }
    
    /**
     * 写入输出流
     */
    private void write(byte[] bytes, String contentType) throws Exception {
    	ServletOutputStream ouputStream ;
        if (response != null&&response.getOutputStream()!=null) {
            response.setContentType(contentType);
            response.setContentLength(bytes.length);
            ouputStream = response.getOutputStream();
            ouputStream.write(bytes, 0, bytes.length);
            ouputStream.flush();
            ouputStream.close();
        }
    }
    
    /**
     * 根据浏览器生成下载文件的文件名
     */
    private String generateDownloadFileName(String fileName) throws UnsupportedEncodingException{
    	String agent = request.getHeader("USER-AGENT");
    	if ((null != agent && (-1 != agent.indexOf("MSIE")||-1 != agent.indexOf("Chrome")))) {
    		fileName = URLEncoder.encode(fileName, "UTF-8");
        } else if (null != agent && -1 != agent.indexOf("Firefox")) {
        	fileName = "=?UTF-8?B?" + (new String(Base64.encodeBase64(fileName.getBytes("UTF-8")))) + "?=";
        } 
		return fileName;
    }
    
    /**
     * 根据javabean导出excel
     * @param jasperFileName .jasper文件名
     * @param downLoadFileName 下载文件名
     * @param parameters 传入参数
     * @param reportBeanList javabean List
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
	public void downloadExcel(String jasperFileName,String downLoadFileName,Map<String,Object> parameters,List reportBeanList) throws Exception{
    	ByteArrayOutputStream oStream = new ByteArrayOutputStream();
    	String currentPath=context.getRealPath(JASPER_FILS_PATH+"/"+jasperFileName);
    	JRDataSource dataSource = new JRBeanCollectionDataSource(reportBeanList);
    	JasperPrint jasperPrint = JasperFillManager.fillReport(currentPath, parameters,dataSource);
    	JRXlsExporter exporter = new JRXlsExporter();
    	setExporterDefault(exporter,jasperPrint,oStream);
        exporter.exportReport();
        byte[] bytes = oStream.toByteArray();
        response.setHeader("Content-Disposition", "attachment;filename=\"" + generateDownloadFileName(downLoadFileName) + ".xls\";");
        write(bytes,"application/vnd.ms-excel");
    }
    
    private void setExporterDefault(JRXlsExporter exporter,JasperPrint jasperPrint,ByteArrayOutputStream oStream){
    	exporter.setParameter(JRXlsExporterParameter.JASPER_PRINT, jasperPrint);
        exporter.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND, true);
        exporter.setParameter(JRXlsExporterParameter.IS_FONT_SIZE_FIX_ENABLED, true);
        exporter.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, false);
        exporter.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_COLUMNS, false);
        exporter.setParameter(JRXlsExporterParameter.IS_DETECT_CELL_TYPE, true);
        exporter.setParameter(JRXlsExporterParameter.OUTPUT_STREAM, oStream);
    }

}
