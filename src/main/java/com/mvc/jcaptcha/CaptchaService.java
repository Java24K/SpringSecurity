package com.mvc.jcaptcha;

import org.springframework.stereotype.Service;

import com.octo.captcha.engine.CaptchaEngine;
import com.octo.captcha.service.captchastore.CaptchaStore;
import com.octo.captcha.service.image.DefaultManageableImageCaptchaService;

/*
 * �Զ������֤��������
 */
@Service
public class CaptchaService extends DefaultManageableImageCaptchaService {
	public CaptchaService() {
		super();
	}

	/**
	 * @param minSeconds
	 * @param maxStoreSize
	 *            ��󻺴��С
	 * @param loadBefore
	 */
	public CaptchaService(int minSeconds, int maxStoreSize, int loadBefore) {
		super(minSeconds, maxStoreSize, loadBefore);
	}

	public CaptchaService(CaptchaStore captchaStore, CaptchaEngine captchaEngine, int minSeconds, int maxStroreSize,
			int loadBefore) {
		super(captchaStore, captchaEngine, minSeconds, maxStroreSize, loadBefore);
	}

	/**
	 * ��д��֤���� �ڸ��쳣 �������쳣ʱ�ж�Ϊ��֤ʧ��
	 */
	@Override
	public Boolean validateResponseForID(String ID, Object response) {
		Boolean isHuman;

		try {
			isHuman = super.validateResponseForID(ID, response);
		} catch (Exception e) {
			isHuman = false;
		}

		return isHuman;
	}
}