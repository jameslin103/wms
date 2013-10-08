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
		Query query = em.createQuery("select e from EnterpriseEmployees e where e.enterprise.enterpriseId=?1  and e.departure=0");
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
				employees.setGinsengProtectNature(data[19].toString());
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
		Query query=em.createQuery("select e from EnterpriseEmployees e  where e.enterprise.enterpriseId=?1  and e.departure=0 ");
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
	
	
	/**
	 * 统计新增人数
	 * @param enterpriseId
	 * @return
	 */
	public long newStaffCount(Integer enterpriseId){
		Query query=em.createQuery("select count(e) from  EnterpriseEmployees e where e.enterprise.enterpriseId=?1  and e.departure=0 and e.ginsengProtectNature='新增' ");
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
		Query query=em.createQuery("select e from  EnterpriseEmployees e where e.enterprise.enterpriseId=?1 and e.whetherGinseng=1 and e.departure=0  and e.ginsengProtectNature='新增' order by employeesId asc ");
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
	 * 批量上传增员；减员；并且与数据库匹配
	 */
	@SuppressWarnings({ "unchecked", "static-access" })
	public boolean  batchIncreaseEmployees(File file , String fiName,int number,int readRow)
	{
		boolean flag=false;
		GenerateSqlFromExcel excel =new GenerateSqlFromExcel();
		
			List<String[]> arrayList;
			try {
				arrayList = excel.generateStationBugSql(file,fiName,number,readRow);
				if(arrayList==null)return false;
				List<EnterpriseEmployees> employeesListPO=getAllEnterpriseEmployees();
				for (int i = 0; i < arrayList.size(); i++)
				{
					String[] data = arrayList.get(i);
					if(data.length!=0 || !data.equals("")){
					for(EnterpriseEmployees emp:employeesListPO){
						String cardNumber=(data[7].toString())== null?"":data[7].toString();
						if(StringUtil.isEmpty(emp.getCardNumber()))return false;
						if(StringUtil.isEmpty(cardNumber))return false;
						if(cardNumber.equals(emp.getCardNumber())){
							emp.setSociaSecurity(data[10].toString()== null?"":data[10].toString());
							emp.setHealthCare((data[11].toString())== null?"":data[11].toString());
							emp.setAccumulationFund((data[12].toString())== null?"":data[12].toString());
							emp.setGinsengProtectNature(data[13].toString()== null?"":data[13].toString());
							emp.setCinsengDate(data[14].toString()== null?emp.getCreateDate():DateUtil.StringToDate(data[14].toString(), DateUtil.FORMAT_DATE));
							emp.setNote(data[15].toString()== null?"":data[15].toString());

							super.update(emp);
							flag=true;
						}
						
					}	
				}
			}
				
		} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
		}
	   return flag;
	}
	/**
	 * 
	 * @return 所有企业员工
	 */
	@SuppressWarnings("unchecked")
	public List<EnterpriseEmployees> getAllEnterpriseEmployees()
	{
		Query query = em.createQuery("select e from EnterpriseEmployees e where e.departure=0 ");
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
			query = em.createQuery("select e from EnterpriseEmployees e where (e.ginsengProtectNature='新增' or e.ginsengProtectNature='续保' or e.ginsengProtectNature='减员')" +
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
	
    
	
	
	
	
	
	
	
	

}
