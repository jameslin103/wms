<%@ page language="java" pageEncoding="UTF-8"%>

 <font color="#2E9AFE"> 
        当前页:第${pageView.currentpage}页 | 总记录数:${pageView.totalrecord}条 | 每页显示:${pageView.maxresult}条 | 总页数:${pageView.totalpage}页
</font>　
<s:iterator begin="%{#request.pageView.pageindex.startindex}" end="%{#request.pageView.pageindex.endindex}" var="wp">
	<s:if test="%{#request.pageView.currentpage==wp}">
	   <b><font color="#2E9AFE">第${wp}页</font></b>
    </s:if>
   	<s:if test="%{#request.pageView.currentpage!=wp}">
   			<a href="javascript:topage('${wp}')">第${wp}页</a>
   	</s:if>
</s:iterator>
