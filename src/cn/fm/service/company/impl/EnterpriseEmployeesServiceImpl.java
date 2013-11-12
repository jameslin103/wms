package cn.fm.service.company.impl;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
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
	
	
	private String messageError="";
	
	
	public void save(EnterpriseEmployees entity)
	{
		super.save(entity);	
	}


	
	@SuppressWarnings("unchecked")
	public List<EnterpriseEmployees> getAllEnterpriseEmployees(Integer enterpriseId)
	{
		try {
			Query query = em.createQuery("select e from EnterpriseEmployees e where e.enterprise.enterpriseId=?1 and e.departure=0 and e.reduction=0 and e.pseudoDelete=0");
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
	public List<String> saveImportExcelEmployees(File file , String fiName,int number,int readRow, Enterprise enterprise) {
		List<String>  messageList=new ArrayList<String>();
		List<EnterpriseEmployees>  employeesListVO=new ArrayList<EnterpriseEmployees>();
		GenerateSqlFromExcel excel =new GenerateSqlFromExcel();
		try {
			List<String[]> arrayList=excel.generateStationBugSql(file,fiName,number,readRow);
			if(arrayList==null || arrayList.equals("")){messageList.add("请填充你要上传的数据!"); return messageList;}
			for (int i = 0; i < arrayList.size(); i++) 
			{
				String[] data = arrayList.get(i);
				String messge=isExitSameEnterpriseEmployees(data);
				if(!StringUtil.isEmpty(messge))	messageList.add(messge);
				if(messageList.size()==0)
				{
					employeesListVO.add(recordEnterpriseEmployeesExcelDate(data));
				}	
				
			}
			if(messageList.size()==0){
				try {
					saveEnterpriseEmployees(employeesListVO,enterprise);
				} catch (Exception e) {
					e.printStackTrace();
					messageList.add("数据格式错误!");
				}
					
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
			messageList.add("文件格式错误!");
		}
		return messageList;
	}
	/**
	 * 判断是否未离职的员工；重复上传数据
	 * @param employees
	 * @return
	 */
	public  String isExitSameEnterpriseEmployees(String[] fileDate){
		
		String message=null;
		
		String empName=fileDate[2].toString()==""?null:fileDate[2].toString();
		String empCarNumber=fileDate[10].toString()==""?null:fileDate[10].toString();
		
		String empName_PO=null;
		String empCarNumber_PO=null;
		Date endContractDeadline_PO=null;
		String fullName=null;
		
		
		int isSameName=0;
		int same_carNumber=0;
		if(StringUtil.isEmpty(empName))
		{
			
			message="Excel（当中存在多行空数据数据请删除多余的空格行!）"; 
			return message;
		}
		if(empName!=null)
		{
			List<EnterpriseEmployees> EnterpriseEmployeesListPO=getAllEnterpriseEmployees();
			if(EnterpriseEmployeesListPO==null || EnterpriseEmployeesListPO.size()==0)return null;
			for (EnterpriseEmployees empPO : EnterpriseEmployeesListPO)
				{
					empName_PO=empPO.getEmployeesName()==null?null:empPO.getEmployeesName();
					empCarNumber_PO=empPO.getCardNumber()==null?null:empPO.getCardNumber();
					if(empName_PO!=null)
					{
						if(empName.equals(empName_PO))
						{
							isSameName++;
							fullName=empPO.getEnterprise().getFullName();
							if(empCarNumber!=null && empCarNumber_PO!=null)
							{
								if(empCarNumber_PO.equals(empCarNumber))
								{
									same_carNumber++;
									endContractDeadline_PO=empPO.getEndContractDeadline();
							 }
						  }
				
					  }
				 }
			}
			if(same_carNumber>0 && same_carNumber>0)
			{
				String endDate=DateUtil.dateToString(endContractDeadline_PO,DateUtil.FORMAT_DATE);
				String currentDate=DateUtil.getCurrentTime();
				if(DateUtil.timeCompare(currentDate,endDate)==-1){
					message=empName+"，已在，"+fullName+"，未离职，合同到期为，"+endDate;
				}
			}
			if(isSameName>0 && same_carNumber==0)
			{
				if(StringUtil.isEmpty(empCarNumber)){
					message="数据库中存在，"+isSameName+"个，"+empName+"，请输入身份证!";
				}
				
			}
		}
		
		return message;
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * 构造exce传入的数据
	 * @param data
	 * @return
	 */
	public EnterpriseEmployees   recordEnterpriseEmployeesExcelDate(String[] data)
	{
		if(data==null || data.equals(""))return null;
		EnterpriseEmployees  employees=new EnterpriseEmployees();
		employees.setContractNo(data[1].toString());
		employees.setEmployeesName(data[2].toString());
		employees.setEmployeesSex(data[3].toString());
		employees.setNativePlace(data[4].toString());
		employees.setHouseholdRegister(data[5]==null?null:data[5].equals(Constant.WMS_YES)?1:0);
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
		/*社保 */
		employees.setHealthCare(data[22].toString()==null?"":data[22].toString());
		/*医保*/
		employees.setSociaSecurity(data[23].toString()==null?null:data[23].toString());
		/*公积金*/
		employees.setAccumulationFund(data[24].toString()==null?null:data[24]);
		/*大病统筹*/
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
		return employees;
		
	}
	/**
	 * 保存cxcel上传的数据
	 * @param enterpriseEmployeesList
	 */
	public void saveEnterpriseEmployees(List<EnterpriseEmployees> enterpriseEmployeesList,Enterprise enterprise){
		if(enterpriseEmployeesList==null ||enterpriseEmployeesList.size()==0)return;
		EnterpriseEmployees  employees;
		for (EnterpriseEmployees emp : enterpriseEmployeesList) {
			if(emp==null || emp.equals(""))continue;
			employees=new EnterpriseEmployees();
			employees.setContractNo(emp.getContractNo());
			employees.setEmployeesName(emp.getEmployeesName());
			employees.setEmployeesSex(emp.getEmployeesSex());
			employees.setNativePlace(emp.getContractNo());
			employees.setHouseholdRegister(emp.getHouseholdRegister());
			employees.setHomeAddress(emp.getHomeAddress());
			employees.setMaritalStatus(emp.getMaritalStatus());
			employees.setLevelEducation(emp.getLevelEducation());
			employees.setPhoto(emp.getPhoto());
			employees.setCardNumber(emp.getCardNumber());
			employees.setPhone(emp.getPhone());
			employees.setIndustry(emp.getIndustry());
			employees.setJobs(emp.getJobs());
			employees.setBank(emp.getBank());
			employees.setBankCardNumber(emp.getCardNumber());
			employees.setStartContractDeadline(emp.getStartContractDeadline());
			employees.setEndContractDeadline(emp.getEndContractDeadline());
			employees.setServiceCost(emp.getServiceCost());
			employees.setGinsengProtectNature(emp.getGinsengProtectNature());
			employees.setWhetherGinseng(emp.getWhetherGinseng());
			employees.setCinsengDate(emp.getCinsengDate());
			employees.setHealthCare(emp.getHealthCare());
			employees.setSociaSecurity(emp.getSociaSecurity());
			employees.setAccumulationFund(emp.getAccumulationFund());
			employees.setSeriousDisease(emp.getSeriousDisease());
			employees.setBase(emp.getBase());
			employees.setSocialInsurance(emp.getSocialInsurance());
			employees.setFertilityInsurance(emp.getFertilityInsurance());
			employees.setInductrialBase(emp.getInductrialBase());
			employees.setBasicMedical(emp.getBasicMedical());
			employees.setHousingFund(emp.getHousingFund());
			employees.setSeriousDiseaseBase(emp.getSeriousDiseaseBase());
			employees.setPaymentWay(emp.getPaymentWay());
			employees.setDeparture(emp.getDeparture());
			employees.setPseudoDelete(emp.getPseudoDelete());
			
			if(enterprise!=null)employees.setEnterprise(em.find(Enterprise.class, enterprise.getEnterpriseId()));
			try {
				super.save(employees);
			} catch (Exception e) {
				e.printStackTrace();
			}
			

		}
		
	}
	
	
	
	
	
	public List<EnterpriseEmployees> getEnterpriseEmployeesSalaryDetail(Serializable entityId) {
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
		Query query=em.createQuery("select count(e) from  EnterpriseEmployees e " +
				"where e.enterprise.enterpriseId=?1   " +
				" and e.ginsengProtectNature=1 " +
				" and e.departure=0" +
				" and e.reduction=0 " +
				" and e.pseudoDelete=0");
		query.setParameter(1, enterpriseId);
		return (Long)query.getSingleResult();
		
	}
	/**
	 * 统计续保人数
	 * @param enterpriseId
	 * @return
	 */
	public long renewalPersonnel(Integer enterpriseId){
		Query query=em.createQuery("select count(e) from  EnterpriseEmployees e " +
				"where e.enterprise.enterpriseId=?1 " +
				" and e.ginsengProtectNature=2  " +
				" and e.departure=0 " +
				" and e.reduction=0 " +
				" and e.pseudoDelete=0");
		query.setParameter(1, enterpriseId);
		return (Long)query.getSingleResult();
		
	}
	/**
	 * 统计参保人数
	 * @param enterpriseId
	 * @return
	 */
	public long ginsengPersonnel(Integer enterpriseId){
		Query query=em.createQuery("select count(e) from  EnterpriseEmployees e " +
				"where e.enterprise.enterpriseId=?1 " +
				"and e.whetherGinseng=1" +
				" and e.departure=0 " +
				" and e.reduction=0 " +
				" and e.pseudoDelete=0");
		query.setParameter(1, enterpriseId);
		return (Long)query.getSingleResult();
		
	}
	/**
	 * 统计减员人数
	 * @param enterpriseId
	 * @return
	 */
	public long reductionTotal(Integer enterpriseId)
	{
		try {
			Query query=em.createQuery("select count(e) from  EnterpriseEmployees e " +
					"where e.enterprise.enterpriseId=?1 " +
					" and e.departure=0 " +
					" and e.reduction=1 " +
					" and e.pseudoDelete=0");
			query.setParameter(1, enterpriseId);
			return (Long)query.getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
		
		
	}
	/**
	 * 增员减员参保详细信息
	 */
	@SuppressWarnings("unchecked")
	public List<EnterpriseEmployees>  findWorkersIncreasedToEmployees(Integer enterpriseId)
	{
		Query query=em.createQuery("select e from  EnterpriseEmployees e " +
				"where e.enterprise.enterpriseId=?1 " +
				" and (e.whetherGinseng=1 " +
				" or e.reduction=1  " +
				" or e.ginsengProtectNature=1 )" +
				" and e.departure=0  " +
				" and e.pseudoDelete=0  " +
				" order by employeesId asc ");
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
				"and e.ginsengProtectNature=?2 and e.departure=0 order by employeesId asc ");
		query.setParameter(1, enterpriseId).setParameter(2,type);
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
		
		GenerateSqlFromExcel excel =new GenerateSqlFromExcel();
		List<EnterpriseEmployees> employeesListPO=getAllEnterpriseEmployees(enterpriseId);
			try {
				List<String[]> arrayList = excel.generateStationBugSql(file,fiName,number,readRow);
				if(arrayList==null){messageList.add("数据格式出错!");return messageList;}
				
				for (int i = 0; i < arrayList.size(); i++)
				{
					String[] data = arrayList.get(i);
					
					
					EnterpriseEmployees reductionOrIncreaseEmployees=new EnterpriseEmployees();
					//上传员工进行匹配
					
					reductionOrIncreaseEmployees=isExistSameToByEnterprise(data,employeesListPO);
					
					if(StringUtil.isEmpty(messageError))
					{
						if(reductionOrIncreaseEmployees!=null)
						{
							if(reductionOrIncreaseEmployees.getGinsengProtectNature()!=null && reductionOrIncreaseEmployees.getGinsengProtectNature()==1)
							{
									 if(updateRenewalEmployees(reductionOrIncreaseEmployees)==true)
									 {
										 renewal++;
									 }
									
								}
								if(reductionOrIncreaseEmployees.getGinsengProtectNature()!=null && reductionOrIncreaseEmployees.getGinsengProtectNature()==2)
								{
									
									if(updateRenewalEmployees(reductionOrIncreaseEmployees)==true)
								    {
											 increase++;
									}
								}
								
									
						  }
					}
				}
				if(StringUtil.isEmpty(messageError))
				{
					if(renewal>0)
					{
						 messageList.add(renewal+"");
					}
					if(increase>0)
					{
						 messageList.add(increase+"");
					}
				}else{
					messageList.add(messageError);
				}
				
			}catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				messageList.add("文件出错请重试!");
			
		    }
	   return messageList;
	}
	
	/**
	 * 续保动作,或者增员动作
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
	 * 查询全库人员匹配上传增员，人员是否已经离职；或者存在重复数据
	 * @date 2013-10-16
	 * @version 1.0
	 * @author jameslin
	 */
	public EnterpriseEmployees isExistSameToByEnterprise(String[] fileDate, List<EnterpriseEmployees> enterpriseEmployeesList){
		this.messageError="";
		EnterpriseEmployees   enterpriseEmployees=null;
		
		int    sameName=0;             //相同名字
		int    cardNumberSame=0;       //相同身份证
		int    sameCardTotal=0;        //几张相同身份证
		
		Integer  employeesId=0;
		
		String excelEmployeesName=fileDate[1]==null?"":fileDate[1].toString();
		String excelCarNumber=fileDate[2]==null?"":fileDate[2].toString();
		
		
		
		for (EnterpriseEmployees emp : enterpriseEmployeesList)
		{
			String empEolyeesName=emp.getEmployeesName();
			if(empEolyeesName==null)continue;
			if(empEolyeesName.equals(excelEmployeesName))
			{
				sameName++;
				employeesId=emp.getEmployeesId();
				if(sameName>1){
					String cardId=emp.getCardNumber();
					if(StringUtil.isEmpty(cardId))continue;
					if(emp.getCardNumber().equals(excelCarNumber))
					{
						cardNumberSame++;
						if(cardNumberSame>1)
						{
							sameCardTotal++;
						}
					}
				}
			}
			
		}
		
		if(sameName>1 && cardNumberSame>1)
		{
			messageError="数据库存在，"+sameName+"个，同名："+excelEmployeesName+"，身份证相同"+cardNumberSame+"张,号码为："+excelCarNumber;
			
		}
		else if(sameName>1 && cardNumberSame==0)
		{
			messageError="数据库存在，"+sameName+"个，同名："+excelEmployeesName+"，身份证为空，请确定身份证!";
			
		}
		else if(sameName==0)
		{
			messageError="数据库中不存在:"+excelEmployeesName;
		}else{
			if(StringUtil.isEmpty(messageError) && employeesId!=0){
				enterpriseEmployees=new EnterpriseEmployees();
				enterpriseEmployees=temporaryBuildingEmployees(fileDate,employeesId);
			}
		}
		
		return enterpriseEmployees;
	}
	
	/**
	 * 封装excel上传的数据
	 * @param fileDate
	 * @param employeesId
	 * @return
	 */
	public EnterpriseEmployees temporaryBuildingEmployees(String[] fileDate,Integer employeesId )
	{
		EnterpriseEmployees empVO=new EnterpriseEmployees();
		String excelEmployeesName=fileDate[1]==null?"":fileDate[1].toString();
		String excelCarNumber=fileDate[2]==null?"":fileDate[2].toString();
		String sociaSecurity=fileDate[3]==null?"":fileDate[3].toString();
		String healthCare=fileDate[4]==null?"":fileDate[4].toString();
		String accumulationFund=fileDate[5]==null?"":fileDate[5].toString();
		String excelRecruiting=fileDate[6]==null?"":fileDate[6].toString();
		Date cinsengDate=DateUtil.StringToDate(fileDate[7]==null?"":fileDate[7].toString(), DateUtil.FORMAT_DATE);
		String note=fileDate[8]==null?"":fileDate[8].toString();
		
		
		
		empVO.setEmployeesId(employeesId);
		empVO.setEmployeesName(excelEmployeesName);
		
		empVO.setCardNumber(excelCarNumber);
		//医保
		empVO.setSociaSecurity(sociaSecurity);
		//社保
		empVO.setHealthCare(healthCare);
		//公积金
		empVO.setAccumulationFund(accumulationFund);
		
		int natrue=excelRecruiting.equals(Constant.WMS_XU_BAO)?2:1;
		/*续保*/
		empVO.setGinsengProtectNature(natrue);
		
		empVO.setCinsengDate(cinsengDate);
		
		empVO.setNote(note);
		
		
		
		return empVO;
	}
	
	/**
	 * 
	 * @return 所有企业员工
	 */
	@SuppressWarnings("unchecked")
	public List<EnterpriseEmployees> getAllEnterpriseEmployees()
	{
		Query query = em.createQuery("select e from EnterpriseEmployees e where e.departure=0 and e.reduction=0 and pseudoDelete=0");
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
			em.createQuery(" update EnterpriseEmployees e set e.reduction=1, e.ginsengProtectNature=3,e.reductionDate=?1,e.note=?2 where e.employeesId=?3")
            .setParameter(1, enterpriseEmployees.getReductionDate()).setParameter(2, enterpriseEmployees.getNote())
            .setParameter(3, enterpriseEmployees.getEmployeesId()).executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
		
	}


	/**
	 * 查询参保人员是否完成
	 */
	@SuppressWarnings("unchecked")
	public List<EnterpriseEmployees> findRecutionState(Integer enterpriseId,Integer month,Integer year) {
		try {
			return   em.createQuery("select o " +
					" from EnterpriseEmployees o " +
					"where o.enterprise.enterpriseId=?1" +
					" and month(o.cinsengDate)=?2" +
					" and year(o.cinsengDate)=?3")
					.setParameter(1, enterpriseId)
					.setParameter(2, month)
					.setParameter(3, year)
					.getResultList();

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	/**
	 * 修改增减员状态
	 * @param enterpriseId
	 * @param recutionState
	 */
	public void updateRecutionState(Integer enterpriseId,Integer recutionState,String reductionNote,Integer month,Integer year)
	{
		
		try {
			em.createQuery("update EnterpriseEmployees o set o.reductionState=?1,o.reductionNote=?2 where " +
					"o.enterprise.enterpriseId=?3" +
					" and o.departure=0 " +
					" and o.pseudoDelete=0" +
					" and month(o.cinsengDate)=?4" +
					" and year(o.cinsengDate)=?5")
					.setParameter(1, recutionState)
					.setParameter(2, reductionNote)
					.setParameter(3, enterpriseId)
					.setParameter(4, month)
					.setParameter(5, year)
					.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	/**
	 * 统计，增员，减员，参保人员
	 * @param year
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Object[]>     getViewInsuranceWithMonthTotal(Integer year,Integer enterpriseId)
	{
		try {
			String sql="select e.reductionState," +
			"sum(case e.ginsengProtectNature  when '1' then 1 else 0 end ) , " +
			"sum(case e.ginsengProtectNature when '2' then 1 else 0 end )," +
			"sum(case e.ginsengProtectNature when '3' then 1  else 0 end )," +
			" month(e.cinsengDate)  from EnterpriseEmployees e " +
			" where  year(e.cinsengDate) =?1 " +
			" and e.enterprise.enterpriseId=?2 " +
			" and e.pseudoDelete=0 " +
			" and e.departure=0 " +
			" group by month(e.cinsengDate)";

			return em.createQuery(sql).setParameter(1, year).setParameter(2, enterpriseId).getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}
	
	
	
	
	
	
}
