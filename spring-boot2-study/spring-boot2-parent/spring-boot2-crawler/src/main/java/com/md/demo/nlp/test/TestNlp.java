package com.md.demo.nlp.test;

import org.nlpcn.commons.lang.finger.FingerprintService;
import org.nlpcn.commons.lang.finger.SimHashService;

/**
 * 指纹去重库Github地址：https://github.com/hemin1003/nlp-lang
 * 
 * 更多阅读：https://blog.csdn.net/hemin1003/article/details/82819489（【应用算法】信息流-推荐系统的去重策略）
 * 
 * @author Minbo
 */
public class TestNlp {

	/**
	 * 任何一段信息文字，都可以对应一个不太长的随机数，作为区别它和其它信息的指纹（Fingerprint)。
	 * 只要算法设计的好，任何两段信息的指纹都很难重复，就如同人类的指纹一样。信息指纹在加密、信息压缩和处理中有着广泛的应用。
	 * 这里的做法是文章抽取特征词，压缩为md5指纹。利用这些指纹进行hash去重。广泛应用在搜索结果、推荐结果去重。
	 */
	public static void main(String[] args) {
		
		String content = "卓尔防线继续伤筋动骨 队长梅方出场再补漏说起来卓尔队长梅方本赛季就是个“补漏”的命！在中卫与右边后卫间不停地轮换。如果不出意外，今天与广州恒大一战梅方又要换位置，这也是汉军队长连续三场比赛中的第三次换位。而从梅方的身上也可以看出，本赛季汉军防线如此“折腾”，丢球多也不奇怪了。梅方自2009赛季中乙出道便一直司职中后卫，还曾入选过布拉泽维奇国奥队，也是司职的中卫。上赛季，梅方与忻峰搭档双中卫帮助武汉卓尔队中超成功，但谁知进入本赛季后从第一场比赛开始梅方便不断因为种种“意外”而居无定所。联赛首战江苏舜天时，也是由于登贝莱受伤，朱挺位置前移，梅方临危受命客串右边后卫。第二轮主场与北京国安之战梅方仅仅打了一场中卫，又因为柯钊受罚停赛4轮而不得不再次到边路“补漏”。随着马丁诺维奇被弃用，梅方一度成为中卫首选，在与上海东亚队比赛中，邱添一停赛，梅方与忻峰再度携手，紧接着与申鑫队比赛中移至边路，本轮忻峰又停赛，梅方和邱添一成为中卫线上最后的选择。至于左右边后卫位置，卓尔队方面人选较多，罗毅、周恒、刘尚坤等人均可出战。记者马万勇原标题：卓尔防线继续伤筋动骨队长梅方出场再补漏稿源：中新网作者：";
		String content2 = "在中卫与右边后卫间不停地轮换。卓尔防线继续伤筋动骨 队长梅方出场再补漏说起来卓尔队长梅方本赛季就是个“补漏”的命！还曾入选过布拉泽维奇国奥队，也是司职的中卫。上赛季，梅方与忻峰搭档双中卫帮助武汉卓尔队中超成功，但谁知进入本赛季后从第一场比赛开始梅方便不断因为种种“意外”而居无定所。";

		// 方式1
//		String f1 = new FingerprintService().fingerprint(content);
//		String f2 = new FingerprintService().fingerprint(content2);
//		System.out.println(f1);
//		System.out.println(f2);
//		System.out.println(f1.equals(f2));

		// 方式2
		SimHashService service = new SimHashService();
		String f3 = String.valueOf(service.fingerprint(content));
		String f4 = String.valueOf(service.fingerprint(content2));
		System.out.println(f3);
		System.out.println(f4);
		System.out.println(f3.equals(f4));

//		System.out.println(service.hmDistance(content, content2));
	}

}
