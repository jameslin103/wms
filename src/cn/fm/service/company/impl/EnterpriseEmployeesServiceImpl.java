package cn.fm.service.company.impl;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.fm.bean.company.Enterprise;
import cn.fm.bean.company.EnterpriseEmployees;
import cn.fm.service.base.DaoSupport;
import cn.fm.service.company.EnterpriseEmployeesService;
import cn.fm.utils.Constant;
import cn.fm.utils.DateUtil;
import cn.fm.utils.GenerateSqlFromExcel;
import cn.fm.utils.StringUtil;

@Service @Transactional
public class EnterpriseEmployeesServiceImpl extends	DaoSupport<EnterpriseEmployees> implements EnterpriseEmployeesService {
	
	public void save(EnterpriseEmployees entity)
	{
		super.save(entity);	
	}


	
	@SuppressWarnings("unchecked")
	public List<EnterpriseEmployees> getAllEnterpriseEmployees(Integer enterpriseId)
	{
		Query query = em.createQuery("select e from EnterpriseEmployees e where e.enterprise.id=?1 ");
		return query.setParameter(1, enterpriseId).getResultList();
	}
	
	/**
	 * 根据姓名查询企业员工或者全站的员工在某个企业
	 *   select * from buyer AS o where o.regTime >='2013/03/03' and o.regTime<='2013/08/03';
	 *   select * from enterpriseemployees as e where e.employeesName like '%刘%' and e.employeesId;
	 */
	@SuppressWarnings("unchecked")
	public List<EnterpriseEmployees>  findAllEnterpriseEmployees(String employeesName,Integer all,Integer enterpriseId)
	{
		Query query = null;
		if(!StringUtil.isEmpty(employeesName)  && all!=null)
		{
			query = em.createQuery("select e from EnterpriseEmployees e where e.employeesName like ?1")
			.setParameter(1, "%"+employeesName+"%");
		}
		if(StringUtil.isEmpty(employeesName) && all!=null){
			query = em.createQuery("select e from EnterpriseEmployees e");
		}
		if(all==null && !StringUtil.isEmpty(employeesName))
		{
			query = em.createQuery("select e from EnterpriseEmployees e where e.employeesName like ?1 and e.enterprise.id=?2 ")
			.setParameter(1, "%"+employeesName+"%").setParameter(2, enterpriseId);
			
		}
		
		 return query.getResultList();
	}
	
	
	@SuppressWarnings("unchecked")
	public List<EnterpriseEmployees> findInsuranceEnterpriseEmployees(Integer  insurance)
	{
		Query query = em.createQuery("select e from EnterpriseEmployees e where e.whetherGinseng=?1");
		
		List<EnterpriseEmployees>  list=query.setParameter(1, insurance).getResultList();
		return list;
	}
	
	/**
	 * 批量导入企业员工
	 * 
	 */
	@SuppressWarnings({ "unchecked", "static-access" })
	public void saveImportExcelEmployees(File file , String fiName,int number, Enterprise enterprise) {
		GenerateSqlFromExcel excel =new GenerateSqlFromExcel();
		try {
			List<String[]> arrayList=excel.generateStationBugSql(file,fiName,number);
			if(arrayList==null)return;
			for (int i = 0; i < arrayList.size(); i++) {
				String[] data = arrayList.get(i);
				EnterpriseEmployees employees=new EnterpriseEmployees();
				employees.setContractNo(data[1].toString());
				employees.setEmployeesName(data[2].toString());
				employees.setEmployeesSex(data[3].toString());
				employees.setNativePlace(data[4].toString());
				employees.setHouseholdRegister(data[5].toString()==null?null:data[5].equals(Constant.WMS_YES)?1:0);
				employees.setHomeAddress(data[6].toString());
				employees.setMaritalStatus(data[7].toString()==null?null:data[7].equals(Constant.WMS_YES)?1:0);
				employees.setLevelEducation(data[8].toString());
				employees.setPhoto(data[9].toString()==null?null:data[9].equals(Constant.WMS_PHOTO_YES)?1:0);
				employees.setCardNumber(data[10].toString());
				employees.setPhone(data[11].toString());
				employees.setIndustry(data[12].toString());
				employees.setJobs(data[13].toString());
				employees.setBank(data[14].toString());
				employees.setBankCardNumber(data[15].toString());
				employees.setStartContractDeadline(data[16].toString()==null?null:DateUtil.StringToDate(data[16], DateUtil.FORMAT_DATE));
				employees.setEndContractDeadline(data[17].toString()==null?null:DateUtil.StringToDate(data[17], DateUtil.FORMAT_DATE));
				employees.setServiceCost(data[18].toString().equals("")?null:Double.valueOf(data[18]));
				employees.setGinsengProtectNature(data[19].toString());
				employees.setWhetherGinseng(data[20].toString()==null?null:data[20].equals(Constant.WMS_YES)?1:0);
				employees.setCinsengDate(data[21].toString()==null?null:DateUtil.StringToDate(data[21], DateUtil.FORMAT_DATE));
				employees.setHealthCare(data[22].toString()==null?null:data[22].equals(Constant.WMS_YES)?1:0);
				employees.setSociaSecurity(data[23].toString()==null?null:data[23].equals(Constant.WMS_YES)?1:0);
				employees.setAccumulationFund(data[24].toString()==null?null:data[24].equals(Constant.WMS_YES)?1:0);
				employees.setSeriousDisease(data[25].toString()==null?null:data[25].equals(Constant.WMS_YES)?1:0);
				employees.setBase(data[26].toString()==null?null:data[26].equals(Constant.WMS_YES)?1:0);
				employees.setSocialInsurance(data[27].toString().equals("")?null:Double.valueOf(data[27]));
				employees.setFertilityInsurance(data[28].toString().equals("")?null:Double.valueOf(data[28]));
				employees.setInductrialBase(data[29].toString().equals("")?null:Double.valueOf(data[29]));
				employees.setBasicMedical(data[30].toString().equals("")?null:Double.valueOf(data[30]));
				employees.setHousingFund(data[31].toString().equals("")?null:Double.valueOf(data[31]));
				employees.setSeriousDiseaseBase(data[32].toString().equals("")?null:Double.valueOf(data[32]));
				employees.setPaymentWay(data[33].toString());
				employees.setEnterprise(enterprise);
				
				super.save(employees);	
			}
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
	}

	public List<EnterpriseEmployees> getEnterpriseEmployeesSalaryDetail(
			Serializable entityId) {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * 根据id查找一个实体
	 * 
	 */

	public EnterpriseEmployees findEnterpriseEmployees(Integer employeesId) {
		return super.find(employeesId);
	}
	
	/**  
	* @Name: getExcelFiledNameList
	* @Description: 获取excel的标题数据
	*               放到ArrayList集合中
	* @Author: 刘洋（作者）
	* @Version: wms1.00 （版本号）
	* @Create Date: 2013-08-25（创建日期）
	* @Parameters: 无
	* @Return: ArrayList(Excel标题集数据)
	*/
	public List<String> getExcelFiledNameList() {
		String [] titles = {"序","合同编号","姓名",	"性别",	"籍贯",	"户口性质",	"家庭住址",	"婚姻",	"文化程度","照片",	"身份证"	,"电话",	"行业",	"岗位",	
				"开户银行",	"卡号","合同期限(起)","合同期限(止)","服务费（元）","参保性质","是否参保","哪月起参保？","社保","医保","公积金","大病统筹",
				"参考默认","社会保险","生育保险","工伤","基本医疗保险","住房公积金","大病统筹","缴纳方式"};
	
		
		List<String> filedName = new ArrayList<String>();
		for(int i=0;i<titles.length;i++){
			String title = titles[i];
			filedName.add(title);
		}
		return filedName;
	}
	/**  
	* @Name: getExcelFiledDataList
	* @Description: 获取excel的数据内容
	* 	   获取数据,
	* @Author: jameslin（作者）
	* @Version: wms1.00 （版本号）
	* @Create Date: 2013-08-25 （创建日期）
	* @Parameters: 无
	* @Return: ArrayList(Excel标题集数据)
	*/
	@SuppressWarnings("unchecked")
	public List<EnterpriseEmployees> getExcelFiledDataList(EnterpriseEmployees enterpriseEmployees,int enterpriseId)
	{

		List<EnterpriseEmployees> enterpriseEmployeesListDatePO=null;
		Query query=em.createQuery("select e from EnterpriseEmployees e where e.enterprise.id=?1");
		query.setParameter(1, enterpriseId);
		enterpriseEmployeesListDatePO=query.getResultList();
		List<EnterpriseEmployees> employeesListVO = this.enterpriseEmployeesPOListToVOList(enterpriseEmployeesListDatePO);

		return  employeesListVO;
	}
	/**  
	* @Name: elecUserPOListToVOList
	* @Description: 获取的用户列表中的值从PO对象转换成VO对象
	* @Author: jameslin（作者）
	* @Version: wmsV1.00 （版本号）
	* @Create Date: 2013-08-25 （创建日期）
	* @Parameters: List<ElecUser> list 存放PO对象
	* @Return: List<ElecUserForm> 存放VO对象
	*/
	private List<EnterpriseEmployees> enterpriseEmployeesPOListToVOList(List<EnterpriseEmployees> list) {
		//构造报表导出数据
		List<EnterpriseEmployees> employeesList = new ArrayList<EnterpriseEmployees>();
		EnterpriseEmployees enterpriseEmployeesVO = null;
		for(int i=0;list!=null && i<list.size();i++){
			EnterpriseEmployees employeesListVO = list.get(i);
			enterpriseEmployeesVO = new EnterpriseEmployees();
			enterpriseEmployeesVO.setEmployeesId(employeesListVO.getEmployeesId());
			enterpriseEmployeesVO.setContractNo(employeesListVO.getContractNo());
			enterpriseEmployeesVO.setEmployeesName(employeesListVO.getEmployeesName());
			enterpriseEmployeesVO.setEmployeesSex(employeesListVO.getEmployeesSex());
			enterpriseEmployeesVO.setNativePlace(employeesListVO.getNativePlace());
			enterpriseEmployeesVO.setHouseholdRegister(employeesListVO.getHouseholdRegister());
			enterpriseEmployeesVO.setHomeAddress(employeesListVO.getHomeAddress());
			enterpriseEmployeesVO.setMaritalStatus(employeesListVO.getMaritalStatus());
			enterpriseEmployeesVO.setLevelEducation(employeesListVO.getLevelEducation());
			enterpriseEmployeesVO.setPhoto(employeesListVO.getPhoto());
			enterpriseEmployeesVO.setCardNumber(employeesListVO.getCardNumber());
			enterpriseEmployeesVO.setPhone(employeesListVO.getPhone());
			enterpriseEmployeesVO.setIndustry(employeesListVO.getIndustry());
			enterpriseEmployeesVO.setJobs(employeesListVO.getJobs());
			enterpriseEmployeesVO.setBank(employeesListVO.getBank());
			enterpriseEmployeesVO.setBankCardNumber(employeesListVO.getBankCardNumber());
			enterpriseEmployeesVO.setStartContractDeadline(employeesListVO.getStartContractDeadline());
			enterpriseEmployeesVO.setEndContractDeadline(employeesListVO.getEndContractDeadline());
			enterpriseEmployeesVO.setServiceCost(employeesListVO.getServiceCost());
			enterpriseEmployeesVO.setGinsengProtectNature(employeesListVO.getGinsengProtectNature());
			enterpriseEmployeesVO.setWhetherGinseng(employeesListVO.getWhetherGinseng());
			enterpriseEmployeesVO.setCinsengDate(employeesListVO.getCinsengDate());
			enterpriseEmployeesVO.setHealthCare(employeesListVO.getHealthCare());
			enterpriseEmployeesVO.setSociaSecurity(employeesListVO.getSociaSecurity());
			enterpriseEmployeesVO.setAccumulationFund(employeesListVO.getAccumulationFund());
			enterpriseEmployeesVO.setSeriousDisease(employeesListVO.getSeriousDisease());
			enterpriseEmployeesVO.setBase(employeesListVO.getBase());
			enterpriseEmployeesVO.setSocialInsurance(employeesListVO.getSocialInsurance());
			enterpriseEmployeesVO.setFertilityInsurance(employeesListVO.getFertilityInsurance());
			enterpriseEmployeesVO.setInductrialBase(employeesListVO.getInductrialBase());
			enterpriseEmployeesVO.setBasicMedical(employeesListVO.getBasicMedical());
			enterpriseEmployeesVO.setHousingFund(employeesListVO.getHousingFund());
			enterpriseEmployeesVO.setSeriousDiseaseBase(employeesListVO.getSeriousDiseaseBase());
			enterpriseEmployeesVO.setPaymentWay(employeesListVO.getPaymentWay());
			//enterpriseEmployeesVO.setEnterpriseId(employeesListVO.getEnterpriseId());
			employeesList.add(enterpriseEmployeesVO);
		}
		return employeesList;
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
