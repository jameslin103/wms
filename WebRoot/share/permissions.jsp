<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript">
	$(function(){
		$(".shoose").click(function(){
			alert(this);
			$(this).addClass('active');
			$("#action").addClass('active');
		});
		
		
	
	})


</script>
	<ul class="nav nav-tabs">
		<s:iterator value="#session.menuList" id="menu">
			<s:if test="#menu.url=='viewEnterpriseDetailed'">
				<li >
					<a href="viewEnterpriseDetailed"  ><s:property value="#menu.name" />
					</a>
				</li>
			</s:if>
			<s:if test="#menu.url=='viewEnterpriseEmployees'">
				<li >
					<a href="viewEnterpriseEmployees"  ><s:property value="#menu.name" />
					</a>
				</li>
			</s:if>
			<s:if test="#menu.url=='viewSalaryBudgetTable'">
				<li >
					<a href="viewSalaryBudgetTable" >
						<s:property value="#menu.name" />
					</a>
				</li>
			</s:if>
			<s:if test="#menu.url=='viewInsuranceWithMonth'">
				<li >
					<a href="viewInsuranceWithMonth" ><s:property value="#menu.name" />
					</a>
				</li>
			</s:if>
			<s:if test="#menu.url=='viewBalanceDetail'">
				<li >
					<a href="viewBalanceDetail" ><s:property value="#menu.name" />
					</a>
				</li>
			</s:if>
			<s:if test="#menu.url=='viewEnterpriseDetailed'">
				<li >
					<a href="viewEnterpriseDetailed" ><s:property value="#menu.name" />
					</a>
				</li>
			</s:if>
		</s:iterator>
	</ul>
