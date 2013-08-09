package cn.fm.web.action.user;

import javax.annotation.Resource;
import cn.fm.bean.user.Buyer;
import cn.fm.service.user.BuyerService;
import cn.fm.web.action.BaseAction;




@SuppressWarnings("serial")
public class LoginAction extends BaseAction{
	
		private Buyer buyer;
		private Buyer loginUser;
		
		@Resource
		private BuyerService buyerSerivce;
		
		public Buyer getBuyer() {
			return buyer;
		}
		public void setBuyer(Buyer buyer) {
			this.buyer = buyer;
		}
		
		
		public String userLogin()
		{
			if(buyer==null)return INPUT;
			if (isInvalid(buyer.getUsername().trim()))
		            return INPUT;
		    if (isInvalid(buyer.getPassword().trim()))
		            return INPUT;
			boolean isCheckUser=buyerSerivce.checkUser(buyer.getUsername(), buyer.getPassword());
			if(isCheckUser!=true){
				return INPUT;	
			}
			loginUser=buyerSerivce.find(buyer.getUsername().trim());
			if( loginUser!=null){
				request.getSession().setAttribute("user",loginUser);
			}
			
			return SUCCESS;
		}
	
		private boolean isInvalid(String value) {
		        return (value == null || value.length() == 0);
		}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
