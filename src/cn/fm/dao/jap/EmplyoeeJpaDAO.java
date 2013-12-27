package cn.fm.dao.jap;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import cn.fm.bean.PageBean;
import cn.fm.bean.user.Employee;
import cn.fm.dao.EmployeeDAO;


@Repository
public class EmplyoeeJpaDAO extends JpaDAO<Employee> implements EmployeeDAO {

	@Override
	public List<Employee> findByCondition(Employee employee) {
		String jpql = "from Employee " + createCondition(employee);
		return findByJPQL(jpql);
	}

	private String createCondition(Employee employee) {
		if (employee == null) {
			return "";
		}
		StringBuilder builder = new StringBuilder(" where 1=1 ");
		if (employee.getName() != null && !employee.getName().trim().equals("")) {
			builder.append(" and name like '%" + employee.getName() + "%'");
		}
		if (employee.getGender() != null
				&& !employee.getGender().trim().equals("")) {
			builder.append(" and gender='" + employee.getGender() + "'");
		}
		if (employee.getDegree() != null
				&& !employee.getDegree().trim().equals("")) {
			builder.append(" and degree='" + employee.getDegree() + "'");
		}
		if (employee.getDepartment() != null
				&& StringUtils.isNotBlank(employee.getDepartment().getId())) {
			builder.append(" and department.id='"
					+ employee.getDepartment().getId() + "'");
		}
		return builder.toString();
	}


	public PageBean<Employee> findByPage(int page, int pageSize,
			Employee employee) {
		PageBean<Employee> pageBean = new PageBean<Employee>();
		pageBean.setPage(page);
		pageBean.setPageSize(pageSize);
		String jpql = "from Employee " + createCondition(employee);
		List<Employee> data = findByJPQLAndPage(page, pageSize, jpql);
		pageBean.setData(data);
		jpql = "select count(*) from Employee " + createCondition(employee);
		int totalNums = findTotalNums(jpql);
		pageBean.setTotalNums(totalNums);
		pageBean.setTotalPage(totalNums % pageSize == 0 ? totalNums / pageSize
				: totalNums / pageSize + 1);
		pageBean.setActualPageSize(data.size());
		return pageBean;
	}
}
