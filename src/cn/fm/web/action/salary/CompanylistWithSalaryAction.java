package cn.fm.web.action.salary;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import cn.fm.bean.PageView;
import cn.fm.bean.company.Enterprise;
import cn.fm.bean.company.EnterpriseEmployees;
import cn.fm.bean.salary.BalanceDetail;
import cn.fm.bean.salary.CreateSalaryBudgetTable;
import cn.fm.bean.user.User;
import cn.fm.service.company.EnterpriseEmployeesService;
import cn.fm.service.company.EnterpriseService;
import cn.fm.service.salary.BalanceDetailService;
import cn.fm.service.salary.CreateSalaryBudgetTableService;
import cn.fm.service.user.UserService;
import cn.fm.utils.DateUtil;
import cn.fm.utils.StringUtil;
import cn.fm.utils.WebUtil;
import cn.fm.web.action.BaseAction;


@SuppressWarnings("serial")
public class CompanylistWithSalaryAction extends BaseAction{

	
	@Resource
	private CreateSalaryBudgetTableService  createSalaryBudgetTableService;
	@Resource
	private EnterpriseEmployeesService  enterpriseEmployeesService;
	@Resource
	private   BalanceDetailService balanceDetailService;
	@Resource
	private   EnterpriseService    enterpriseService;
	@Resource
	private UserService    userService;
	
	private Enterprise  enterprise;
	
	private User       user;
	
	
	private  CreateSalaryBudgetTable   createSalaryBudgetTable;
	
	private  List<EnterpriseEmployees> employeesRecution;
	
	private   Integer        month; 
	private   String		 year;
	private   Integer        yearSub;
	private   Integer        enterpriseId;
	private   Integer        reductionState;
	private   String         reductionNote;
	
	private int page;
	private Integer   budgetId;
	
	private   Date   salaryDate;
	private   String  uname;
	private   String  tabname;


	/**
	 * 查看工资预算表
	 * @return SUCCESS
	 */
	public String viewCompanyListWithSaraly()
	{
		
			List<User> users=userService.getUsers();
			if(createSalaryBudgetTable==null)createSalaryBudgetTable=new CreateSalaryBudgetTable();
			if(user==null)user=new User();
			if(!StringUtil.isEmpty(createSalaryBudgetTable.getName()) || createSalaryBudgetTable.getSalaryDate()!=null){
				this.setTabname(createSalaryBudgetTable.getName());
				this.setSalaryDate(createSalaryBudgetTable.getSalaryDate());
			}
			if(!StringUtil.isEmpty(user.getId())){
				this.setUname(user.getId());
			}
			PageView<CreateSalaryBudgetTable> pageView=createSalaryBudgetTableService.getAllCreateSalaryBudgetTable(10,this.getPage(),enterprise,createSalaryBudgetTable,user);	
			request.setAttribute("pageView", pageView);
			request.setAttribute("users", users);
			
			
		return SUCCESS;
	}
	
	/**
	 * 查看增减员与参保
	 * @return SUCCESS
	 */
	public String viewCompanyListWithInsurance()
	{
		if(year==null)
		{
			this.year=DateUtil.getCurrentTime().toString().substring(0, 4);
		}
		String formCurrentSql="";
		String groupby="   group by month(o.cinsengDate) order by o.enterprise.enterpriseId, o.cinsengDate asc";
		StringBuffer jpql = new StringBuffer("");
		List<Object> params = new ArrayList<Object>();
			if(month!=null && month!=0){
				jpql.append(" month(o.cinsengDate)=?").append(params.size()+1).append(" and ");
				params.add(month);
			}
				formCurrentSql="o.enterprise.enterpriseId, o.cinsengDate, o.reductionState,o.reductionNote, sum(case o.ginsengProtectNature  when '1' then 1 else 0 end ) " +
							    ",sum(case o.ginsengProtectNature when '2' then 1 else 0 end )," +
							    "sum(case o.ginsengProtectNature when '3' then 1  else 0 end )," +
							    "month(o.cinsengDate)";
				
				jpql.append(" year(o.cinsengDate)=?").append(params.size()+1);
				params.add(Integer.parseInt(year));
				jpql.append(" and o.pseudoDelete=?").append(params.size()+1);
				params.add(0);
				jpql.append(" and o.departure=?").append(params.size()+1);
				params.add(0);
				
				
		PageView<EnterpriseEmployees> pageView = new PageView<EnterpriseEmployees>(10,  this.getPage());
		pageView.setQueryResult(enterpriseEmployeesService.getScrollDataSum(pageView.getFirstResult(), pageView.getMaxresult(),jpql.toString(),params.toArray(), groupby,formCurrentSql));
			
		
			List<Object> map=getEnterpriseEmployees(pageView.getRecords());
			if(map==null)map=new ArrayList<Object>();
			request.setAttribute("map", map);
		return SUCCESS;
	}
	
	public List<Object>   getEnterpriseEmployees(List<EnterpriseEmployees> pageView)
	{
		Map<String, Object>  map=null;
		List<Object> list=new ArrayList<Object>();
		for (Object en : pageView) {
			
			
			Object[] e=(Object[]) en;
			Integer enterpriseid=Integer.valueOf(e[0].toString());
			String cinsengDate=e[1]==null?null:e[1].toString();
			String reductionState=e[2]==null?null:e[2].toString();
			String reductionNote=e[3]==null?"":e[3].toString();
			String addIncrease=e[4]==null?null:e[4].toString();
			String cinseng=e[5]==null?null:e[5].toString();
			String ginsengProtectNature=e[6]==null?null:e[6].toString();
			String month=e[7]==null?null:e[7].toString();
			
			Enterprise enterprise=enterpriseService.find(enterpriseid);
			map=new HashMap<String, Object>();
			map.put("enterprise", enterprise);
			map.put("cinsengDate", cinsengDate);
			map.put("reductionState", reductionState);
			map.put("addIncrease", addIncrease);
			map.put("cinseng", cinseng);
			map.put("ginsengProtectNature", ginsengProtectNature);
			map.put("month", month);
			map.put("reductionNote", reductionNote);
			list.add(map);
		} 
		
		
		return list;
	}
	
	
	/**
	 * 查看资金往来
	 * @return SUCCESS
	 */
	public String viewCompanyListWithBalance()
	{
		
		PageView<BalanceDetail> pageView =balanceDetailService.getAllEnterpriseBalanceDetail(10, this.getPage(), enterprise);
		request.setAttribute("pageView", pageView);
		request.setAttribute("balanceDetailList", treconstructBalanceDetail( pageView.getRecords()));
		
		return SUCCESS;
	}

	private List<Object> treconstructBalanceDetail(List<BalanceDetail> balanceDetailList) {
		
		Map<String, Object>  map;
		List<Object>  list=new ArrayList<Object>();
		for (Object bal : balanceDetailList) {
			 Object[] ob=( Object[])bal;
			 Integer enterpriseId=(Integer)(ob[0]==null?0:ob[0]);
			
			map=new HashMap<String, Object>();
			map.put("balance",ob[1]==null?null:ob[1]);
			map.put("enterprise", enterpriseService.find(enterpriseId));
			list.add(map);
		}

		return list;
	}
	
	public String findEnterpriseEmployeesRecution()
	{
		
		 employeesRecution=enterpriseEmployeesService.findRecutionState(enterpriseId,month,Integer.parseInt(year));
		if(employeesRecution==null || employeesRecution.size()==0)
			employeesRecution=new ArrayList<EnterpriseEmployees>();
		
		return "employeesRecution";
	}
	/**
	 * 修改增员,参保，状态
	 * @return
	 */
	public String updateRecutionState()
	{
		if(enterpriseId==null || reductionState==null)return INPUT;
		
		enterpriseEmployeesService.updateRecutionState(enterpriseId,reductionState,reductionNote,month,Integer.parseInt(year));
		
		return SUCCESS;
	}
	public String findSalaryTable()
	{
		if(budgetId==null)return SUCCESS;
		createSalaryBudgetTable=createSalaryBudgetTableService.find(budgetId);
		return SUCCESS;
	}
	public String updateSaralyStatus()
	{
//		CreateSalaryBudgetTable createSalaryBudgetTablePO=createSalaryBudgetTableService.find(budgetId);
//		createSalaryBudgetTableService.clear();
//		createSalaryBudgetTablePO.setHeLinesDate(createSalaryBudgetTable.getHeLinesDate());
//		createSalaryBudgetTablePO.setCmbcDate(createSalaryBudgetTable.getCmbcDate());
//		createSalaryBudgetTablePO.setCashnumberDate(createSalaryBudgetTable.getCashnumberDate());
//		
//		createSalaryBudgetTableService.update(createSalaryBudgetTablePO);
		User user=WebUtil.getUser(request);
		createSalaryBudgetTable.setUser_operator(user.getEmployee().getName());
		createSalaryBudgetTable.setBudgetId(budgetId);
		createSalaryBudgetTableService.updateSalaryStatus(createSalaryBudgetTable);
		
		return SUCCESS;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public Integer getBudgetId() {
		return budgetId;
	}
	public void setBudgetId(Integer budgetId) {
		this.budgetId = budgetId;
	}
	public CreateSalaryBudgetTable getCreateSalaryBudgetTable() {
		return createSalaryBudgetTable;
	}
	public void setCreateSalaryBudgetTable(
			CreateSalaryBudgetTable createSalaryBudgetTable) {
		this.createSalaryBudgetTable = createSalaryBudgetTable;
	}
	public int getPage() {
		return page<1?1:page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	
	public Integer getReductionState() {
		return reductionState;
	}

	public void setReductionState(Integer reductionState) {
		this.reductionState = reductionState;
	}

	public Integer getEnterpriseId() {
		return enterpriseId;
	}

	public void setEnterpriseId(Integer enterpriseId) {
		this.enterpriseId = enterpriseId;
	}

	public Integer getYearSub() {
		return yearSub;
	}

	public void setYearSub(Integer yearSub) {
		this.yearSub = yearSub;
	}

	public Integer getMonth() {
		return month;
	}

	public void setMonth(Integer month) {
		this.month = month;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	
	public String getReductionNote() {
		return reductionNote;
	}

	public void setReductionNote(String reductionNote) {
		this.reductionNote = reductionNote;
	}

	public void setCreateSalaryBudgetTableService(
			CreateSalaryBudgetTableService createSalaryBudgetTableService) {
		this.createSalaryBudgetTableService = createSalaryBudgetTableService;
	}

	public void setEnterpriseEmployeesService(
			EnterpriseEmployeesService enterpriseEmployeesService) {
		this.enterpriseEmployeesService = enterpriseEmployeesService;
	}

	public void setBalanceDetailService(BalanceDetailService balanceDetailService) {
		this.balanceDetailService = balanceDetailService;
	}
	
	

	public void setEnterpriseService(EnterpriseService enterpriseService) {
		this.enterpriseService = enterpriseService;
	}

	public List<EnterpriseEmployees> getEmployeesRecution() {
		return employeesRecution;
	}

	public void setEmployeesRecution(List<EnterpriseEmployees> employeesRecution) {
		this.employeesRecution = employeesRecution;
	}

	public Enterprise getEnterprise() {
		return enterprise;
	}

	public void setEnterprise(Enterprise enterprise) {
		this.enterprise = enterprise;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Date getSalaryDate() {
		return salaryDate;
	}

	public void setSalaryDate(Date salaryDate) {
		this.salaryDate = salaryDate;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public String getTabname() {
		return tabname;
	}

	public void setTabname(String tabname) {
		this.tabname = tabname;
	}
	
	
}
