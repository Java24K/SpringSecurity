package com.mvc.jcaptcha;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.octo.captcha.service.CaptchaServiceException;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

public class ImageCaptchaServlet extends HttpServlet {
	// 验证码生成类
	@Autowired
	private CaptchaService imageCaptchaService;
	// Spring中的配置名称
	// private String beanName = "imageCaptchaService";

	/**
	 * 初始化ImageCaptchaService 对象
	 */
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		//  将servlet上下文注入到根上下文 可以使用@Autowired
		SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, config.getServletContext());
		//  根据当前servlet所在的容器上下文(servletContext)得到容器中的web上下文即根上下文 得到根上下文的bean 不可以使用@Autowired
		//	WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(config.getServletContext());
		//	imageCaptchaService = (CaptchaService) context.getBean(beanName, ImageCaptchaService.class);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		byte[] captchaChallengeAsJpeg = null;
		// the output stream to render the captcha image as jpeg into
		// 可以捕获内存缓冲区的数据，转换成字节数组
		ByteArrayOutputStream jpegOutputStream = new ByteArrayOutputStream();
		try {
			// get the session id that will identify the generated captcha.
			// the same id must be used to validate the response, the session id
			// 1. is a good candidate!
			String captchaId = req.getSession().getId();
			req.getSession().setAttribute("captchaId", captchaId);
			// call the ImageCaptchaService getChallenge method
			// 2.  将一副图片加载到内存中
			BufferedImage challenge = imageCaptchaService.getImageChallengeForID(captchaId, req.getLocale());
			// 3. 是创建一个和指定输出流关联的JPEGImageEncoder
			JPEGImageEncoder jpegEncoder = JPEGCodec.createJPEGEncoder(jpegOutputStream);
			// 4.  Encode a BufferedImage as a JPEG data stream. to jpegOutputStream
			jpegEncoder.encode(challenge);
		} catch (IllegalArgumentException e) {
			resp.sendError(HttpServletResponse.SC_NOT_FOUND);
			return;
		} catch (CaptchaServiceException e) {
			resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			return;
		}
		// 5. JPEG data stream to byte array
		captchaChallengeAsJpeg = jpegOutputStream.toByteArray();
		// 6. flush it in the response
		resp.setHeader("Cache-Control", "no-store");
		resp.setHeader("Pragma", "no-cache");
		resp.setDateHeader("Expires", 0);
		resp.setContentType("image/jpeg");
		ServletOutputStream responseOutputStream = resp.getOutputStream();
		responseOutputStream.write(captchaChallengeAsJpeg);
		responseOutputStream.flush();
		responseOutputStream.close();
	}
}