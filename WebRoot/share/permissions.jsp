<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<script type="text/javascript">
	$(function(){
		$(".shoose").click(function(){
			alert(this);
			$("#action").addClass('active');
		});
		
		
	
	})


</script>
	<ul class="nav nav-tabs">
		<s:iterator value="#session.menuList" id="menu">
			<s:if test="#menu.url=='viewEnterpriseEmployees'">
				<li id="action">
					<a href="viewEnterpriseEmployees" class="shoose" ><s:property value="#menu.name" />
					</a>
				</li>
			</s:if>
			<s:if test="#menu.url=='viewSalaryBudgetTable'">
				<li id="action">
					<a href="viewSalaryBudgetTable" class="shoose">
						<s:property value="#menu.name" />
					</a>
				</li>
			</s:if>
			<s:if test="#menu.url=='viewInsuranceWithMonth'">
				<li id="action">
					<a href="viewInsuranceWithMonth" class="shoose"><s:property value="#menu.name" />
					</a>
				</li>
			</s:if>
			<s:if test="#menu.url=='viewBalanceDetail'">
				<li id="action">
					<a href="viewBalanceDetail" class="shoose"><s:property value="#menu.name" />
					</a>
				</li>
			</s:if>
			<s:if test="#menu.url=='viewEnterpriseDetailed'">
				<li id="action">
					<a href="viewEnterpriseDetailed" class="shoose"><s:property value="#menu.name" />
					</a>
				</li>
			</s:if>
		</s:iterator>
	</ul>
