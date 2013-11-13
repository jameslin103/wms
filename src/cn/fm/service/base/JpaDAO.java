package cn.fm.service.base;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.transaction.annotation.Transactional;
import cn.fm.bean.PageData;
import cn.fm.bean.utils.AbstractPageQueryUtil;
import cn.fm.utils.CollectionsUtil;
import cn.fm.utils.CommonUtil;
import cn.fm.utils.Constant;
import cn.fm.utils.StringUtil;


@SuppressWarnings("unchecked")
@Transactional
public class JpaDAO {
	
	@PersistenceContext protected EntityManager em;
	
	
	 public PageData findByPage(final String hsql, final Map pmap, final int topage, final int pagesize, final String orderby) {
	        String hstr = hsql;
	        if (StringUtil.isEmptyStr(hstr)) {
	            return null;
	        }
	        if (hstr.toUpperCase().trim().indexOf("SELECT") == 0) {
	            hstr = "select count(*) " + hstr.substring(hstr.toUpperCase().indexOf("FROM"), hstr.length());
	        } else {
	            hstr = "select count(*) " + hstr;
	        } 
	        
	        Query query = em.createQuery(hstr);
	        if (pmap != null)
	            CommonUtil.addQueryParams(query, pmap);
	        List ls = query.getResultList();
	        Integer totalrow = new Integer(0);
	     
	        if (ls != null && ls.size() > 0) {
	            totalrow = new Integer(ls.get(0).toString());
	            if (hsql.trim().indexOf("having") != -1) {
	                totalrow = new Integer(ls.size());
	            } else {
	                totalrow = new Integer(ls.get(0).toString());
	            }
	        }
	        String qhsql = hsql;
	        if (orderby != null && !orderby.equals("")) {
	            qhsql = hsql + " order by " + orderby;
	        }
	        query = em.createQuery(qhsql);
	        if (pmap != null)
	            CommonUtil.addQueryParams(query, pmap);
	       
	        int size = pagesize;
	        if (size <= 0) size = Constant.WMS_PAGE_SIZE;
	        int fromrow = (topage - 1) * size;
	        if(topage!=0){
		        query.setFirstResult(fromrow);
		        query.setMaxResults(size);
	        }
//	        query.setCacheable(true);
	        List datals = query.getResultList();
	        PageData pdata = new PageData(); 
	        pdata.setDatals(datals);
	        pdata.setTotalRows(totalrow);
	        int torowid = fromrow + size;
	        if (torowid > totalrow) torowid = totalrow;
	        pdata.setFromRowId(fromrow + 1);
	        pdata.setToRowId(torowid);
	        int pagenum = (totalrow % size == 0) ? totalrow / size : totalrow / size + 1;
	        pdata.setPagesize(size);
	        pdata.setTotalPageNum(pagenum);
	        pdata.setCurrentPage(topage);
	        pdata.setHql(qhsql);

	        return pdata;
	    }
	  public PageData findPageData(final String hsql,String alias,final Map pmap, final int topage, final int pagesize, final String orderby) {
	        String hstr = hsql;
	        if (StringUtil.isEmptyStr(hstr)) {
	            return null;
	        }
	        if (hstr.toUpperCase().trim().indexOf("SELECT") == 0) {
	            hstr = "select count("+ alias + ") " + hstr.substring(hstr.toUpperCase().indexOf("FROM"), hstr.length());
	        } else {
	            hstr = "select count("+ alias + ") " + hstr;
	        }
	        Query query = null;
	        try {
	            query =em.createQuery(hstr);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        if (pmap != null)
	            CommonUtil.addQueryParams(query, pmap);
	        Object totalRecod = query.getSingleResult();
	        int totalrow = (Integer)totalRecod;

	        String qhsql = hsql;
	        if (orderby != null && !orderby.equals("")) {
	            qhsql = hsql + " order by " + orderby;
	        }
	        query = em.createQuery(qhsql);
	        if (pmap != null)
	            CommonUtil.addQueryParams(query, pmap);
	        int size = pagesize;
	        if (size <= 0) size = Constant.WMS_PAGE_SIZE;
	        int fromrow = (topage - 1) * size;
	        query.setFirstResult(fromrow);
	        query.setMaxResults(size);
	        List datals = query.getResultList();
	        PageData pdata = new PageData();
	        pdata.setDatals(datals);
	        pdata.setTotalRows(totalrow);
	        int torowid = fromrow + size;
	        if (torowid > totalrow) torowid = totalrow;
	        pdata.setFromRowId(fromrow + 1);
	        pdata.setToRowId(torowid);
	        int pagenum = (totalrow % size == 0) ? totalrow / size : totalrow / size + 1;
	        pdata.setPagesize(size);
	        pdata.setTotalPageNum(pagenum);
	        pdata.setCurrentPage(topage);
	        pdata.setHql(qhsql);
	        return pdata;
	    }
	public PageData findPageByList(final int topage, final int pagesize, final String sortName,String sortOrder,List list)
	  {
	        boolean reverse = false;
	        if(sortOrder.equals("desc"))reverse = true;
	        List datals = CollectionsUtil.sortList(list, sortName, reverse);
	        return findPageByList(topage,pagesize,datals);

	  }
	    public PageData findPageByList(final int topage, final int pagesize,List list){
	        List datals = list;
	        int totalrow = (Integer)list.size();
	        int size = pagesize;
	        if (size <= 0) size = Constant.WMS_PAGE_SIZE;
	        int fromrow = (topage - 1) * size;
	        int torowid = fromrow + size;
	        if (torowid > totalrow) torowid = totalrow;
	        datals = getDatals(fromrow,torowid,datals);
	        PageData pdata = new PageData();
	        pdata.setDatals(datals);
	        pdata.setTotalRows(totalrow);
	        pdata.setFromRowId(fromrow + 1);
	        pdata.setToRowId(torowid);
	        int pagenum = (totalrow % size == 0) ? totalrow / size : totalrow / size + 1;
	        pdata.setPagesize(size);
	        pdata.setTotalPageNum(pagenum);
	        pdata.setCurrentPage(topage);
	        return pdata;
	    }
	 	public PageData findPageDataProcedure(AbstractPageQueryUtil pageQuery)
	 	{
	        //pageQuery.setSession();
	        return pageQuery.queryDataProcedure();
	    }
	
	 	 public PageData findPageDataSQL(AbstractPageQueryUtil pageQuery)
	 	 {
	         if(!pageQuery.isValidInputParameters())
	             return null;
	         //pageQuery.setSession(getSession());
	         return pageQuery.queryData();
	     }
	 	 public List getDatals(int fromrow,int recordCount,List list){ 
	     	List pageList = new ArrayList();
	     	for(int i = fromrow;i<recordCount;i++){
	     		pageList.add(list.get(i));
	     	}
	     	return pageList;
	     }
	
}
