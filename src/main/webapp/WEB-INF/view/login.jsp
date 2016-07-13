<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="images/Default.css" type=text/css rel=stylesheet>
<link href="images/xtree.css" type=text/css rel=stylesheet>
<link href="images/User_Login.css" type=text/css rel=stylesheet>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<BODY id=userlogin_body>

	<FORM action="j_spring_security_check" method="post" id="loginForm"
		name="loginForm">

		<DIV id=user_login>
			<DL>
				<DD id=user_top>
					<UL>
						<LI class=user_top_l></LI>
						<LI class=user_top_c></LI>
						<LI class=user_top_r></LI>
					</UL>
				<DD id=user_main>
					<UL>
						<LI class=user_main_l></LI>
						<LI class=user_main_c>
							<DIV class=user_main_box>
								<UL>
									<LI class=user_main_text>用户名：</LI>
									<LI class=user_main_input><INPUT class=TxtUserNameCssClass
										id=TxtUserName maxLength=20 name="j_username"></LI>
								</UL>
								<UL>
									<LI class=user_main_text>密&nbsp;&nbsp;&nbsp;&nbsp;码：</LI>
									<LI class=user_main_input><INPUT class=TxtPasswordCssClass
										id=TxtPassword type=password name="j_password"></LI>
								</UL>
								<UL>
									<LI >验证码： &nbsp;&nbsp;&nbsp;<input class=TxtValidateCssClass type='text' name='jcaptcha' value='' /> 
									<img src="jcaptcha" alt="图片没啦?请刷新页面~" title="看不清?点我~"
										onclick="this.src='/demo/jcaptcha?now=' + new Date().getTime()" />
									</LI>
									<!-- <LI><input type="checkbox"
										name="_spring_security_remember_me" value="true" />记住我?</LI>-->
								</UL>
								<UL>
									<LI>${error}${msg}</LI>
									<c:if test="${SPRING_SECURITY_LAST_EXCEPTION.message != null}">
								        <p>
								           ${SPRING_SECURITY_LAST_EXCEPTION.message}
								        </p>
								    </c:if>
								</UL>

							</DIV>
						</LI>
						<LI class=user_main_r><INPUT class=IbtnEnterCssClass
							id=IbtnEnter
							style="BORDER-TOP-WIDTH: 0px; BORDER-LEFT-WIDTH: 0px; BORDER-BOTTOM-WIDTH: 0px; BORDER-RIGHT-WIDTH: 0px"
							onclick='submit();' type=image src="images/user_botton.gif"
							name=IbtnEnter></LI>
					</UL>
				<DD id=user_bottom>
			</DL>

		</DIV>

		<SPAN id=ValrUserName style="DISPLAY: none; COLOR: red"></SPAN><SPAN
			id=ValrPassword style="DISPLAY: none; COLOR: red"></SPAN><SPAN
			id=ValrValidateCode style="DISPLAY: none; COLOR: red"></SPAN>

		<DIV id=ValidationSummary1 style="DISPLAY: none; COLOR: red"></DIV>

		<DIV></DIV>

	</FORM>
</html>