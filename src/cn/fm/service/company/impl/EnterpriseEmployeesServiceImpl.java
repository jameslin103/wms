package cn.fm.service.company.impl;

import java.io.File;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
		try {
			Query query = em.createQuery("select e from EnterpriseEmployees e where e.enterprise.enterpriseId=?1  and e.departure=0 and e.reduction=0");
			return query.setParameter(1, enterpriseId).getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
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
			query = em.createQuery("select e from EnterpriseEmployees e where e.employeesName like ?1  and e.departure=0 ")
			.setParameter(1, "%"+employeesName+"%");
		}
		if(StringUtil.isEmpty(employeesName) && all!=null){
			query = em.createQuery("select e from EnterpriseEmployees e  where e.departure=0 ");
		}
		if(all==null && !StringUtil.isEmpty(employeesName))
		{
			query = em.createQuery("select e from EnterpriseEmployees e where e.employeesName like ?1 and e.enterprise.id=?2  and e.departure=0 ")
			.setParameter(1, "%"+employeesName+"%").setParameter(2, enterpriseId);
			
		}
		
		 return query.getResultList();
	}
	
	/**
	 * 查看参保人员与未参保人员
	 * @param insurance
	 * @param enterpriseId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<EnterpriseEmployees> findInsuranceEnterpriseEmployees(Integer  insurance,Integer enterpriseId)
	{
		Query query = em.createQuery("select e from EnterpriseEmployees e where e.whetherGinseng=?1 and e.enterprise.enterpriseId=?2  and e.departure=0 " );
		
		return query.setParameter(1, insurance).setParameter(2, enterpriseId).getResultList();
		 
	}
	
	/**
	 * 批量导入企业员工
	 * 
	 */
	@SuppressWarnings({ "unchecked", "static-access" })
	public void saveImportExcelEmployees(File file , String fiName,int number,int readRow, Enterprise enterprise) {
		
		GenerateSqlFromExcel excel =new GenerateSqlFromExcel();
		try {
			List<String[]> arrayList=excel.generateStationBugSql(file,fiName,number,readRow);
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
				employees.setGinsengProtectNature(data[19].toString()==null?null:(data[19].toString().equals(Constant.WMS_ZENG_YUAN)?1:2));
				employees.setWhetherGinseng(data[20].toString()==null?null:data[20].equals(Constant.WMS_YES)?1:0);
				employees.setCinsengDate(data[21].toString()==null?null:DateUtil.StringToDate(data[21], DateUtil.FORMAT_DATE));
				employees.setHealthCare(data[22].toString()==null?"":data[22].toString());
				employees.setSociaSecurity(data[23].toString()==null?null:data[23].toString());
				employees.setAccumulationFund(data[24].toString()==null?null:data[24].toString());
				employees.setSeriousDisease(data[25].toString()==null?null:data[25].toString());
				employees.setBase(data[26].toString()==null?null:data[26].equals(Constant.WMS_YES)?1:0);
				employees.setSocialInsurance(data[27].toString().equals("")?null:Double.valueOf(data[27]));
				employees.setFertilityInsurance(data[28].toString().equals("")?null:Double.valueOf(data[28]));
				employees.setInductrialBase(data[29].toString().equals("")?null:Double.valueOf(data[29]));
				employees.setBasicMedical(data[30].toString().equals("")?null:Double.valueOf(data[30]));
				employees.setHousingFund(data[31].toString().equals("")?null:Double.valueOf(data[31]));
				employees.setSeriousDiseaseBase(data[32].toString().equals("")?null:Double.valueOf(data[32]));
				employees.setPaymentWay(data[33].toString());
				employees.setDeparture(0);
				employees.setPseudoDelete(0);
				if(enterprise!=null)employees.setEnterprise(em.find(Enterprise.class, enterprise.getEnterpriseId()));
				
				
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
	 * 统计新增人数
	 * @param enterpriseId
	 * @return
	 */
	public long newStaffCount(Integer enterpriseId){
		Query query=em.createQuery("select count(e) from  EnterpriseEmployees e where e.enterprise.enterpriseId=?1  and e.departure=0 and e.ginsengProtectNature=1 ");
		query.setParameter(1, enterpriseId);
		return (Long)query.getSingleResult();
		
	}
	/**
	 * 统计续保人数
	 * @param enterpriseId
	 * @return
	 */
	public long renewalPersonnel(Integer enterpriseId){
		Query query=em.createQuery("select count(e) from  EnterpriseEmployees e where e.enterprise.enterpriseId=?1 and e.ginsengProtectNature='续保'  and e.departure=0 ");
		query.setParameter(1, enterpriseId);
		return (Long)query.getSingleResult();
		
	}
	/**
	 * 统计参保人数
	 * @param enterpriseId
	 * @return
	 */
	public long ginsengPersonnel(Integer enterpriseId){
		Query query=em.createQuery("select count(e) from  EnterpriseEmployees e where e.enterprise.enterpriseId=?1 and e.whetherGinseng=1  and e.departure=0 ");
		query.setParameter(1, enterpriseId);
		return (Long)query.getSingleResult();
		
	}
	
	@SuppressWarnings("unchecked")
	public List<EnterpriseEmployees>  findWorkersIncreasedToEmployees(Integer enterpriseId)
	{
		Query query=em.createQuery("select e from  EnterpriseEmployees e where e.enterprise.enterpriseId=?1 and e.whetherGinseng=1 and e.departure=0  and e.ginsengProtectNature=1 order by employeesId asc ");
		query.setParameter(1, enterpriseId);
		return query.getResultList();
	}
	
	/**
	 * 查询新增或者续保，减员人员
	 * @param enterpriseId
	 * @param type
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<EnterpriseEmployees>    findNewStaffAndRenewalEmployees(Integer enterpriseId,String type)
	{
		
		Query query=em.createQuery("select e from  EnterpriseEmployees e where e.enterprise.enterpriseId=?1 " +
				"and e.ginsengProtectNature like ?2 and e.departure=0 order by employeesId asc ");
		query.setParameter(1, enterpriseId).setParameter(2, "%"+type+"%");
		return query.getResultList();
		
	}
	
	/**
	 * 批量上传增员；续保；
	 */
	@SuppressWarnings({ "unchecked", "static-access" })
	public List<String>  batchIncreaseEmployees(File file , String fiName,int number,int readRow,Integer enterpriseId)
	{
		int renewal=0; //续保
		int increase=0; //增员
		List<String>  messageList=new ArrayList<String>();
		Map<String,String> map = new HashMap<String,String>();
		GenerateSqlFromExcel excel =new GenerateSqlFromExcel();
		List<EnterpriseEmployees> employeesListPO=getAllEnterpriseEmployees(enterpriseId);
			try {
				List<String[]> arrayList = excel.generateStationBugSql(file,fiName,number,readRow);
				if(arrayList==null){messageList.add("数据格式出错!");return messageList;}
				
				for (int i = 0; i < arrayList.size(); i++)
				{
					String[] data = arrayList.get(i);
					messageList=uploadExcelDateByDatabaseEmployeesMatch(data,employeesListPO);
					if(messageList.size()==0)
					{
						EnterpriseEmployees reductionOrIncreaseEmployees=new EnterpriseEmployees();
						
						reductionOrIncreaseEmployees=structureExcelByEmployeesDate(data,employeesListPO,enterpriseId);
						if(reductionOrIncreaseEmployees!=null){
							if(reductionOrIncreaseEmployees.getEmployeesId()!=null && reductionOrIncreaseEmployees.getEmployeesId()!=0){
								 if(updateRenewalEmployees(reductionOrIncreaseEmployees)==true){
									 renewal++;
									 map.put("renewal", renewal+"");
									 messageList.add(map.toString());
								 }
								
							}else{
								 reductionOrIncreaseEmployees.setEnterprise(em.find(Enterprise.class, enterpriseId));
								 super.save(reductionOrIncreaseEmployees);
								 increase++;
								 map.put("increase", increase+"");
								 messageList.add(map.toString());
							}
							
						}
					}
						
				}
				
			}catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				messageList.add("文件出错请重试!");
			
		    }
	   return messageList;
	}
	
	/**
	 * 续保动作
	 * @param enterpriseEmployees
	 * @return
	 */
	
	public boolean updateRenewalEmployees(EnterpriseEmployees enterpriseEmployees)
	{
		try {
			em.createQuery("update EnterpriseEmployees ee set ee.cardNumber=?1,ee.startContractDeadline=?2 ," +
					"ee.endContractDeadline=?3,ee.sociaSecurity=?4,ee.healthCare=?5,ee.accumulationFund=?6," +
					"ee.ginsengProtectNature=?7,ee.note=?8,ee.cinsengDate=?9 where ee.employeesId=?10")
			   .setParameter(1, enterpriseEmployees.getCardNumber()).setParameter(2, enterpriseEmployees.getStartContractDeadline())
			   .setParameter(3, enterpriseEmployees.getEndContractDeadline()).setParameter(4, enterpriseEmployees.getSociaSecurity())
			   .setParameter(5, enterpriseEmployees.getHealthCare()).setParameter(6, enterpriseEmployees.getAccumulationFund())
			   .setParameter(7, enterpriseEmployees.getGinsengProtectNature()).setParameter(8, enterpriseEmployees.getNote())
			   .setParameter(9, enterpriseEmployees.getCinsengDate()).setParameter(10, enterpriseEmployees.getEmployeesId()).executeUpdate();
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}

	}
	
	
	/**
	 * 增员、续保，并且与数据库匹配
	 * @param fileDate
	 * @param enterpriseEmployeesList
	 * @return message
	 * @date 2013-10-16
	 * @version 1.0 版本
	 */
	public List<String> uploadExcelDateByDatabaseEmployeesMatch(String[] fileDate, List<EnterpriseEmployees> enterpriseEmployeesList)
	{
		List<String> promptMessage=new ArrayList<String>();
		int sameName=0;
		int sameCarNumber=0;
		int sameCarNumberTotal=0;
		
		String excelEmployeesName=fileDate[1]==null?"":fileDate[1].toString();
		if(StringUtil.isEmpty(excelEmployeesName)){promptMessage.add("姓名不能为空");return promptMessage;}
		
		String excelCarNumber=fileDate[2]==null?"":fileDate[2].toString();
		//String excelRecruiting=fileDate[8]==null?"":fileDate[8].toString();
		
		String employeesName="";
		String carNumber="";
		for (EnterpriseEmployees emp : enterpriseEmployeesList)
		{
				employeesName=emp.getEmployeesName();
				carNumber=emp.getCardNumber();
			
				if(excelEmployeesName.equals(employeesName))
				{
					sameName++;
					if(sameName>1)
					{
						if(carNumber.equals(excelCarNumber))
						{
							sameCarNumber++;
							if(sameCarNumber>1){
								sameCarNumberTotal++;
								}
						}
					}
				}		
			}
			if(sameCarNumberTotal>1)
			{
				String message="数据库存在 ："+sameName+"个 ，："+excelEmployeesName+"，身份证相同："+sameCarNumberTotal+"个， 身份证号码："+excelCarNumber;
				promptMessage.add(message);
			}

			
		return promptMessage;
		
	}
	
	
	/**
	 * excel导入的数据封装到EnterpriseEmployees 这个对象中
	 * @param fileDate
	 * @param enterpriseEmployeesList
	 * @return
	 */
	public EnterpriseEmployees structureExcelByEmployeesDate(String[] fileDate, List<EnterpriseEmployees> enterpriseEmployeesList,Integer enterpriseId)
	{
		
		int sameName=0;
		int cardNumberTotal=0;
		EnterpriseEmployees  emp=new EnterpriseEmployees();
		
		String excelEmployeesName=fileDate[1]==null?"":fileDate[1].toString();
		String excelCarNumber=fileDate[2]==null?"":fileDate[2].toString();
		Date startContractDeadline=DateUtil.StringToDate(fileDate[3]==null?"":fileDate[3].toString(), DateUtil.FORMAT_DATE);
		Date endContractDeadline=DateUtil.StringToDate(fileDate[4]==null?"":fileDate[4].toString(), DateUtil.FORMAT_DATE);
		String sociaSecurity=fileDate[5]==null?"":fileDate[5].toString();
		String healthCare=fileDate[6]==null?"":fileDate[6].toString();
		String accumulationFund=fileDate[7]==null?"":fileDate[7].toString();
		String excelRecruiting=fileDate[8]==null?"":fileDate[8].toString();
		Date cinsengDate=DateUtil.StringToDate(fileDate[9]==null?"":fileDate[9].toString(), DateUtil.FORMAT_DATE);
		String note=fileDate[10]==null?"":fileDate[10].toString();
		
		String employeesName="";
		String carNumber="";

		if(!StringUtil.isEmpty(excelRecruiting) && excelRecruiting.equals(Constant.WMS_XU_BAO))
		{
			for (EnterpriseEmployees employees : enterpriseEmployeesList) 
				{
					employeesName=employees.getEmployeesName();
					carNumber=employees.getCardNumber();
					if(excelEmployeesName.equals(employeesName))
					{
						sameName++;
					    if(sameName>1)
					    {
					    	
					    	if(carNumber.equals(excelCarNumber))//续保
							{
					    		cardNumberTotal++;
							}
					    	if(cardNumberTotal==1){
					    		emp.setEmployeesId(employees.getEmployeesId());
					    		emp.setCardNumber(excelCarNumber);
					    		emp.setStartContractDeadline(startContractDeadline);
					    		emp.setEndContractDeadline(endContractDeadline);
					    		//医保
					    		emp.setSociaSecurity(sociaSecurity);
					    		//社保
					    		emp.setHealthCare(healthCare);
					    		//公积金
					    		emp.setAccumulationFund(accumulationFund);
					    		
					    		int natrue=excelRecruiting.equals(Constant.WMS_XU_BAO)?2:1;
					    		/*续保*/
					    		emp.setGinsengProtectNature(natrue);
					    		
					    		emp.setNote(note);
					    		
					    		emp.setCinsengDate(cinsengDate);
					    	}
					    	
					    }
					    if(sameName==1){
					    	
					    	emp.setEmployeesId(employees.getEmployeesId());
				    		emp.setCardNumber(excelCarNumber);
				    		emp.setStartContractDeadline(startContractDeadline);
				    		emp.setEndContractDeadline(endContractDeadline);
				    		//医保
				    		emp.setSociaSecurity(sociaSecurity);
				    		//社保
				    		emp.setHealthCare(healthCare);
				    		//公积金
				    		emp.setAccumulationFund(accumulationFund);
				    		
				    		int natrue=excelRecruiting.equals(Constant.WMS_XU_BAO)?2:1;
				    		/*续保*/
				    		emp.setGinsengProtectNature(natrue);
				    		
				    		emp.setCinsengDate(cinsengDate);
				    		
				    		emp.setNote(note);
					    	
					    }
				   }
			}
		}
		
		//增员信息
		if(!StringUtil.isEmpty(excelRecruiting) && excelRecruiting.equals(Constant.WMS_ZENG_YUAN))
		{
			
			emp.setEmployeesName(employeesName);
			
    		emp.setCardNumber(excelCarNumber);
    		
    		emp.setStartContractDeadline(startContractDeadline);
    		emp.setEndContractDeadline(endContractDeadline);
    		//医保
    		emp.setSociaSecurity(sociaSecurity);
    		//社保
    		emp.setHealthCare(healthCare);
    		//公积金
    		emp.setAccumulationFund(accumulationFund);
    		
    		int natrue=excelRecruiting.equals(Constant.WMS_XU_BAO)?2:1;
    		/*续保*/
    		emp.setGinsengProtectNature(natrue);
    		
    		emp.setCinsengDate(cinsengDate);
    		
    		emp.setNote(note);
		}
		
		
		
		return emp;
	}
	
	/**
	 * 匹配上传增员，人员是否已经离职；或者存在重复数据
	 * @date 2013-10-16
	 * @version 1.0
	 * @author jameslin
	 */
	public String isExistSameToByEnterprise(String employeesName,String cardNumber, Integer enterpriseId){
		
		int    sameName=0;          //相同名字
		int    cardNumberSame=0;   //相同身份证
		int    sameCardTotal=0;    //几张相同身份证
		String message="";
		Date   departureDate;     //离职日期
		Date   endContractDate;   //合同结束日期
		List<EnterpriseEmployees> enterpriseEmployeesPO=getAllEnterpriseEmployees();
		for (EnterpriseEmployees emp : enterpriseEmployeesPO) {
			if(emp.getEmployeesName().equals(employeesName))
			{
				sameName++;
				if(sameName>1){
					if(emp.getCardNumber().equals(cardNumber)){
						cardNumberSame++;
						if(cardNumberSame>1){
							sameCardTotal++;
						}
					}
				}
				if(sameName>1 && cardNumberSame==1)
				{
					String fullName=emp.getEnterprise().getFullName();
					endContractDate=emp.getEndContractDeadline();
					if(emp.getEnterprise().getEnterpriseId()!=enterpriseId){
						departureDate=emp.getReductionDate();
						if(departureDate!=null)
						{
							if(DateUtil.compareDateWithNow(departureDate)==-1)
							{
								
								 message=employeesName+"已在,"+fullName+",还未离职,离职时间为:"+departureDate+",合同到期为:"+endContractDate;
							}
						}
						if(departureDate==null || departureDate.equals(""))
						{
							    message=employeesName+"已在:"+fullName+",合同到期为:"+endContractDate;
						}
					}
				}
				if(cardNumberSame>1){
					
				}
			}
		}
		
		
		
		return message;
		
	}
	
	/**
	 * 
	 * @return 所有企业员工
	 */
	@SuppressWarnings("unchecked")
	public List<EnterpriseEmployees> getAllEnterpriseEmployees()
	{
		Query query = em.createQuery("select e from EnterpriseEmployees e where e.departure=0 and e.reduction=0");
		return query.getResultList();
	}


	/**
	 * 修改企业员工信息
	 */
	public boolean updateEnterpriseEmployees(EnterpriseEmployees enterpriseEmployees) {
		
		try {
			
			Query query=em.createQuery("update EnterpriseEmployees set employeesName=?1," +
					"employeesSex=?2," +
					"householdRegister=?3," +
					"nativePlace=?4," +
					"photo=?5," +
					"cardNumber=?6," +
					"phone=?7," +
					"serviceCost=?8," +
					"socialInsurance=?9," +
					"fertilityInsurance=?10," +
					"contractNo=?11," +
					"homeAddress=?12," +
					"bankCardNumber=?13," +
					"bank=?14," +
					"industry=?15," +
					"jobs=?16," +
					"maritalStatus=?17," +
					"levelEducation=?18," +
					"startContractDeadline=?19," +
					"endContractDeadline=?20," +
					"whetherGinseng=?21," +
					"sociaSecurity=?22," +
					"healthCare=?23," +
					"seriousDisease=?25," +
					"seriousDiseaseBase=?26," +
					"ginsengProtectNature=?27," +
					"cinsengDate=?28," +
					"base=?29," +
					"paymentWay=?30," +
					"inductrialBase=?31," +
					"housingFund=?32," +
					"basicMedical=?33," +
					"pseudoDelete=?34 where employeesId=?35");
			query.setParameter(1, enterpriseEmployees.getEmployeesName())
			     .setParameter(2, enterpriseEmployees.getEmployeesSex())
			     .setParameter(3, enterpriseEmployees.getHouseholdRegister())
			     .setParameter(4, enterpriseEmployees.getNativePlace())
			     .setParameter(5, enterpriseEmployees.getPhoto())
			     .setParameter(6, enterpriseEmployees.getCardNumber())
			     .setParameter(7, enterpriseEmployees.getPhone())
			     .setParameter(8, enterpriseEmployees.getServiceCost())
			     .setParameter(9, enterpriseEmployees.getSocialInsurance())
			     .setParameter(10, enterpriseEmployees.getFertilityInsurance())
			     .setParameter(11, enterpriseEmployees.getContractNo())
			     .setParameter(12, enterpriseEmployees.getHomeAddress())
			     .setParameter(13, enterpriseEmployees.getBankCardNumber())
			     .setParameter(14, enterpriseEmployees.getBank())
			     .setParameter(15, enterpriseEmployees.getIndustry())
			     .setParameter(16, enterpriseEmployees.getJobs())
			     .setParameter(17, enterpriseEmployees.getMaritalStatus())
			     .setParameter(18, enterpriseEmployees.getLevelEducation())
			     .setParameter(19, enterpriseEmployees.getStartContractDeadline())
			     .setParameter(20, enterpriseEmployees.getEndContractDeadline())
			     .setParameter(21, enterpriseEmployees.getWhetherGinseng())
			     .setParameter(22, enterpriseEmployees.getSociaSecurity())
			     .setParameter(23, enterpriseEmployees.getHealthCare())
			     .setParameter(25, enterpriseEmployees.getSeriousDisease())
			     .setParameter(26, enterpriseEmployees.getSeriousDiseaseBase())
			     .setParameter(27, enterpriseEmployees.getGinsengProtectNature())
			     .setParameter(28, enterpriseEmployees.getCinsengDate())
			     .setParameter(29, enterpriseEmployees.getBase())
			     .setParameter(30, enterpriseEmployees.getPaymentWay())
			     .setParameter(31, enterpriseEmployees.getInductrialBase())
			     .setParameter(32, enterpriseEmployees.getHousingFund())
			     .setParameter(33, enterpriseEmployees.getBasicMedical())
			     .setParameter(34, enterpriseEmployees.getPseudoDelete())
			     .setParameter(35, enterpriseEmployees.getEmployeesId()).executeUpdate();
			
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	
	/**
	 * 查询离职员工
	 * @return
	 */
    @SuppressWarnings("unchecked")
	public List<EnterpriseEmployees>  findEmployeesDeparture(Integer enterpriseId){
    	Query query = em.createQuery("select e from EnterpriseEmployees e where e.departure=1 and e.enterprise.enterpriseId=?1 ");
    		  query.setParameter(1, enterpriseId);
    	
    	
		return query.getResultList();

    }
    /**
	 * 查询隐藏员工
	 * @return
	 */
    @SuppressWarnings("unchecked")
	public List<EnterpriseEmployees>  findEmployeesHidden(Integer enterpriseId){
    	Query query = em.createQuery("select e from EnterpriseEmployees e where e.pseudoDelete=0 and e.enterprise.enterpriseId=?1");
    		  query.setParameter(1, enterpriseId);
    	
    	
		return query.getResultList();

    }


    
   /*
    * 统计当前企业增员与减员明细
    */
    public long findInsuranceWithMonthCount(Integer enterpriseId)
    {
    	
    	
    	
    	
    	return 0;
    }
    
    /**
	 * 查看统计企业增减员参保明细表
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<EnterpriseEmployees> getInsuranceWithEmployeeList(Integer enterpriseId,Integer year,Integer month)
	{
		Query query = null ;
		try {
			query = em.createQuery("select e from EnterpriseEmployees e where (e.ginsengProtectNature=1 or e.ginsengProtectNature=2 )" +
					" and e.pseudoDelete=0 and e.enterprise.enterpriseId=?1 " +
					" and e.departure=0 and  year(e.cinsengDate )=?2 and month( e.cinsengDate )=?3");
			  query.setParameter(1, enterpriseId).setParameter(2, year).setParameter(3, month);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	
	
	return query.getResultList();

		
	}
    
    
    
    
    
    
    
    

	public List<String> getExcelFiledNameList() {
		// TODO Auto-generated method stub
		return null;
	}


	/**
	 * 批量上传减员信息表
	 */
	// TODO Auto-generated method stub
	@SuppressWarnings({ "unchecked", "static-access" })
	public List<String> uploadExcelByInsuranceReduction(File file, String fileName, Integer number, Integer readRow,Integer enterpriseId) {
		
		GenerateSqlFromExcel excel =new GenerateSqlFromExcel();
		List<String>   messageList=new ArrayList<String>();
		  try {
			  List<String[]> arrayList=excel.generateStationBugSql(file,fileName,number,readRow);
				if(arrayList==null){messageList.add("请确认你上传数据的完整性!"); return messageList;}
				for (int i = 0; i < arrayList.size(); i++) {
					String[] data = arrayList.get(i);
					messageList.addAll(uploadExcelDateByDatabaseEmployees(data,enterpriseId));
					
				}
		} catch (Exception e) {
			e.printStackTrace();
			messageList.add("未知的文件异常!");
		}
		
	return messageList;
		
	}
	
	/**
	 * 上传的exce减员数据与数据库人员进行匹配
	 * @param fileDate
	 * @param enterpriseId
	 * @return 存在提示信息(isExistingEmployees)
	 */
    public List<String>  uploadExcelDateByDatabaseEmployees(String[] fileDate,Integer enterpriseId){
    	        
		    	int      count=0;
		    	int      employeesId=0;
		    	int      reduction=0;
		    	String   note="";
		    	String message=new String();
		    	List<String>  isExistingEmployees=new ArrayList<String>();
		    	
		    	//TODO 待解决上传空数据
		        //if(isEmptyString(fileDate)==false)return isExistingEmployees;
		    	
		    	String carNumber=fileDate[2]==null?"":fileDate[2];
		    	String employeesNameDate=fileDate[1]==null?"":fileDate[1];
		    	Date   reductionDate=DateUtil.StringToDate(fileDate[3]==null?"":fileDate[3].toString(), DateUtil.FORMAT_DATE);
		    	if(reductionDate==null){isExistingEmployees.add("减员日期不能为空!");return isExistingEmployees;}
		    	
		    	String noteExcel=fileDate[4]==null?"":fileDate[4].toString();
		    	if(StringUtil.isEmpty(carNumber.trim())){ isExistingEmployees.add(" 身份证不能为空!"); return isExistingEmployees;}   		 
		    	
		    	List<EnterpriseEmployees> enterpriseEmployeesList=getAllEnterpriseEmployees(enterpriseId);
		    	
		    	for (EnterpriseEmployees emp : enterpriseEmployeesList)
		    	{
		    		 String empCarNumber=emp.getCardNumber();
		    		 String empEmployeesName=emp.getEmployeesName();
					 if(carNumber.trim().equals(empCarNumber.trim()) &&  employeesNameDate.trim().equals(empEmployeesName.trim()))
					 {
						 count++;
						 if(emp.getReduction()==0){
							 reduction++;
							 employeesId=emp.getEmployeesId();
							 note=emp.getNote();
						 }else{
							 message=new String(employeesNameDate+"已减员,时间为: "+emp.getReductionDate()+"身份证号码:"+emp.getCardNumber());
							 isExistingEmployees.add(message);
						 }
					 }
				}
		    	if(reduction==1)
				 {
		    		 int reducontNumber=0;
		    		  if(!StringUtil.isEmpty(noteExcel))note=noteExcel;
		    		  EnterpriseEmployees templEmployees=new EnterpriseEmployees();
		    		  templEmployees.setReductionDate(reductionDate);
		    		  templEmployees.setNote(note);
		    		  templEmployees.setEmployeesId(employeesId);
		    		  
		    		  //减员动作
		    		  if( updateReduction(templEmployees)==true){
		    			  reducontNumber++;
		    			  isExistingEmployees.add(reducontNumber+"");
		    		  }
		    		   
				 }
				 if(count==0)
				 {
					 message=new String("数据库不存在:"+employeesNameDate+"身份证:"+carNumber);
					 isExistingEmployees.add(message);					 
					 
				 }if(count>1){
					 message=new String(employeesNameDate+"身份证:"+carNumber+"相同:"+count+"个");
					 isExistingEmployees.add(message);
				 }
    	
    	return isExistingEmployees;
    	
    }
	
	public boolean isEmptyString(String[] dateFile){
		for (String str : dateFile) {
			if(str.length()==0 && str.trim().isEmpty())return false;
		}
		
		
		return true;
	}
	/**
	 * 进行减员操作
	 * @param reductionDate
	 * @param employeesId
	 * @return
	 */
	public boolean updateReduction(EnterpriseEmployees enterpriseEmployees)
	{
		try {
			em.createQuery(" update EnterpriseEmployees e set e.reduction=1,e.reductionDate=?1,e.note=?2 where e.employeesId=?3")
            .setParameter(1, enterpriseEmployees.getReductionDate()).setParameter(2, enterpriseEmployees.getNote())
            .setParameter(3, enterpriseEmployees.getEmployeesId()).executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
		
	}


}
