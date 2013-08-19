package cn.fm.service.company.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import cn.fm.bean.company.EnterpriseEmployees;
import cn.fm.service.base.DaoSupport;
import cn.fm.service.company.EnterpriseEmployeesService;
import cn.fm.utils.Constant;
import cn.fm.utils.DateUtil;
import cn.fm.utils.GenerateSqlFromExcel;

@Service @Transactional
public class EnterpriseEmployeesServiceImpl extends	DaoSupport<EnterpriseEmployees> implements EnterpriseEmployeesService {
	
	public void save(EnterpriseEmployees entity)
	{
		super.save(entity);	
	}

	@SuppressWarnings("unchecked")
	public List<EnterpriseEmployees> getAllEnterpriseEmployees()
	{
		List<EnterpriseEmployees> listEnterpriseEmployees=new ArrayList<EnterpriseEmployees>();
		Query query = em.createQuery("select e from EnterpriseEmployees e ");
		listEnterpriseEmployees=query.getResultList();
		return listEnterpriseEmployees;
	}
	
	
	
	
	
	
	/**
	 * 批量导入企业员工
	 */
	@SuppressWarnings({ "unchecked", "static-access" })
	public void saveImportExcelEmployees(File file , String fiName) {
		GenerateSqlFromExcel excel =new GenerateSqlFromExcel();
		try {
			List<String[]> arrayList=excel.generateStationBugSql(file,fiName);
			if(arrayList==null)return;
			for (int i = 0; i < arrayList.size(); i++) {
				String[] data = arrayList.get(i);
				EnterpriseEmployees employees=new EnterpriseEmployees();
				employees.setContractNo(data[2].toString());
				employees.setEmployeesName(data[3].toString());
				employees.setEmployeesSex(data[4].toString());
				employees.setNativePlace(data[5].toString());
				employees.setHomeAddress(data[6].toString());
				employees.setMaritalStatus(data[7].toString()==null?null:data[7].equals(Constant.WMS_YES)?0:1);
				employees.setLevelEducation(data[8].toString());
				employees.setPhoto(data[9].toString()==null?null:data[9].equals(Constant.WMS_PHOTO_YES)?1:0);
				employees.setCardNumber(data[10].toString());
				employees.setPhone(data[11].toString());
				employees.setBank(data[12].toString());
				employees.setBankCardNumber(data[13].toString());
				employees.setStartContractDeadline(data[14].toString()==null?null:DateUtil.StringToDate(data[14], DateUtil.FORMAT_DATE));
				employees.setEndContractDeadline(data[15].toString()==null?null:DateUtil.StringToDate(data[15], DateUtil.FORMAT_DATE));
				employees.setServiceCost(data[16].toString().equals("")?null:Double.valueOf(data[16]));
				employees.setGinsengProtectNature(data[17].toString()==null?null:data[17].equals(Constant.WMS_XIN_ZENG)?1:0);
				employees.setCinsengDate(data[18].toString()==null?null:DateUtil.StringToDate(data[18], DateUtil.FORMAT_DATE));
				employees.setSociaSecurity(data[19].toString()==null?null:data[19].equals(Constant.WMS_YES)?1:0);
				employees.setHealthCare(data[20].toString()==null?null:data[20].equals(Constant.WMS_YES)?1:0);
				employees.setAccumulationFund(data[21].toString()==null?null:data[21].equals(Constant.WMS_YES)?1:0);
				employees.setSeriousDisease(data[22].toString()==null?null:data[22].equals(Constant.WMS_YES)?1:0);
				employees.setBank(data[23].toString());
				employees.setSocialInsurance(data[24].toString().equals("")?null:Double.valueOf(data[24]));
				employees.setFertilityInsurance(data[25].toString().equals("")?null:Double.valueOf(data[25]));
				employees.setInductrialBase(data[26].toString().equals("")?null:Double.valueOf(data[26]));
				employees.setBasicMedical(data[27].toString().equals("")?null:Double.valueOf(data[27]));
				employees.setHousingFund(data[28].toString().equals("")?null:Double.valueOf(data[28]));
				employees.setSeriousDiseaseBase(data[29].toString().equals("")?null:Double.valueOf(data[29]));
				employees.setPaymentWay(data[30].toString());
				
				super.save(employees);	
			}
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
	}

}
