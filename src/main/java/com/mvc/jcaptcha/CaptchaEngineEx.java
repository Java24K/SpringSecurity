package com.mvc.jcaptcha;

import java.awt.Color;

import com.octo.captcha.component.image.backgroundgenerator.BackgroundGenerator;
import com.octo.captcha.component.image.backgroundgenerator.GradientBackgroundGenerator;
import com.octo.captcha.component.image.color.SingleColorGenerator;
import com.octo.captcha.component.image.fontgenerator.FontGenerator;
import com.octo.captcha.component.image.fontgenerator.RandomFontGenerator;
import com.octo.captcha.component.image.textpaster.DecoratedRandomTextPaster;
import com.octo.captcha.component.image.textpaster.TextPaster;
import com.octo.captcha.component.image.textpaster.textdecorator.BaffleTextDecorator;
import com.octo.captcha.component.image.textpaster.textdecorator.TextDecorator;
import com.octo.captcha.component.image.wordtoimage.ComposedWordToImage;
import com.octo.captcha.component.image.wordtoimage.WordToImage;
import com.octo.captcha.component.word.wordgenerator.RandomWordGenerator;
import com.octo.captcha.component.word.wordgenerator.WordGenerator;
import com.octo.captcha.engine.image.ListImageCaptchaEngine;
import com.octo.captcha.image.gimpy.GimpyFactory;

/**
 * �Զ������֤����������
 * 
 * @author ghost
 * 
 */
public class CaptchaEngineEx extends ListImageCaptchaEngine {
	/**
	 * ������֤��ľ��巽��
	 */
	@Override
	protected void buildInitialFactories() {

		// ��֤�����С����
		Integer minAcceptedWordLength = new Integer(3);
		// ��֤�����󳤶�
		Integer maxAcceptedWordLength = new Integer(4);

		// ��֤��ͼƬ�ĸ߶ȿ���趨
		Integer imageHeight = new Integer(45);
		Integer imageWidth = new Integer(70);

		// ��֤������ʾ�������С
		Integer minFontSize = new Integer(20);
		Integer maxFontSize = new Integer(20);

		// ����ַ�����
		// abcdefghijklmnopqrstuvwxyz
		WordGenerator wordGenerator = new RandomWordGenerator("0123456789");

		// ������ɫ�������
		BackgroundGenerator backgroundGenerator = new GradientBackgroundGenerator(imageWidth, imageHeight, Color.gray,
				Color.white);

		// �����������
		FontGenerator fontGenerator = new RandomFontGenerator(minFontSize, maxFontSize);

		// ��֤�����ɫ
		SingleColorGenerator singleColor = new SingleColorGenerator(Color.blue);

		// ������ɫ
		BaffleTextDecorator baffleTextDecorator = new BaffleTextDecorator(1, Color.white);
		// ���Դ������
		// LineTextDecorator lineTextDecorator = new LineTextDecorator(1,
		// Color.blue);
		// TextDecorator[] textDecorator = new TextDecorator[2];
		// textDecorator[0] = lineTextDecorator;
		TextDecorator[] textDecorator = new TextDecorator[1];
		textDecorator[0] = baffleTextDecorator;

		TextPaster textPaster = new DecoratedRandomTextPaster(minAcceptedWordLength, maxAcceptedWordLength, singleColor,
				textDecorator);
		// ����ͼƬ���
		WordToImage wordToImage = new ComposedWordToImage(fontGenerator, backgroundGenerator, textPaster);

		addFactory(new GimpyFactory(wordGenerator, wordToImage));
	}
}