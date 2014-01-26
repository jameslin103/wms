<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <base href="<%=basePath%>"/>
    
    <title>修改企业信息</title>
    
	<meta http-equiv="pragma" content="no-cache"/>
	<meta http-equiv="cache-control" content="no-cache"/>
	<meta http-equiv="expires" content="0"/> 
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3"/>
	<meta http-equiv="description" content="This is my page"/>
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" type="text/css" href="styles/wms.css"/>
	<%@ include file="../help/public_css_js.jsp"%>
	<style>
  		legend{text-align:center; width:40%\9} 
   </style>
	<script type="text/javascript">
		$(function(){
			$("#menu3").addClass("current");
			$("#cancel").click(function(){
				history.go(-1);
			});
			
		});
	</script>
  </head>
  
  <body style="background-color:#F1EEEE">
 	<center>
  		<br/>
		<fieldset style="border:2px groove #F0F0F0; width:1100px;">
		<br/>
			<legend>
				<span style="font-size:30px;">修改企业信息</span>
			</legend>
			<br/>
         <form action="updateEnterprise" method="post" id="add_form">
          <input  type="hidden" name="enterprise.enterpriseId" value="${enterprise.enterpriseId}"/>
            <table width="1000">
              <tbody>
                <tr>
                  <td class="bgc">企业名称</td>
                  <td><input type="text" name="enterprise.fullName" value="${enterprise.fullName}"/></td>
                  <td class="bgc">合同编号</td>
                  <td><input type="text" name="enterprise.contatId" value="${enterprise.contatId}"/></td>
                </tr>
                
                
                <tr>
                  <td class="bgc">行业分类</td>
                  <td>
                  	<!--<s:select list="{'IT|通信|电子|互联网','金融业','房地产|建筑业',
                  					'商业服务','贸易|批发|零售|租赁业',
                  					'文体教育|工艺美术','生产|加工|制造','交通|运输|物流|仓储',
                  					'服务业','文化|传媒|娱乐|体育','能源|矿产|环保','政府|非盈利机构','农|林|牧|渔|其他'}"
                  					name="enterprise.industryType"  headerKey="0" headerValue="-请选择-" 
                  					theme="simple" listKey="'1','2'">
                  	</s:select>
                  -->
                   <select name="enterprise.industryType">
                      <option value="0">-请选择-</option>
                      <option value="1">IT|通信|电子|互联网</option>
                      <option value="2">金融业</option>
                      <option value="3">房地产|建筑业</option>
                      <option value="4">商业服务</option>
                      <option value="5">贸易|批发|零售|租赁业</option>
                      <option value="6">文体教育|工艺美术</option>
                      <option value="7">生产|加工|制造</option>
                      <option value="8">交通|运输|物流|仓储</option>
                      <option value="9">服务业</option>
                      <option value="10">文化|传媒|娱乐|体育</option>
                      <option value="11">能源|矿产|环保</option>
                      <option value="12">政府|非盈利机构</option>
                      <option value="13">农|林|牧|渔|其他</option>
                    </select>
                  </td>
                  <td class="bgc">合同起止时间</td>
                  <td>
                   <input id="d4311" class="Wdate" type="text" style="width:150px;"
                   			onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'d4311\')||\'2020-10-01\'}',skin:'whyGreen'})" 
                   				name="enterpriseContract.startContractDate"></input>
						~
                    <input id="d4312" class="Wdate" type="text" style="width:150px;"
                    	onfocus="WdatePicker({minDate:'#F{$dp.$D(\'d4311\')}',maxDate:'2020-10-01',skin:'whyGreen'})" 
                    		name="enterpriseContract.endContractDate" ></input>
                  </td>
                </tr>
                <tr>
                  <td class="bgc">企业地址</td>
                  <td><input type="text" name="enterprise.address"  value="${enterprise.address}" /></td>
                  <td class="bgc">法人代表</td>
                  <td><input type="text" name="enterprise.legalRepresentative" value="${enterprise.legalRepresentative}"/></td>
                </tr>
                <tr>
                  <td class="bgc">企业联系人</td>
                  <td><input type="text" name="enterprise.contact" value="${enterprise.contact}"/></td>
                  <td class="bgc">开户行</td>
                  <td><input type="text" name="enterprise.accountLine" value="${enterprise.accountLine}"/></td>
                </tr>
                <tr>
                  <td class="bgc">联系电话</td>
                  <td><input type="text" name="enterprise.phone" value="${enterprise.phone}"/></td>
                  <td class="bgc">传&nbsp;&nbsp;真</td>
                  <td><input type="text" name="enterprise.fax" value="${enterprise.fax}"/></td>
                </tr>
                <tr>
                	<td class="bgc">企业银行账号</td>
                    <td><input type="text" name="enterprise.enterpriseBankAccount" value="${enterprise.enterpriseBankAccount}"/></td>
                	<td>派遣负责人</td>
                	<td><input type="text" name="enterprise.send"  value="${enterprise.send}"/></td>
                </tr>
                <tr>
                  <td class="bgc">QQ</td>
                  <td><input type="text" name="enterprise.qq" value="${enterprise.qq}"/></td>
                  <td class="bgc">邮箱</td>
                  <td><input type="text" name="enterprise.email"  value="${enterprise.email}"/></td>
                </tr>
                <tr>
               	 	<td>状态?</td>
	                <td colspan="3">
	                	<s:if test="#request.enterprise.status==0">
	                		<input type="radio" name="enterprise.status" value="0" checked="checked"/>合约中，
							<input type="radio" name="enterprise.status" value="1"/>暂停
	                	</s:if>
	                	<s:elseif test="#request.enterprise.status==1">
	                		<input type="radio" name="enterprise.status" value="0"/>合约中，
	                		<input type="radio" name="enterprise.status" value="1" checked="checked"/>暂停
	                	</s:elseif>
	                </td>
                </tr>
              </tbody>
            </table>
             <div id="opr">
					<input type="submit" value=" 修  改 " class="oprbtn" id="ok" />
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="button" value=" 取  消 " class="oprbtn" id="cancel"/>
			</div>
          </form>
	</fieldset>
	</center>
  </body>
</html>
