<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib  prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>新疆大学论坛</title>
        <base href="<%=basePath%>">
	    <meta http-equiv="X-UA-Compatible" content="IE=edge">
	    <meta name="viewport" content="width=device-width, initial-scale=1">
	    <script src="bootstrap/js/jquery.min.js"></script>
	    <script src="bootstrap/dist/js/bootstrap.min.js"></script>
	    <link href="bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">
	    <link href="bootstrap/dist/css/navbar-static-top.css" rel="stylesheet">
    </head>
    <body>
    	
        <s:set name="total" value="total" scope="application"></s:set>
        <s:set name="yestNum" value="yestNum" scope="application"></s:set>
        <s:set name="todayNum" value="todayNum" scope="application" ></s:set>
        <s:set name="student" value="student" scope="session" ></s:set>
        
	    <nav class="navbar navbar-default navbar-static-top">
	      <div class="container">
	        <div class="navbar-header">
	          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
	            <span class="sr-only">Toggle navigation</span>
	            <span class="icon-bar"></span>
	            <span class="icon-bar"></span>
	            <span class="icon-bar"></span>
	          </button>
	          <a class="navbar-brand" href="#">新疆大学论坛</a>
	        </div>
	        <div id="navbar" class="navbar-collapse collapse">
	          <ul class="nav navbar-nav">
	            <li class="active"><a href="#">Home</a></li>
	            <li><a href="#about">About</a></li>
	            <li><a href="#contact">Contact</a></li>
	            <li class="dropdown">
	              <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Dropdown <span class="caret"></span></a>
	              <ul class="dropdown-menu">
	                <li><a href="#">Action</a></li>
	                <li><a href="#">Another action</a></li>
	                <li><a href="#">Something else here</a></li>
	                <li role="separator" class="divider"></li>
	                <li class="dropdown-header">Nav header</li>
	                <li><a href="#">Separated link</a></li>
	                <li><a href="#">One more separated link</a></li>
	              </ul>
	            </li>
	          </ul>
	          <ul class="nav navbar-nav navbar-right">
	            <li><a href="../navbar/">Default</a></li>
	            <li class="active"><a href="./">Static top <span class="sr-only">(current)</span></a></li>
	            <li><a href="../navbar-fixed-top/">Fixed top</a></li>
	          </ul>
	        </div><!--/.nav-collapse -->
	      </div>
	    </nav>

        <div class="container" >
          <div style="float:left">
          	
          </div>
          <div style="float:right">
	          <h3 style="color:red;"> 帖子排行榜:</h3>
	          <ul>
	          <!-- 显示帖子排行榜的前十个 -->
	          <s:iterator value="hotPosts" id="row" status="st">
	          	<s:if test="#st.index<10">
	              <li><a href="post!viewDetail.action?pid=<s:property value="id" />"><s:property value="#row.name" />&nbsp; &nbsp;</a>【点击量<s:property value="#row.count" />】</li>
	            </s:if>
	          </s:iterator>
	          </ul>
          </div>
        </div>

        <div class="container">
            <s:iterator value="rootBoard" id="row">
            	<div class="row">
		                <h2><s:property value="#row.name" /></h2>
                        <s:iterator value="#row.boards" id="sub">  
        				   <div class="col-md-4">
	                    	   <p>
                          		<h4><s:property value="#sub.name" /></h4>
	                            <s:if test="#sub.boardImg!=null">
	                                <img width="60" height="60" src="/upload/<s:property value="#sub.boardImg" />" class="bimg" ></img>
	                            </s:if>
	                            <s:else>
	                                <img width="60" height="60" src="/images/bimg.gif" class="bimg" ></img>
	                            </s:else>
	                           </p>
                           		<p><a class="btn btn-default" href="login!showAll.action?bid=<s:property value="#sub.id" /> " role="button">查看详情  &raquo;</a></p>
                           </div>
                           
                        </s:iterator>
                </div>
            </s:iterator>
        </div> <!-- continer ends -->

      <hr>

      <footer>
        <p>&copy; 2016 农二代小组, Inc.</p>
      </footer>

    </body>
</html>
